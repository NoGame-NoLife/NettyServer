package com.kuuhaku.heartbeat.util;

import com.kuuhaku.heartbeat.annotation.MessageUsage;
import com.kuuhaku.heartbeat.handle.BaseDeal;
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
public class MsgTypeManager extends ApplicationObjectSupport {
    private static Map<String, Class> clazzMap = new HashMap<>();
    //private ApplicationContext applicationContext;
    private static Map<String, BaseDeal> dealMap = new HashMap<>();

    @PostConstruct
    private void init() throws IllegalAccessException, InstantiationException {
        ApplicationContext applicationContext = super.getApplicationContext();
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(MessageUsage.class);
        System.out.println("count----->"+beanMap.size());
        beanMap.getClass().newInstance();
        beanMap.forEach((name,bean)->{
            System.out.println("load class --->"+name);
            String usage = bean.getClass().getAnnotation(MessageUsage.class).usage();
            clazzMap.put(usage, bean.getClass());
            //获取对应deal实现类
            dealMap.put(usage,(BaseDeal) applicationContext.getBean(usage+"Deal"));
        });
    }

    public static BaseDeal getDeal(String usage){
        return dealMap.get(usage);
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
