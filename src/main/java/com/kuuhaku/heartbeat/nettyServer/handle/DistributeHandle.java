package com.kuuhaku.heartbeat.nettyServer.handle;

import com.kuuhaku.heartbeat.cache.SystemCache;
import com.kuuhaku.heartbeat.nettyServer.channelMap.ChannelMap;
import com.kuuhaku.heartbeat.service.MongoDBService;
import com.kuuhaku.heartbeat.util.SystemManager;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class DistributeHandle extends SimpleChannelInboundHandler<BaseProtocol> {
    private final static Logger logger = LoggerFactory.getLogger(DistributeHandle.class);

    private boolean isInit = false;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
        ChannelMap.remove((NioSocketChannel) ctx.channel());
        //TODO:添加下线逻辑
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        logger.info("has a connect......");
        //TODO:添加上线逻辑
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception{
        if(evt instanceof IdleStateEvent){
            IdleStateEvent ide = (IdleStateEvent) evt;
            if(ide.state() == IdleState.READER_IDLE){
                //TODO:添加超时逻辑:发送ping消息
                //BaseProtocol back = new BaseProtocol();
                //ctx.writeAndFlush(back).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        super.userEventTriggered(ctx,evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseProtocol o) throws Exception {
        logger.info("收到客户端消息:={}",o.getContent());
        //ChannelMap.put(o.getDeviceSerial(),(NioSocketChannel)ctx.channel());
        String deviceSerial = o.getDeviceSerial();
        String usage = o.getUsage().toUpperCase();
        //首次通信后根据消息包内容创建缓存空间
        if(!isInit){
            SystemCache.addDevice(deviceSerial, usage, (NioSocketChannel) ctx.channel());
        }
        BaseDeal handle = SystemManager.getDeal(usage);
        String result = handle.deal(o.getContent());
        //ctx.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
        ctx.close();
        logger.error("异常信息:",cause);
    }
}
