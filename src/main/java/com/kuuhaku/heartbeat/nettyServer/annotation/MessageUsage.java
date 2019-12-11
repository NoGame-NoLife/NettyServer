package com.kuuhaku.heartbeat.nettyServer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
* @Author Kuuhaku
* @Date 2019/11/27 1:22
* @Description 用于标注需要用于解析消息对象的类
**/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MessageUsage {
    String usage() default "Base";
}
