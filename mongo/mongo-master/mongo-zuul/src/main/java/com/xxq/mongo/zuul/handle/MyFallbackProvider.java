package com.xxq.mongo.zuul.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 14:51 2020/5/4
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@Slf4j
@Component
public class MyFallbackProvider implements FallbackProvider {
    /**
     * 若所有服务都需要降级，则返回*
     * @return 需要降级的服务ID
     */
    @Override
    public String getRoute() {
        return "mongo-consumer";
    }

    /**
     * 熔断时的反馈信息
     * @param route
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.info("route"+route);
        log.info("exception"+cause.getMessage());
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() {
                return 200;
            }

            @Override
            public String getStatusText() {
                return "ok";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() {
                return new ByteArrayInputStream("sorry, the service is unavailable now".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                return httpHeaders;
            }
        };
    }
}
