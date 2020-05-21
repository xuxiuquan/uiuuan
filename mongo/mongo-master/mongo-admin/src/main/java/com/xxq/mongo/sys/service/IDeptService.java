package com.xxq.mongo.sys.service;

import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;
import com.xxq.mongo.sys.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 机构管理 服务类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
public interface IDeptService extends IService<Dept> {

    List<Dept> findTree();

    PageResult findPage(PageRequest pageRequest);
}
