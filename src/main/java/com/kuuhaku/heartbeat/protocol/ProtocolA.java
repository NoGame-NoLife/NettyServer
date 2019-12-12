package com.kuuhaku.heartbeat.protocol;

import com.kuuhaku.heartbeat.nettyServer.annotation.MessageUsage;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author Kuuhaku
 * @Date 2019/11/27 1:05
 * @Description TODO
 */
//@Component
//@MessageUsage(usage="Heart")
public class ProtocolA{
    private String name;
    private String test2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    @Override
    public String toString(){
        return "name:"+name+"___test2:"+test2;
    }
}
