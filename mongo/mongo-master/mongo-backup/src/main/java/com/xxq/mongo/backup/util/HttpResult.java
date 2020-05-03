package com.xxq.mongo.backup.util;

import lombok.Getter;
import lombok.Setter;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 19:51 2020/4/14
 * @ Description：HTTP结果封装
 * @ Modified By：
 * @Version : 1.0
 */
@Setter
@Getter
public class HttpResult {
    private int code = 200;
    private String msg;
    private Object data;

    public static HttpResult error(){
        return error(500,"未知异常，请联系管理员");
    }

    public static HttpResult error(String msg){
        return error(500,msg);
    }

    public static HttpResult error(int code, String msg) {
        HttpResult httpResult = new HttpResult();
        httpResult.setCode(code);
        httpResult.setMsg(msg);
        return httpResult;
    }

    public static HttpResult ok(String msg){
        HttpResult httpResult = new HttpResult();
        httpResult.setMsg(msg);
        return httpResult;
    }

    public HttpResult data(Object data){
        this.setData(data);
        return this;
    }

    public static HttpResult ok(){
        HttpResult httpResult = new HttpResult();
        return httpResult;
    }
}
