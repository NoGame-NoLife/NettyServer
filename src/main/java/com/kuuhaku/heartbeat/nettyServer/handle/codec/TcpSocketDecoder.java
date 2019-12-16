package com.kuuhaku.heartbeat.nettyServer.handle.codec;

import com.kuuhaku.heartbeat.entity.Telnet;
import com.kuuhaku.heartbeat.nettyServer.handle.BaseProtocol;
import com.kuuhaku.heartbeat.util.MsgConvertor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.ArrayList;
import java.util.List;

public class TcpSocketDecoder extends ByteToMessageDecoder {


    private static final byte[] telnet_header = new byte['t'];
    private static final byte Retrun_Character = '\n';

    private List<Byte> orderBytes = new ArrayList<>();
    private boolean orderFlag = true;
    private boolean select_device_flag = true;


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        if(orderFlag){
            byte[] order_check = new byte[1];
            in.readBytes(order_check);
            //重置书写标记
            in.resetReaderIndex();
            //判断为telnet命令
            if(telnet_header.equals(order_check)){

            }else{
                //标记orderFlag,不再进行命令检查,直到下次标记重置
                orderFlag = false;
                return;
            }


            //判断是否是指定设备的命令语句
            if(select_device_flag){
                //此处先采集到整段指令再继续处理
                String order = null;
                int slice_lenth = in.readableBytes();
                byte[] order_slice = new byte[slice_lenth];
                for(int i=0; i<slice_lenth; i++){
                    if(order_slice[i] != Retrun_Character){
                        orderBytes.add(order_slice[i]);
                    }else{
                        //开始获取完整命令
                        byte[] orderByteArray = new byte[orderBytes.size()];
                        for(int index=0; index<orderBytes.size(); index++){
                            orderByteArray[index] = orderBytes.get(index);
                        }
                        order = new String(orderByteArray);
                        break;
                    }
                }


            }else{
                //命令互传
            }




        }








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

    //根据命令中的deviceSerial,将自身channel和对应设备channel互相映射
    public void connectBoth(String order){

    }

}
