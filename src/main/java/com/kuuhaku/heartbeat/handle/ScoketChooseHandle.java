package com.kuuhaku.heartbeat.handle;

import com.kuuhaku.heartbeat.handle.heartbeat.HeartBeatHandle;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.boot.SpringApplication;

import java.util.List;

/**
 * @Description 协议初始化解码器,判定当前连接使用什么解码器(tcpSocket/tcpSocket)
 * @Author Kuuhaku
 * @DATE 2019/11/18 9:07
**/
public class ScoketChooseHandle extends ByteToMessageDecoder {
    //默认编码长度为23
    private static final int MAX_LENGTH = 23;
    //websocket握手的协议前缀:"GET /"
    private static final String WEBSOCKET_PREFIX = "GET /";
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String protocol = getPrefix(in);
        if(protocol.startsWith(WEBSOCKET_PREFIX)){
            //websocket请求协议类型
            //SpringApplicationholder.getSpringBeanForClass(PipelineAdd.class)
            ctx.pipeline().remove(LengthFieldBasedFrameDecoder.class);
            ctx.pipeline().remove(LengthFieldPrepender.class);
            ctx.pipeline().remove(HeartBeatHandle.class);
        }

    }

    private String getPrefix(ByteBuf in){
        int length = in.readableBytes();
        if(length > MAX_LENGTH){
            length = MAX_LENGTH;
        }
        in.markReaderIndex();
        byte[] content = new byte[length];
        in.readBytes(content);
        return new String(content);
    }

    public void webSocketAdd(ChannelHandlerContext ctx){
        //将请求和应答消息转化为http消息
        ctx.pipeline().addBefore("byteToBuf","http-codec",new HttpServerCodec());
        //将http消息的多个部分合并成一个整体
        ctx.pipeline().addBefore("byteToBuf","agreegator",new HttpObjectAggregator(65535));
        //想客户端发送http5文件
        ctx.pipeline().addBefore("byteTobuf","http-chunked",new ChunkedWriteHandler());
        ctx.pipeline().addBefore("byteToBuf","WebSocketAggregator",new WebSocketFrameAggregator(65535));

    }
}
