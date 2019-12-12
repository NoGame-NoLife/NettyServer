package com.kuuhaku.heartbeat.monitor;

import com.kuuhaku.heartbeat.nettyServer.channelMap.ChannelMap;
import com.kuuhaku.heartbeat.service.HeartBeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.HashMap;
import java.util.Map;

@Endpoint(id="monitor")
public class MonitorEndpoint {
    @Autowired
    private HeartBeatService heartBeatService;

    @ReadOperation
    public Map<String,String> countMonitor(){
        //System.out.println("---------"+heartBeatService.count());
        Map<String,String> countMap = new HashMap<>();
        countMap.put("name","countMap");
        countMap.put("count", ChannelMap.getPool().entrySet().size()+"");
        return countMap;
    }


}
