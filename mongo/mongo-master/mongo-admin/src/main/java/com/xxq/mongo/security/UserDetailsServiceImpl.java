package com.xxq.mongo.security;

import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.entity.User;
import com.xxq.mongo.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 19:11 2020/4/19
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserService.findByName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        List<String> permissions = iUserService.findPermissions(user.getName()).stream().map(Menu::getPerms).collect(Collectors.toList());
        List<GrantedAuthority> grantedAuthorities = permissions.stream().map(GrantedAuthorityImpl::new).collect(Collectors.toList());
        return new JwtUserDetails(user.getName(),user.getPassword(),user.getSalt(),grantedAuthorities);
    }
}
