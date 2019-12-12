package com.kuuhaku.heartbeat.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kuuhaku.heartbeat.protocol.BaseProtocol;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author Kuuhaku
 * @Date 2019/12/12 13:24
 **/
public class MsgConvertor {
    private static final String USAGEKEY = "usage";
    private static final String CONTENTKEY = "content";
    private static final String DEVICESERIALKEY = "deviceSerial";
    private static final String TIMEKEY = "time";

    public static BaseProtocol fromJson(String msg){
        Gson gson = new Gson();
        BaseProtocol result = new BaseProtocol();
        List contentList = new ArrayList();

        JsonParser jsp = new JsonParser();
        JsonElement jse = jsp.parse(msg);
        JsonObject jso = jse.getAsJsonObject();
        if(!jso.has(USAGEKEY)){
            return null;
        }
        String usage = jso.get(USAGEKEY).getAsString();
        //获取消息内容类型
        Class contentType = MsgTypeManager.getClazz(usage.toUpperCase());
        JsonElement content = jso.get(CONTENTKEY);
        for(JsonElement single:content.getAsJsonArray()){
            contentList.add(gson.fromJson(single,contentType));
        }
        //填装其他属性
        result.setContent(contentList);
        result.setDeviceSerial(jso.get(DEVICESERIALKEY).getAsString());
        if(jso.has(TIMEKEY)){
            result.setTime(jso.get(TIMEKEY).getAsString());
        }
        result.setUsage(usage);
        return result;
    }

}

