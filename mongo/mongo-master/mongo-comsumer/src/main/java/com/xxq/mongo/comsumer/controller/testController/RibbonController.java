package com.xxq.mongo.comsumer.controller.testController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 23:28 2020/5/2
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@RestController
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/ribbon/call")
    public String call(){
        //调用服务，mongo-producer为服务中心注册的服务名
        //LoadBalancerInterCeptor会拦截调用并根据服务名找到对应的服务器
        String callServiceResult = restTemplate.getForObject("http://mongo-producer/hello",String.class);
        return callServiceResult;
    }

}
