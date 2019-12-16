package com.kuuhaku.heartbeat.entity;

import com.kuuhaku.heartbeat.nettyServer.annotation.MessageUsage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description telnet链接信息确认或用于操作员远程telnet指令确认
 *
 * @Author Kuuhaku
 * @Date 2019/12/16 9:22
 **/
@Component
@MessageUsage(usage = "Telnet")
public class Telnet {
    public static final String MARK = "telnet";
    public static final byte[] Telnet_PreFix = "teln".getBytes();

    private boolean isOperator = false;
    private String time;
    private byte[] order;

    public byte[] getOrder() {
        return order;
    }

    public void setOrder(byte[] order) {
        this.order = order;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isOperator() {
        return isOperator;
    }

    public void setOperator(boolean operator) {
        isOperator = operator;
    }

}
