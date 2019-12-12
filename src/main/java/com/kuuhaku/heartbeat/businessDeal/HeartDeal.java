package com.kuuhaku.heartbeat.businessDeal;

import com.google.gson.Gson;
import com.kuuhaku.heartbeat.entity.HeartBeat;
import com.kuuhaku.heartbeat.nettyServer.handle.BaseDeal;
import com.kuuhaku.heartbeat.protocol.Pb;
import com.kuuhaku.heartbeat.protocol.ProtocolA;
import com.kuuhaku.heartbeat.protocol.ProtocolB;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Kuuhaku
 * @Date 2019/12/10 23:55
 * @Description 心跳业务处理模块
 */
@Service("HeartDeal")
public class HeartDeal implements BaseDeal<HeartBeat> {
    private final static String USAGE = "Heart";
    @Override
    public String deal(List<HeartBeat> data) {
//        Gson gson = new Gson();
//        ProtocolA a = gson.fromJson(data, ProtocolA.class);
        for(HeartBeat i : data){
            System.out.println(i.toString());
        }
        return "hello this is heart deal handler";
    }
}
