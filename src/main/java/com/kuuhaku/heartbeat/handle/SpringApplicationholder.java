package com.kuuhaku.heartbeat.handle;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description TODO
 * @Author Kuuhaku
 * @Date 2019/11/18 11:06
 **/
public class SpringApplicationholder implements ApplicationContextAware {
    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringApplicationholder.context = context;
    }

    public static Object getSpringBean(String beanName) {
        return context==null?null:context.getBean(beanName);
    }

    public static <T> T getSpringBeanForClass(Class<T> clazz) {
        return context==null?null:context.getBean(clazz);
    }
}
