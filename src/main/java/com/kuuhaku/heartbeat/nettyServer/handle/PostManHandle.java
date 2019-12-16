package com.kuuhaku.heartbeat.nettyServer.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Description TODO
 * @Author Kuuhaku
 * @Date 2019/12/16 17:47
 **/
public class PostManHandle extends SimpleChannelInboundHandler<byte[]> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, byte[] b) throws Exception {

    }
}
