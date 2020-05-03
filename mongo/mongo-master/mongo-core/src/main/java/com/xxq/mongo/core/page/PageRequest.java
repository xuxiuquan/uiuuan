package com.xxq.mongo.core.page;



import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 18:55 2020/4/14
 * @ Description：分页请求
 * @ Modified By：
 * @Version : 1.0
 */
@Setter
@Getter
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 每页参数
     */
    private int pageSize = 10;

    /**
     * 查询参数
     */
    private Map<String, Object> params = new HashMap<String, Object>();
}
