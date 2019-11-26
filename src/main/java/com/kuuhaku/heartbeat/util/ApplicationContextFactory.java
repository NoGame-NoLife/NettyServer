package com.kuuhaku.heartbeat.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * @Author Kuuhaku
 * @Date 2019/11/27 2:24
 * @Description TODO
 */
public class ApplicationContextFactory {
    private static ApplicationContext applicationContext = null;
    public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
}
