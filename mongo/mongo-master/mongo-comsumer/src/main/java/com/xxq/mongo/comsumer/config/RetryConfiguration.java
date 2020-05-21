package com.xxq.mongo.comsumer.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 20:32 2020/5/6
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@Configuration
public class RetryConfiguration {
    @Bean
    @ConditionalOnMissingBean(name = "configServerRetryInterceptor")
    public RetryOperationsInterceptor configServerRetryInterceptor(){
        return RetryInterceptorBuilder.stateless().backOffOptions(1000,1.2,5000).maxAttempts(10).build();
    }
}
