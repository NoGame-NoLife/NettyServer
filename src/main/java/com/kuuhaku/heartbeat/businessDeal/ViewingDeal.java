package com.kuuhaku.heartbeat.businessDeal;

import com.kuuhaku.heartbeat.entity.Viewing;
import com.kuuhaku.heartbeat.nettyServer.handle.BaseDeal;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author Kuuhaku
 * @Date 2019/12/13 14:55
 **/
@Service("ViewingDeal")
public class ViewingDeal implements BaseDeal<Viewing> {
    @Override
    public String deal(List<Viewing> data) {
        System.out.println("--------viewing数据:"+data.size());
        return null;
    }
}
