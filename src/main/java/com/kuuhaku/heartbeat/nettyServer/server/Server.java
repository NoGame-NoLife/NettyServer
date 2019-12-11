package com.kuuhaku.heartbeat.nettyServer.server;

import com.kuuhaku.heartbeat.nettyServer.handle.ScoketChooseHandle;
import com.kuuhaku.heartbeat.nettyServer.handle.DistributeHandle;
import com.kuuhaku.heartbeat.nettyServer.handle.codec.ProtocolEncoder;
import com.kuuhaku.heartbeat.nettyServer.handle.codec.TcpSocketDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class Server {
    private final static Logger logger = LoggerFactory.getLogger(Server.class);

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();

    @Value("${port}")
    private int port;

    public void run() throws InterruptedException {
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(boss,worker).channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                .childHandler(new serverInitalizer());
        ChannelFuture future = sb.bind().sync();
        if(future.isSuccess()){
            logger.info("start server success....");
        }
    }

    @PreDestroy
    public void  destroy(){
        boss.shutdownGracefully().syncUninterruptibly();
        worker.shutdownGracefully().syncUninterruptibly();
    }

}

class serverInitalizer extends ChannelInitializer<Channel>{
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new IdleStateHandler(3,0,0))
                .addLast(new ScoketChooseHandle())
                .addLast("protocolEncoder", new ProtocolEncoder())
                //TcpSocket解码器
                .addLast("byteToProtocol",new TcpSocketDecoder())
                //处理或分发收到的请求
                .addLast("heartbeatResolve",new DistributeHandle());
    }
}