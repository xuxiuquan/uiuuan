package com.xxq.mongo.security;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 19:24 2020/4/19
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@AllArgsConstructor
@Setter
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
