package com.kuuhaku.heartbeat.handle.webSocket;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author Kuuhaku
 * @Date $(DATE) $(TIME)
 * @Description TODO
 */
@Component
@ChannelHandler.Sharable
public class WebSocketPackHandle extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static Logger logger = LoggerFactory.getLogger(WebSocketPackHandle.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        //判断是否为关闭链路的命令
        if(frame instanceof CloseWebSocketFrame){
            ctx.close();
            return;
        }
        //判断是否为ping消息
        if(frame instanceof PingWebSocketFrame){
            ctx.channel().write(new PongWebSocketFrame(frame.content()));
            return;
        }
        //判断是否为文字消息
        if(frame instanceof TextWebSocketFrame){
            String msg = ((TextWebSocketFrame)frame).text();
            //采用空格ping消息
            if(" ".equals(msg)){
                return;
            }
            logger.info("暂时不支持文字消息,先转成byte传输!");
            return;
        }
        if(frame instanceof BinaryWebSocketFrame){
            byte[] contentBytes = new byte[frame.content().readableBytes()];
            frame.content().readBytes(contentBytes);
            ctx.fireChannelRead(contentBytes);
        }
    }
}
