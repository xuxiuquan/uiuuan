package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.sys.entity.Config;
import com.xxq.mongo.sys.mapper.ConfigMapper;
import com.xxq.mongo.sys.service.IConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统配置表 服务实现类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements IConfigService {

}
