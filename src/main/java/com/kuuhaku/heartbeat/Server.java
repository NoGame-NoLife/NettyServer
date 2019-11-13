package com.kuuhaku.heartbeat;

import com.kuuhaku.heartbeat.protocol.CustomProtocol;
import com.kuuhaku.heartbeat.protocol.ServerDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.resolver.InetSocketAddressResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.annotation.Resource;
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

    @Resource
    private HeatBeatHandle heatBeatHandle;
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new IdleStateHandler(5,0,0)).addLast(heatBeatHandle).addLast(new ServerDecoder());
    }
}