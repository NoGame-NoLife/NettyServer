package com.kuuhaku.heartbeat.protocol;

import com.kuuhaku.heartbeat.nettyServer.annotation.MessageUsage;
import org.springframework.stereotype.Component;

/**
 * @Author Kuuhaku
 * @Date 2019/11/27 1:05
 * @Description TODO
 */
//@Component
//@MessageUsage(usage="Data")
public class ProtocolB{
    private String name;
    private String test1;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }
    @Override
    public String toString(){
        return "name:"+name+"___test2:"+test1;
    }
}
