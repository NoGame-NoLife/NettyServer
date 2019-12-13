package com.kuuhaku.heartbeat.businessDeal;

import com.kuuhaku.heartbeat.entity.HeartBeat;
import com.kuuhaku.heartbeat.nettyServer.handle.BaseDeal;
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
