package com.kuuhaku.heartbeat.businessDeal;

import com.kuuhaku.heartbeat.handle.BaseDeal;
import org.springframework.stereotype.Service;

/**
 * @Author Kuuhaku
 * @Date 2019/12/10 23:57
 * @Description TODO
 */
@Service("DataDeal")
public class DataDeal implements BaseDeal {
    @Override
    public String deal(String data) {
        return "this is data deal handler:"+data;
    }
}
