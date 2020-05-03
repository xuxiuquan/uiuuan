package com.xxq.mongo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 17:54 2020/4/19
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
public class JwtTokenUtils implements Serializable {
    private static final long serialVersionID = 1L;

    /**
     * 用户名称
     */
    private static final String USERNAME = Claims.SUBJECT;

    /**
     * 创建时间
     */
    private static final String CREATED = "created";

    /**
     * 权限列表
     */
    private static final String AUTHORITIES = "authorities";

    /**
     * 密钥
     */
    private static final String SECRECT = "SECRECT:abcdefgh";

    /**
     * token有效期
     */
    private static final long EXPIRE_TIME = 12*60*60*1000;



    public static Authentication getAuthenticationFromToken(HttpServletRequest request){
        Authentication authentication = null;
        String token = JwtTokenUtils.getToken(request);
        if (token != null){
            //请求令牌不能为空
            if(SecurityUtils.getAuthentication() == null){
                //上下文中Authentication为空
                Claims claims = getClaimsFromToken(token);
                if(claims == null){
                    return null;
                }
                String username = claims.getSubject();
                if(username == null){
                    return null;
                }
                if(isTokenExpired(token)){
                    return null;
                }
                Object authors = claims.get(AUTHORITIES);
                List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                if(authors != null && authors instanceof List){
                    for(Object o:(List)authors){
                        authorities.add(new SimpleGrantedAuthority((String)((Map)o).get("authority")));
                    }
                }
                authentication = new UsernamePasswordAuthenticationToken(username,null,authorities);
            }else {
                authentication = SecurityUtils.getAuthentication();
            }
        }
        return authentication;
    }

    /**
     * 判断令牌是否过期
     * @param token
     * @return
     */
    private static boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    /**
     * 从令牌中解析数据声明
     * @param token
     * @return
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        claims = Jwts.parser().setSigningKey(SECRECT).parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * 从请求中截取token
     * @param request
     * @return
     */
    private static String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String tokenHead = "Bearer";
        if(token == null){
            token = request.getHeader("token");
        }else if(token.contains(tokenHead)){
            token = token.substring(tokenHead.length());
        }
        if("".equals(token)){
            return null;
        }
        return token;
    }

    /**
     * 生成令牌
     * @param authentication
     * @return
     */
    public static String generateToken(Authentication authentication) {
        Map<String,Object> claims = new HashMap<>(3);
        claims.put(USERNAME,SecurityUtils.getUsername(authentication));
        claims.put(CREATED,new Date());
        claims.put(AUTHORITIES,authentication.getAuthorities());
        return generateToken(claims);
    }

    /**
     * 从数据声明生成令牌
     * @param claims
     * @return token令牌
     */
    private static String generateToken(Map<String,Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis()+EXPIRE_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512,SECRECT).compact();
    }
}
