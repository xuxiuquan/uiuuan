package com.xxq.mongo.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 20:14 2020/4/23
 * @ Description：自定义令牌对象
 * @ Modified By：
 * @Version : 1.0
 */
@Setter
@Getter
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final long serialVersionID = 1L;
    private String token;

    public JwtAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtAuthenticationToken(Object principal, Object credentials, String token) {
        super(principal, credentials);
        this.token = token;
    }

    public JwtAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities,String token) {
        super(principal, credentials, authorities);
        this.token = token;
    }
}
