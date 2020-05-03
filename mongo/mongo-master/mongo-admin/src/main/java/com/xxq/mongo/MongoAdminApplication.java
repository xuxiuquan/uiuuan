package com.xxq.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"com.xxq"})
@EnableDiscoveryClient
public class MongoAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(MongoAdminApplication.class, args);
	}

}
