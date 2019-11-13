package com.kuuhaku.heartbeat.channelMap;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeartBeatMap {
    private static HeartBeatMap instence = new HeartBeatMap();

    private static Map<Long, NioSocketChannel> heartBeatMap = new ConcurrentHashMap<>();

    public static Map<Long, NioSocketChannel> get() {
        return heartBeatMap;
    }

    public static void set(Map<Long, NioSocketChannel> heartBeatMap) {
        HeartBeatMap.heartBeatMap = heartBeatMap;
    }

    public static void put(Long id, NioSocketChannel ch){
        heartBeatMap.put(id, ch);
    }

    public static void remove(NioSocketChannel ch){
        heartBeatMap.entrySet().stream().filter(entry ->entry.getValue() == ch).forEach(entry->heartBeatMap.remove(entry.getKey()));
    }
}
