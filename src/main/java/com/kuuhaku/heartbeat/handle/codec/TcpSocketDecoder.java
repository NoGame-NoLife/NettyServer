package com.kuuhaku.heartbeat.handle.codec;

import com.google.gson.Gson;
import com.kuuhaku.heartbeat.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class TcpSocketDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int length = in.readInt();
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[in.readableBytes()] ;
        in.readBytes(bytes) ;
        String content = new String(bytes) ;
        Gson gson = new Gson();
        CustomProtocol customProtocol = gson.fromJson(content,CustomProtocol.class);
        out.add(customProtocol) ;
    }
}
