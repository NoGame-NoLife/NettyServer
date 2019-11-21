package com.kuuhaku.heartbeat.handle;

import com.kuuhaku.heartbeat.handle.tcpSocket.HeartBeatHandle;
import com.kuuhaku.heartbeat.handle.webSocket.WebSocketPackHandle;
import com.kuuhaku.heartbeat.handle.webSocket.webSocketHandler;
import com.kuuhaku.heartbeat.protocol.TcpSocketDecoder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.stream.ChunkedWriteHandler;

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
            System.out.println("dddddddd");
            webSocketHandleAdd(ctx);
            ctx.pipeline().remove(TcpSocketDecoder.class);
            ctx.pipeline().remove(HeartBeatHandle.class);
        }
        in.resetReaderIndex();
        ctx.pipeline().remove(this.getClass());
    }

    /**
    * @Author Kuuhaku
    * @Date 2019/11/18 23:21
    * @Description 获取请求头前缀
    **/
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

    public void webSocketHandleAdd(ChannelHandlerContext ctx){
        //将请求和应答消息转化为http消息
        ctx.pipeline().addBefore("tcpDecoder","http-codec",new HttpServerCodec());
        //将http消息的多个部分合并成一个整体
        ctx.pipeline().addBefore("tcpDecoder","agreegator",new HttpObjectAggregator(65535));
        //向客户端发送http5文件
        ctx.pipeline().addBefore("tcpDecoder","http-chunked",new ChunkedWriteHandler());
        ctx.pipeline().addBefore("tcpDecoder","WebSocketAggregator",new WebSocketFrameAggregator(65535));
        //添加websocket处理器处理每条连接的两种请求(http建立连接,后续的为socket)
        ctx.pipeline().addBefore("tcpDecoder","webSocketHandle",new webSocketHandler());
        ctx.pipeline().addBefore("tcpDecoder", "webSocketPackHandle", new WebSocketPackHandle());
    }
}
