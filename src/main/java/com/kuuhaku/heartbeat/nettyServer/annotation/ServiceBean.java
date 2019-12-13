package com.kuuhaku.heartbeat.nettyServer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 用于标记网络服务中需要使用的service
 * @Author Kuuhaku
 * @DATE 2019/12/13 10:45
**/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceBean {
}
