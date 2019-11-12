package com.kuuhaku.heartbeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeatBeatHandle extends SimpleChannelInboundHandler {
    private final static Logger logger = LoggerFactory.getLogger(HeatBeatHandle.class);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}
