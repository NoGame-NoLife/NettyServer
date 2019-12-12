package com.kuuhaku.heartbeat.businessDeal;

import com.kuuhaku.heartbeat.nettyServer.handle.BaseDeal;
import com.kuuhaku.heartbeat.protocol.Pa;
import com.kuuhaku.heartbeat.protocol.ProtocolA;
import com.kuuhaku.heartbeat.protocol.ProtocolB;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Kuuhaku
 * @Date 2019/12/10 23:57
 * @Description TODO
 */
@Service("DataDeal")
public class DataDeal implements BaseDeal<ProtocolB> {
    @Override
    public String deal(List<ProtocolB> data) {
        for(ProtocolB i : data){
            System.out.println(i.toString());
        }
        return "this is data deal handler:";
    }

}
