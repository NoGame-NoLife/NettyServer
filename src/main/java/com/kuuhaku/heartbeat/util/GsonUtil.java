package com.kuuhaku.heartbeat.util;

import com.google.gson.Gson;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;


/**
 * @Author Kuuhaku
 * @Date 2019/11/26 23:25
 * @Description TODO
 */
@Component
public class GsonUtil{

    private Gson gson;


    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }

    public String getName(){
        return this.getClass().getName();
    }
}
