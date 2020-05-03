package com.xxq.mongo.security;

import com.xxq.mongo.util.PasswordEncoder;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 18:52 2020/4/19
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
public class JwtAuthenticationProvider extends DaoAuthenticationProvider {

    public JwtAuthenticationProvider(UserDetailsService userDetailsService){
        setUserDetailsService(userDetailsService);
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if(authentication.getCredentials() == null){
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials","Bad credentials"));
        }
        String password = authentication.getCredentials().toString();
        String salt = ((JwtUserDetails)userDetails).getSalt();
        if(! new PasswordEncoder(salt).matches(userDetails.getPassword(),password)){
            logger.debug("Authentication failed: password not match");
            throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials","Bad credentials"));
        }
    }
}
