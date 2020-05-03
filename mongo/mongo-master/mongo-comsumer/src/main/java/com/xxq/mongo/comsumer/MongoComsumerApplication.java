package com.xxq.mongo.comsumer;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Servlet;

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

	/**
	 * 添加服务监控路径，与服务容错本身无关
	 * @return
	 */
	@Bean
	public ServletRegistrationBean getServlet(){
		HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>(hystrixMetricsStreamServlet);
		servletServletRegistrationBean.setLoadOnStartup(1);
		servletServletRegistrationBean.addUrlMappings("/hystrix.stream");
		servletServletRegistrationBean.setName("HystrixMetricsStreamServlet");
		return servletServletRegistrationBean;
	}

}
