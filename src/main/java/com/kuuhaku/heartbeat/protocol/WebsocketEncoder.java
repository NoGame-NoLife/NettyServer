package com.kuuhaku.heartbeat.protocol;

import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.List;

/**
 * @Author Kuuhaku
 * @Date $(DATE) $(TIME)
 * @Description TODO
 */
public class WebsocketEncoder extends MessageToMessageEncoder<CustomProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CustomProtocol customProtocol, List<Object> list) throws Exception {
        Gson gson = new Gson();
        list.add(new TextWebSocketFrame(gson.toJson(customProtocol)));
    }
}
