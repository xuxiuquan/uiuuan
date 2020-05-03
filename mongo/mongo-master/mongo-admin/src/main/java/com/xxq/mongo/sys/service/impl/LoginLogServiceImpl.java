package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.sys.entity.LoginLog;
import com.xxq.mongo.sys.mapper.LoginLogMapper;
import com.xxq.mongo.sys.service.ILoginLogService;
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
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

}
