package com.kuuhaku.heartbeat;

import com.kuuhaku.heartbeat.server.Server;
import com.kuuhaku.heartbeat.monitor.MonitorEndpoint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnEnabledEndpoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    @Resource
    private Server server;
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        server.run();
    }

    /**
    * @Author Kuuhaku
    * @Date 2019/11/25 20:39
    * @Description 暴露端口供监测
    **/
    @Configuration
    static class MyPointConfiguration{

        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnEnabledEndpoint
        public MonitorEndpoint myMonitorEndPoint(){
            return new MonitorEndpoint();
        }
    }
}
