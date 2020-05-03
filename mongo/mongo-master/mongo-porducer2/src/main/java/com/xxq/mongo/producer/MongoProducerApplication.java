package com.xxq.mongo.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MongoProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoProducerApplication.class, args);
	}

}
