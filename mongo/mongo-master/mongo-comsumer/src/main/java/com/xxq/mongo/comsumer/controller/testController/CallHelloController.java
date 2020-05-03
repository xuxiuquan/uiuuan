package com.xxq.mongo.comsumer.controller.testController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 23:05 2020/5/2
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@RestController
public class CallHelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/call")
    public Object call(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("mongo-producer");
        System.out.println("服务地址"+serviceInstance.getUri().toString()+"/hello");
        System.out.println("服务名称"+serviceInstance.getServiceId());

        String callSericeResult = new RestTemplate().getForObject(serviceInstance.getUri().toString()+"/hello",String.class);
        System.out.println(callSericeResult);
        return callSericeResult;
    }
}
