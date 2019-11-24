package com.kuuhaku.heartbeat.handle.webSocket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class webSocketHandler extends SimpleChannelInboundHandler<Object> {
    private static Logger logger = LoggerFactory.getLogger(webSocketHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.print("get in websocket handler!");
        /**第一次为传统http请求,用于建立连接**/
        if(msg instanceof FullHttpRequest){
            try{
                handleHttpRequest(ctx,(FullHttpRequest)msg);
            }catch (Exception e){
                logger.error("--->",e);
            }
        }
        ctx.pipeline().remove(this.getClass());
    }

    /**
    * @Author Kuuhaku
    * @Date 2019/11/21 23:39
    * @Description TODO
    **/
    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req){
        //如果http解析失败,返回http异常
        if(!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))){
            sendHttpResponse(ctx,req,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //构造握手响应并返回
        String localUrl = "ws://"+req.headers().get(HttpHeaderNames.HOST)+req.uri();
        WebSocketServerHandshakerFactory wsf = new WebSocketServerHandshakerFactory(localUrl,null,false);
        WebSocketServerHandshaker wsh = wsf.newHandshaker(req);
        if(wsh == null){
            logger.info("不支持的连接类型");
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        }else{
            wsh.handshake(ctx.channel(),req);
        }
    }


    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res){
        //返回应答给客户端
        if(res.status().code() != 200){
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
        }
        ChannelFuture f = ctx.writeAndFlush(res);
        if(!HttpHeaders.isKeepAlive(req) || res.status().code() != 200){
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }

}
