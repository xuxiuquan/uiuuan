package com.xxq.mongo.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 13:16 2020/4/25
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.xxq.mongo.backup"})
@EnableDiscoveryClient
public class MongoBackupApplication {
    public static void main(String[] args) {
        SpringApplication.run(MongoBackupApplication.class,args);
    }
}
