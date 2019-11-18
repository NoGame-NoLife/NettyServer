package com.kuuhaku.heartbeat.handle.webSocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class webSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println(msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("get..."));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
        System.out.println("link start....");
    }
}
