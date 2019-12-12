package com.kuuhaku.heartbeat.nettyServer.handle;

import java.util.List;

public interface BaseDeal<T> {
    public String deal(List<T> data);
}
