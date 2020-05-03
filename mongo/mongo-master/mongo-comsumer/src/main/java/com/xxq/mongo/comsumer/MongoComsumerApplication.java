package com.xxq.mongo.comsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients//扫描Spring Cloud Feign客户端
@EnableDiscoveryClient
@SpringBootApplication
public class MongoComsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoComsumerApplication.class, args);
	}

	@Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
	    return new RestTemplate();
    }

}
