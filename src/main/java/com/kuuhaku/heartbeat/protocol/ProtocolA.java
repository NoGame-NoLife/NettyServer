package com.kuuhaku.heartbeat.protocol;

import com.kuuhaku.heartbeat.annotation.MessageUsage;
import org.springframework.stereotype.Component;

import java.lang.annotation.Target;

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
