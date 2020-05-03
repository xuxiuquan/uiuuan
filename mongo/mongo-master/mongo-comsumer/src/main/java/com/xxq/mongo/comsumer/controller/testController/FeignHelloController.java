package com.xxq.mongo.comsumer.controller.testController;

import com.xxq.mongo.comsumer.service.MongoProducerService.MongoProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 23:59 2020/5/2
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@RestController
public class FeignHelloController {

    @Autowired
    MongoProducerService mongoProducerService;

    @RequestMapping("/feign/call")
    public String hello(){
        System.out.println("声明式服务调用");
        return mongoProducerService.hello();
    }
}
