package com.xxq.mongo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 19:28 2020/4/13
 * @ Description：CORS跨域实现
 * @ Modified By：
 * @Version : 1.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许跨域访问的路径
                .addMapping("/**")
                //允许跨域访问的源
                .allowedOrigins("*")
                //允许请求的方法
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
                //预检间隔时间
                .maxAge(168_000)
                //允许头部设置
                .allowedHeaders("*")
                //是否发送cookie
                .allowCredentials(true);
    }
}
