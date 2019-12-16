package com.kuuhaku.heartbeat.businessDeal;

import com.kuuhaku.heartbeat.entity.Telnet;
import com.kuuhaku.heartbeat.nettyServer.handle.BaseDeal;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author Kuuhaku
 * @Date 2019/12/16 9:25
 **/
@Service("TelnetDeal")
public class TelnetDeal implements BaseDeal<Telnet> {

    @Override
    public String deal(List<Telnet> data) {
        return "OK";
    }
}
