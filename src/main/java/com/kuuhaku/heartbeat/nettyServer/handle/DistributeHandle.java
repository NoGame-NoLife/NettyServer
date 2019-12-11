package com.kuuhaku.heartbeat.nettyServer.handle;

import com.kuuhaku.heartbeat.nettyServer.channelMap.ChannelMap;
import com.kuuhaku.heartbeat.protocol.BaseProtocol;
import com.kuuhaku.heartbeat.util.MsgTypeManager;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DistributeHandle extends SimpleChannelInboundHandler<BaseProtocol> {
    private final static Logger logger = LoggerFactory.getLogger(DistributeHandle.class);



    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
        ChannelMap.remove((NioSocketChannel) ctx.channel());
        System.out.println(".........lose a connect");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        logger.info("has a connect......");
        //ctx.writeAndFlush(new BaseProtocol()).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception{
        if(evt instanceof IdleStateEvent){
            IdleStateEvent ide = (IdleStateEvent) evt;
            if(ide.state() == IdleState.READER_IDLE){
                BaseProtocol back = new BaseProtocol();
                ctx.writeAndFlush(back).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        super.userEventTriggered(ctx,evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseProtocol o) throws Exception {
        logger.info("收到客户端消息:={}",o.getContent());
        ChannelMap.put(o.getDeviceSerial(),(NioSocketChannel)ctx.channel());
        //BaseDeal handle = MsgTypeManager.getDeal(o.getUsage());
        System.out.println("----------------"+o.getDeviceSerial());
        //String result = handle.deal(o.getContent());
        //ctx.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
        ctx.close();
        logger.error("异常信息:",cause);
    }
}
