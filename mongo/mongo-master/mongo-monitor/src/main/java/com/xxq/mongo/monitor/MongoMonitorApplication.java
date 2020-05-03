package com.xxq.mongo.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 20:49 2020/4/29
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@EnableAdminServer
@SpringBootApplication
@EnableDiscoveryClient
public class MongoMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoMonitorApplication.class,args);
    }
}
