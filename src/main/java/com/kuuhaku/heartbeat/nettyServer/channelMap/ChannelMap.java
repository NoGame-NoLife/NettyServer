package com.kuuhaku.heartbeat.nettyServer.channelMap;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelMap {
    private static ChannelMap instence = new ChannelMap();

    private static Map<String, NioSocketChannel> Pool = new ConcurrentHashMap<>();

    public static Map<String, NioSocketChannel> getPool() {
        return Pool;
    }

    public static void set(Map<String, NioSocketChannel> heartBeatMap) {
        ChannelMap.Pool = heartBeatMap;
    }

    public static void put(String id, NioSocketChannel ch){
        Pool.put(id, ch);
    }

    public static void remove(NioSocketChannel ch){
        Pool.entrySet().stream().filter(entry ->entry.getValue() == ch).forEach(entry-> Pool.remove(entry.getKey()));
    }
}
