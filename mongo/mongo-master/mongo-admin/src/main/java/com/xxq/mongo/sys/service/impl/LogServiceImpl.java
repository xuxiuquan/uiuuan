package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.sys.entity.Log;
import com.xxq.mongo.sys.mapper.LogMapper;
import com.xxq.mongo.sys.service.ILogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志表 服务实现类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
