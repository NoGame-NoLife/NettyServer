package com.kuuhaku.heartbeat.monitor;

import com.kuuhaku.heartbeat.nettyServer.channelMap.ChannelMap;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id="monitor")
public class MonitorEndpoint {

    @ReadOperation
    public Map<String,String> countMonitor(){
        Map<String,String> countMap = new HashMap<>();
        countMap.put("name","countMap");
        countMap.put("count", ChannelMap.getPool().entrySet().size()+"");
        return countMap;
    }


}
