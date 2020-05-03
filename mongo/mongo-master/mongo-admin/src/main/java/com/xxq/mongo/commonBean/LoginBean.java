package com.xxq.mongo.commonBean;

import lombok.Getter;
import lombok.Setter;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 19:54 2020/4/23
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@Setter
@Getter
public class LoginBean {
    private String account;
    private String password;
    private String captcha;
}
