package com.xxq.mongo.util;

import com.xxq.mongo.security.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 17:53 2020/4/19
 * @ Description：获取令牌进行验证
 * @ Modified By：
 * @Version : 1.0
 */
public class SecurityUtils {

    public static Authentication getAuthentication() {
        if(SecurityContextHolder.getContext() == null){
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

    /**
     * 获取令牌进行认证
     * @param request
     */
    public static void checkAuthentication(HttpServletRequest request) {
        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public static JwtAuthenticationToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager){
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(username,password);
        jwtAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        //执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);
        //认证成功，存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成令牌并返回给客户端
        jwtAuthenticationToken.setToken(JwtTokenUtils.generateToken(authentication));
        return jwtAuthenticationToken;
    }

    /**
     * 获取当前用户名
     * @return
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取用户名
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }
}
