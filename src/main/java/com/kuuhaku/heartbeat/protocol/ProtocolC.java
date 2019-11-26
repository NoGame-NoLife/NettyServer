package com.kuuhaku.heartbeat.protocol;

import com.kuuhaku.heartbeat.annotation.MessageUsage;
import org.springframework.stereotype.Component;

/**
 * @Author Kuuhaku
 * @Date 2019/11/27 1:05
 * @Description TODO
 */
@Component
@MessageUsage(usage="p-c")
public class ProtocolC {
    private boolean k;

    public boolean isK() {
        return k;
    }

    public void setK(boolean k) {
        this.k = k;
    }
}
