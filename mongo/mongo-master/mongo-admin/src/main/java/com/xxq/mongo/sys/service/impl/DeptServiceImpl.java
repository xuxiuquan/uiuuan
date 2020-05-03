package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.sys.entity.Dept;
import com.xxq.mongo.sys.mapper.DeptMapper;
import com.xxq.mongo.sys.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 机构管理 服务实现类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

}
