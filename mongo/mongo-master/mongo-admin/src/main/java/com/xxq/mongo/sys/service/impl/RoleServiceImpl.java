package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.sys.entity.Role;
import com.xxq.mongo.sys.mapper.RoleMapper;
import com.xxq.mongo.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
