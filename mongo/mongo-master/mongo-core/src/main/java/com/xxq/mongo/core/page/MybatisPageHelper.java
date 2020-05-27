package com.xxq.mongo.core.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxq.mongo.common.ReflectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 19:08 2020/4/14
 * @ Description：Mybatis分页查询助手
 * @ Modified By：
 * @Version : 1.0
 */
public class MybatisPageHelper {
    public static final String findPage = "findPage";

    /**
     * 分页查询，查询方法视ORM框架而定
     * @param pageRequest 分页请求
     * @param mapper mapper对象
     * @return
     */
    public static PageResult findPage(PageRequest pageRequest,Object mapper){
        return findPage(pageRequest, mapper,findPage);
    }

    /**
     * 调用分页插件进行分页查询
     * @param pageRequest 分页请求
     * @param mapper mapper对象
     * @param queryMethodName 要分页查询的方法名
     * @param args 方法参数
     * @return
     */
    @SuppressWarnings({"unchecked","rawtypes"})
    public static PageResult findPage(PageRequest pageRequest,Object mapper,String queryMethodName,Object...args){
        //设置分页参数
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        Map<String,Object> params = pageRequest.getParams();
        PageHelper.startPage(pageNum,pageSize);
        Object result = ReflectionUtils.invoke(mapper,queryMethodName,params);
        return getPageResult(pageRequest,new PageInfo((List)result));
    }

    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    private static PageResult getPageResult(PageRequest pageRequest, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTatalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
