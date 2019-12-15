package com.kuuhaku.heartbeat.cache;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Kuuhaku
 * @Date 2019/12/15 17:20
 * @Description 系统所需的缓存信息
 */
public class CacheObject {
    //设备编号
    private String DeviceSerial;
    //缓存各个use对饮的通道:TODO后期每一个设备应该只创建一条通道供数据传输
    private Map<String,NioSocketChannel> channelMap;
    //缓存信息实体
    private Map<String,String> cache;


    public CacheObject(String deviceSerial, String usage, NioSocketChannel channel) {
        DeviceSerial = deviceSerial;
        Map<String, NioSocketChannel> initMap = new HashMap<>();
        initMap.put(usage, channel);
        this.channelMap = initMap;
        this.cache = new HashMap<>();
    }

    public String getDeviceSerial() {
        return DeviceSerial;
    }

    public void setDeviceSerial(String deviceSerial) {
        DeviceSerial = deviceSerial;
    }

    public Map<String, NioSocketChannel> getChannelMap() {
        return channelMap;
    }

    public void setChannelMap(Map<String, NioSocketChannel> channelMap) {
        this.channelMap = channelMap;
    }

    public Map<String, String> getCache() {
        return cache;
    }

    public void setCache(Map<String, String> cache) {
        this.cache = cache;
    }
}
