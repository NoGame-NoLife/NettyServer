package com.kuuhaku.heartbeat.util;

import com.kuuhaku.heartbeat.nettyServer.annotation.MessageUsage;
import com.kuuhaku.heartbeat.nettyServer.annotation.ServiceBean;
import com.kuuhaku.heartbeat.nettyServer.handle.BaseDeal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SystemManager extends ApplicationObjectSupport {
    private final static Logger logger = LoggerFactory.getLogger(SystemManager.class);
    private static Map<String, Class> clazzMap = new HashMap<>();
    private static Map<String, BaseDeal> dealMap = new HashMap<>();
    private static Map<Class,Object> serviceBeanMap = new HashMap<>();
    private int count = 0;

    @PostConstruct
    private void init() throws IllegalAccessException, InstantiationException {
        ApplicationContext applicationContext = super.getApplicationContext();
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(MessageUsage.class);
        logger.info("消息类型----开始加载:");
        beanMap.forEach((name,bean)->{
            logger.info("--->"+name);
            String usage = bean.getClass().getAnnotation(MessageUsage.class).usage();
            clazzMap.put(usage.toUpperCase(), bean.getClass());
            //获取对应deal实现类
            dealMap.put(usage.toUpperCase(),(BaseDeal) applicationContext.getBean(usage+"Deal"));
            count++;
        });
        logger.info("消息类型----加载完成("+count+")");
        logger.info("ServiceBean----开始加载:");
        count=0;
        beanMap = applicationContext.getBeansWithAnnotation(ServiceBean.class);
        beanMap.forEach((name,bean)->{
            logger.info("--->"+name);
            Class clazz = bean.getClass();
            serviceBeanMap.put(clazz,bean);
            count++;
        });
        logger.info("ServiceBean----加载完成("+count+")");
    }

    public static Class getClazz(String usage){
        return clazzMap.get(usage);
    }
    public static BaseDeal getDeal(String usage){
        return dealMap.get(usage);
    }
    public static <T>T getService(Class<T> clazz){
        return (T)serviceBeanMap.get(clazz);
    }

}
