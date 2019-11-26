package com.kuuhaku.heartbeat.util;

import com.kuuhaku.heartbeat.annotation.MessageUsage;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Kuuhaku
 * @Date 2019/11/27 0:58
 * @Description 管理所有的消息类型,提供根据消息类别名useage(String)获取消息类class的服务
 */
@Component
public class MsgTypeManager<T> extends ApplicationObjectSupport {
    private Map<String, Class<T>> clazzMap = new HashMap<>();
    //private ApplicationContext applicationContext;

    @PostConstruct
    private void init(){
        ApplicationContext applicationContext = super.getApplicationContext();
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(MessageUsage.class);
        System.out.println("count----->"+beanMap.size());
        beanMap.forEach((name,bean)->{
            System.out.println("bean.name--->"+name);
            String annotation = bean.getClass().getAnnotation(MessageUsage.class).usage();
            System.out.println("annotation-"+annotation);
        });
    }



    //@Override
    //public void setApplicationContext(ApplicationContext context) throws BeansException {
     //   this.applicationContext = context;
        /*//利用spring获取被target标记的所有类
            Map<String, Object> beanMap = context.getBeansWithAnnotation(MessageUsage.class);
            System.out.println("count----->"+beanMap.size());
            beanMap.forEach((name,bean)->{
                System.out.println("bean.name--->"+name);
                String annotation = bean.getClass().getAnnotation(MessageUsage.class).usage();
                System.out.println("annotation-"+annotation);
            });*/
    //}
}
