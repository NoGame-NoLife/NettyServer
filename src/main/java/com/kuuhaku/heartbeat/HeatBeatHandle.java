package com.kuuhaku.heartbeat;

import com.kuuhaku.heartbeat.channelMap.HeartBeatMap;
import com.kuuhaku.heartbeat.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.FutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HeatBeatHandle extends SimpleChannelInboundHandler<CustomProtocol> {
    private final static Logger logger = LoggerFactory.getLogger(HeatBeatHandle.class);

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("outoutoutoutoutoutoutout");
        HeartBeatMap.remove((NioSocketChannel) ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        logger.info("has a connect......");
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception{
        logger.info("好久没来消息了23333");
        if(evt instanceof IdleStateEvent){
            IdleStateEvent ide = (IdleStateEvent) evt;
            if(ide.state() == IdleState.READER_IDLE){
                logger.info("好久没来消息了23333");

                CustomProtocol back = new CustomProtocol(0L,"pong");
                ByteBuf msg = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(back.toString(), CharsetUtil.UTF_8));
                ctx.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
        super.userEventTriggered(ctx,evt);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomProtocol o) throws Exception {
        logger.info("收到客户端消息:={}",o.getContent());
        HeartBeatMap.put(o.getId(),(NioSocketChannel)ctx.channel());
    }
}
