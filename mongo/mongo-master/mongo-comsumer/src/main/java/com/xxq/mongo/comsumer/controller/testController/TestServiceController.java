package com.xxq.mongo.comsumer.controller.testController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 22:56 2020/5/2
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@RestController
public class TestServiceController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/service")
    public Object getAllService(){
        return discoveryClient.getInstances("mongo-producer");
    }

    @GetMapping("/discover")
    public Object discover(){
        return loadBalancerClient.choose("mongo-producer").getUri().toString();
    }
}
