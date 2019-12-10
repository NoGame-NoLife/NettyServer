package com.kuuhaku.heartbeat.businessDeal;

import com.kuuhaku.heartbeat.handle.BaseDeal;
import org.springframework.stereotype.Service;

/**
 * @Author Kuuhaku
 * @Date 2019/12/10 23:55
 * @Description TODO
 */
@Service("HeartDeal")
public class HeartDeal implements BaseDeal {
    @Override
    public String deal(String data) {
        return "hello this is heart deal handler"+data;
    }
}
