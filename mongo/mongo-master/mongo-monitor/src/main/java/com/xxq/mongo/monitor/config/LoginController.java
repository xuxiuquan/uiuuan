package com.xxq.mongo.monitor.config;

import org.apache.http.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 22:52 2020/5/7
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@RestController
public class LoginController {
    @RequestMapping("/loginView")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("http://localhost:8080/#/login");
    }
}
