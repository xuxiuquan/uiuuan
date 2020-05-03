package com.xxq.mongo.core.service;

import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;

import java.util.List;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 18:52 2020/4/14
 * @ Description：通用CURD接口
 * @ Modified By：
 * @Version : 1.0
 */
public interface CurdService<T> {
    /**
     * 保存操作
     * @param record
     * @return
     */
    int save(T record);

    /**
     * 删除操作
     * @param record
     * @return
     */
    int delete(T record);

    /**
     * 批量删除操作
     * @param records
     * @return
     */
    int delete(List<T> records);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    T findById(Long id);

    /**
     * 分页查询
     * 这里统一封装了分页请求和结果，避免直接引入及具体框架的分页对象，
     * 如mybatis或jpa的分页对象从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要
     * 变动的情况，替换ORM框架也不会影响服务
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);
}
