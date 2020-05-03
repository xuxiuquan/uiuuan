package com.xxq.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 23:38 2020/4/11
 * @ Description：swagger2接口文档配置类
 * @ Modified By：
 * @Version    :1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        //添加请求头参数，token
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<>();
        parameterBuilder.name("token").description("令牌").modelRef(new ModelRef("String")).parameterType("header").required(false).build();
        parameters.add(parameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters)
                ;
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("权限管理系统-maogo")
                .description("")
                .description("powered by XXQ")
                .termsOfServiceUrl("http://www.by-health.com/")
                .contact("386906422@qq.com")
                .version("1.0")
                .build();
    }
}
