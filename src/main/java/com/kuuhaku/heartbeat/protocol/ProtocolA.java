package com.kuuhaku.heartbeat.protocol;

import com.kuuhaku.heartbeat.nettyServer.annotation.MessageUsage;
import org.springframework.stereotype.Component;

/**
 * @Author Kuuhaku
 * @Date 2019/11/27 1:05
 * @Description TODO
 */
@Component
@MessageUsage(usage="Heart")
public class ProtocolA extends BaseProtocol{
    private int k;

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }
}
