package com.kuuhaku.heartbeat.cache;

import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author Kuuhaku
 * @Date 2019/12/15 17:13
 * @Description 缓存系统查询所需
 */
@Component
public class SystemCache {
    private static HashMap<String,CacheObject> cache  = new HashMap<>();

    public static void addDevice(String deviceSerial, String usage, NioSocketChannel channel) {
        if (cache.containsKey(deviceSerial)) {
            //填装通道信息
            cache.get(deviceSerial).getChannelMap().put(usage, channel);
        } else {
            //首次进入,填装信息
            cache.put(deviceSerial, new CacheObject(deviceSerial, usage, channel));
        }
    }
    //填装usage对应的缓存信息
    public static void addCache(String deviceSerial, String usage, String content){
        if(cache.containsKey(deviceSerial)){
            cache.get(deviceSerial).getCache().put(usage,content);
        }
    }

    //获取指定的channel
    public static NioSocketChannel getChannel(String deviceSerial, String usage){
        if(!cache.containsKey(deviceSerial)){return null;}
        if(!cache.get(deviceSerial).getChannelMap().containsKey(usage)){return null;}
        return cache.get(deviceSerial).getChannelMap().get(usage);
    }

}
