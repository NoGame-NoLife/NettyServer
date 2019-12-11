package com.kuuhaku.heartbeat.nettyServer.handle.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author Kuuhaku
 * @Date $(DATE) $(TIME)
 * @Description TODO
 */
public class ProtocolEncoder extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, String response, ByteBuf out) throws Exception {
        byte[] contentArray = response.getBytes();
        byte[] lengthArray = intToByteArray(contentArray.length);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(lengthArray);
        buffer.writeBytes(contentArray);
        byte[] msg = new byte[buffer.readableBytes()];
        out.writeBytes(msg);
    }

    public static byte[] intToByteArray(int i) {
        byte[] result = new byte[4];
        result[0] = (byte)((i >> 24) & 0xFF);
        result[1] = (byte)((i >> 16) & 0xFF);
        result[2] = (byte)((i >> 8) & 0xFF);
        result[3] = (byte)(i & 0xFF);
        return result;
    }
}
