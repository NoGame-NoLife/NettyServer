package com.kuuhaku.heartbeat.nettyServer.handle.codec;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.kuuhaku.heartbeat.protocol.BaseProtocol;
import com.kuuhaku.heartbeat.protocol.Pa;
import com.kuuhaku.heartbeat.util.MsgConvertor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.lang.reflect.Type;
import java.util.List;

public class TcpSocketDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();

        byte[] lengthArray = new byte[4];
        in.readBytes(lengthArray);
        int length = byteArrayToInt(lengthArray);
        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }
        byte[] bytes = new byte[length] ;
        in.readBytes(bytes) ;
        String content = new String(bytes) ;
        System.out.println("---------------------------------------------------------------");
        System.out.println(content);
        System.out.println("---------------------------------------------------------------");
        //Gson gson = new Gson();
        BaseProtocol base = MsgConvertor.fromJson(content);
        out.add(base) ;
    }
    /**
     * @Description 字节数组转换int
     * @Returns
     */
    public static int byteArrayToInt( byte[] bytes ){
        //只支持四个字节
        if( bytes.length != 4 ) return 0;

        //(低位在前，高位在后)
        int value = (int) ((bytes[0] & 0xFF)
                | ((bytes[1] & 0xFF)<<8)
                | ((bytes[2] & 0xFF)<<16)
                | ((bytes[3] & 0xFF)<<24));
        return value;
    }
}
