package com.kuuhaku.heartbeat.businessDeal;

import com.google.gson.Gson;
import com.kuuhaku.heartbeat.nettyServer.handle.BaseDeal;
import com.kuuhaku.heartbeat.protocol.ProtocolA;
import org.springframework.stereotype.Service;

/**
 * @Author Kuuhaku
 * @Date 2019/12/10 23:55
 * @Description TODO
 */
@Service("HeartDeal")
public class HeartDeal implements BaseDeal {
    private final static String USAGE = "Heart";
    @Override
    public String deal(String data) {
        Gson gson = new Gson();
        ProtocolA a = gson.fromJson(data, ProtocolA.class);
        return "hello this is heart deal handler"+data;
    }
}
