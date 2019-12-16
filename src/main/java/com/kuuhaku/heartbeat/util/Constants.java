package com.kuuhaku.heartbeat.util;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

public interface Constants {
    public static final AttributeKey<Channel> Next_Channel =AttributeKey.newInstance("next_Channel");
}
