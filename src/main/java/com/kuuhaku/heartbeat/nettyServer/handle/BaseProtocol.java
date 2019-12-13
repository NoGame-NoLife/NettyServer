package com.kuuhaku.heartbeat.nettyServer.handle;

import java.util.List;

/**
 * @Author Kuuhaku
 * @Date 2019/12/11 0:54
 * @Description TODO
 */
public class BaseProtocol<T> {
    private String deviceSerial;
    private String time;
    private String usage;
    private List<T> content;

    public String getDeviceSerial() {
        return deviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        this.deviceSerial = deviceSerial;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
