package com.xxq.mongo.comsumer.hystrix;

import com.xxq.mongo.comsumer.service.MongoProducerService.MongoProducerService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 22:44 2020/5/3
 * @ Description：MongoProducerService的熔断器，当服务调用失败，则熔断器打开，调用熔断器方法
 * @ Modified By：
 * @Version : 1.0
 */
@Component
public class MongoProducerHystrix implements MongoProducerService {

    @Override
    @RequestMapping("/hello")
    public String hello() {
        return "sorry,producer service call failed";
    }
}
