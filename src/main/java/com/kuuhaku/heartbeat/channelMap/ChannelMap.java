package com.kuuhaku.heartbeat.channelMap;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelMap {
    private static ChannelMap instence = new ChannelMap();

    private static Map<Long, NioSocketChannel> Pool = new ConcurrentHashMap<>();

    public static Map<Long, NioSocketChannel> getPool() {
        return Pool;
    }

    public static void set(Map<Long, NioSocketChannel> heartBeatMap) {
        ChannelMap.Pool = heartBeatMap;
    }

    public static void put(Long id, NioSocketChannel ch){
        Pool.put(id, ch);
    }

    public static void remove(NioSocketChannel ch){
        Pool.entrySet().stream().filter(entry ->entry.getValue() == ch).forEach(entry-> Pool.remove(entry.getKey()));
    }
}
