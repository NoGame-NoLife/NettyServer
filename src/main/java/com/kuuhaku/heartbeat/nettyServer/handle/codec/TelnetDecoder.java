package com.kuuhaku.heartbeat.nettyServer.handle.codec;

import com.kuuhaku.heartbeat.util.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.AttributeKey;

import java.util.List;

/**
 * @Description TODO
 * @Author Kuuhaku
 * @Date 2019/12/16 15:45
 **/
public class TelnetDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> list) throws Exception {
        Channel nextChannel = ctx.channel().attr(Constants.Next_Channel).get();
        nextChannel.writeAndFlush(in);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        //TODO:关闭连接
        System.out.println("断开链接....");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        //TODO:异常捕获,记录异常
        ctx.close();
    }
}
