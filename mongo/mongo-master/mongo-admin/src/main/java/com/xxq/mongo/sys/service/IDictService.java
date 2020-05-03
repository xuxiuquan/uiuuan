package com.xxq.mongo.sys.service;

import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;
import com.xxq.mongo.sys.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
public interface IDictService extends IService<Dict> {

    /**
     * 根据名称查询
     * @param label
     * @return
     */
    List<Dict> findByLable(String label);

    PageResult findPage(PageRequest pageRequest);
}
