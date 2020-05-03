package com.xxq.mongo.producer.controller.testController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 14:56 2020/5/2
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@RestController
public class HelloCtroller {
    @GetMapping("/hello")
    private String hello2(){
        return "hello word2";
    }
}
