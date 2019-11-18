package com.kuuhaku.heartbeat.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class TcpSocketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        long id = in.readLong();
        byte[] bytes = new byte[in.readableBytes()];
        in.readBytes(bytes);
        String content = new String(bytes);
        CustomProtocol cus = new CustomProtocol();
        cus.setId(id);
        cus.setContent(content);
        out.add(cus);
    }
}
