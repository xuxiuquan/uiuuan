package com.xxq.mongo.comsumer.controller.testController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 0:04 2020/5/5
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@RestController
@RefreshScope
public class TestConfigController {
    @Value("${consumer.hello}")
    private String hello;

    @GetMapping("/hello")
    public String from(){
        return this.hello;
    }
}
