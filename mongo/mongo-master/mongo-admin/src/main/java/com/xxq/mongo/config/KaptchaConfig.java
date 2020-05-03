package com.xxq.mongo.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 23:56 2020/4/17
 * @ Description：验证码配置 （教程网址：https://www.jianshu.com/p/a3525990cd82）
 * @ Modified By：
 * @Version : 1.0
 */
@Configuration
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha producer(){
        Properties properties = new Properties();
        properties.put("kaptcha.border","no");
        properties.put("kaptcha.textproducer.font.color","black");
        properties.put("kaptcha.textproducer.char.space","5");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
