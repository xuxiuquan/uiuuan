package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.core.page.MybatisPageHelper;
import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;
import com.xxq.mongo.sys.entity.Dict;
import com.xxq.mongo.sys.mapper.DictMapper;
import com.xxq.mongo.sys.service.IDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {
    @Autowired
    DictMapper dictMapper;

    @Override
    public List<Dict> findByLable(String label) {
        return dictMapper.findByLabel(label);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return MybatisPageHelper.findPage(pageRequest,dictMapper);
    }
}
