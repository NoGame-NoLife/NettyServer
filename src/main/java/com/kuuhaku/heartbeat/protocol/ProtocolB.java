package com.kuuhaku.heartbeat.protocol;

import com.kuuhaku.heartbeat.annotation.MessageUsage;
import org.springframework.stereotype.Component;

/**
 * @Author Kuuhaku
 * @Date 2019/11/27 1:05
 * @Description TODO
 */
@Component
@MessageUsage(usage="Data")
public class ProtocolB extends BaseProtocol{
    private String k;

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }
}
