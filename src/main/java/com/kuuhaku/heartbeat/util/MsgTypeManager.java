package com.kuuhaku.heartbeat.util;

import com.kuuhaku.heartbeat.nettyServer.annotation.MessageUsage;
import com.kuuhaku.heartbeat.nettyServer.handle.BaseDeal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

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
        beanMap.forEach((name,bean)->{
            String usage = bean.getClass().getAnnotation(MessageUsage.class).usage();
            clazzMap.put(usage.toUpperCase(), bean.getClass());
            //获取对应deal实现类
            dealMap.put(usage.toUpperCase(),(BaseDeal) applicationContext.getBean(usage+"Deal"));
        });
    }

    public static Class getClazz(String usage){
        return clazzMap.get(usage);
    }
    public static BaseDeal getDeal(String usage){
        return dealMap.get(usage);
    }

}
