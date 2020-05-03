package com.xxq.mongo.core.page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 19:00 2020/4/14
 * @ Description：分页返回结果
 * @ Modified By：
 * @Version : 1.0
 */
@Setter
@Getter
public class PageResult {
    /**
     * 当前页码
     */
    private int pageNum;

    /**
     * 每页数量
     */
    private int pageSize;

    /**
     * 记录总数
     */
    private long totalSize;

    /**
     * 页码总数
     */
    private int tatalPages;

    /**
     * 分页数据
     */
    private List<?> content;
}
