package com.xxq.mongo.comsumer.service.MongoProducerService;

import com.xxq.mongo.comsumer.hystrix.MongoProducerHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 23:56 2020/5/2
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@FeignClient(name = "mongo-producer",fallback = MongoProducerHystrix.class)
public interface MongoProducerService {

    /**
     * 声明与要调用的目标方法一致的方法，定义需保持一致
     * @return
     */
    @RequestMapping("/hello")
    String hello();
}
