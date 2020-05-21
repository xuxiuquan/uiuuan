package com.xxq.mongo.config;

import com.xxq.mongo.security.JwtAuthenticationFilter;
import com.xxq.mongo.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 17:29 2020/4/19
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@Configuration
@EnableWebSecurity//开启Spring Security
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启权限注解,如@PreAuthorize
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf，由于使用的是JWT，我们不需要csrf
        http.cors().and().csrf().disable().authorizeRequests()
                //以下请求配置，拦截器仍然会拦截
                //跨域预检请求
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                //web jar
                .antMatchers("/webjars/**").permitAll()
                //查看sql监控（druid）
                .antMatchers("/druid/**").permitAll()
                //主页、登录
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                //swagger
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                //验证码
                .antMatchers("/captcha.jpg**").permitAll()
                //服务监控
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated();
        //退出登录处理器
        //todo
        //http.logout().logoutSuccessHandler();
        //token验证过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()),UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //配置后拦截器不对此请求进行拦截
        web.ignoring().antMatchers("/actuator/**");
        //web.ignoring().antMatchers("/login/**");
    }
}
