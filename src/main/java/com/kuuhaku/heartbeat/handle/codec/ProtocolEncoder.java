package com.kuuhaku.heartbeat.handle.codec;

import com.google.gson.Gson;
import com.kuuhaku.heartbeat.protocol.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author Kuuhaku
 * @Date $(DATE) $(TIME)
 * @Description TODO
 */
public class ProtocolEncoder extends MessageToByteEncoder<CustomProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CustomProtocol customProtocol, ByteBuf out) throws Exception {
        Gson gson = new Gson();
        out.writeBytes(gson.toJson(customProtocol).getBytes());
    }
}
