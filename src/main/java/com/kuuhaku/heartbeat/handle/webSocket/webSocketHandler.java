package com.kuuhaku.heartbeat.handle.webSocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public class webSocketHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.print("get in websocket handler!");
        ctx.channel().writeAndFlush(new TextWebSocketFrame("get web"));
    }


}
