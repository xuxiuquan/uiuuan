package com.xxq.mongo.sys.controller;


import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.xxq.mongo.commonBean.LoginBean;
import com.xxq.mongo.core.http.HttpResult;
import com.xxq.mongo.security.JwtAuthenticationToken;
import com.xxq.mongo.sys.entity.User;
import com.xxq.mongo.sys.service.IUserService;
import com.xxq.mongo.util.PasswordUtils;
import com.xxq.mongo.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>
 * 系统操作日志表 前端控制器
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@RestController
@Slf4j
public class LoginController {
    @Autowired
    private Producer captchaProducer;
    @Autowired
    IUserService iUserService;
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 获取登录验证码
     * @param request
     * @param response
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        // create the text for the image
        String capText = captchaProducer.createText();
        log.info(capText);
        // store the text in the session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        // write the data out
        ImageIO.write(bi, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @PostMapping("/login")
    public HttpResult login(@RequestBody LoginBean loginBean, HttpServletRequest httpServletRequest){
        String username = loginBean.getAccount();
        String password = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        //从session中获取生成的验证码，与前台输入的验证码进行匹配
        Object kaptcha = httpServletRequest.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(kaptcha == null){
            return HttpResult.ok("验证码已失效");
        }
        if(!captcha.equals(kaptcha)){
            return HttpResult.ok("验证码不正确");
        }
        //查找用户信息
        User user = iUserService.findByName(username);
        if(user == null){
            return HttpResult.ok("用户不存在");
        }
        if(!PasswordUtils.matches(user.getSalt(),password,user.getPassword())){
            return HttpResult.ok("密码不正确");
        }
        if(user.getStatus()==0){
            return HttpResult.ok("账号已被锁定，请联系管理员");
        }
        JwtAuthenticationToken token = SecurityUtils.login(httpServletRequest,username,password,authenticationManager);
        return HttpResult.ok().data(token);
    }
}
