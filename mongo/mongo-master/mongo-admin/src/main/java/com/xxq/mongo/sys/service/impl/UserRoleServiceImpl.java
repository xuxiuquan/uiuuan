package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.sys.entity.UserRole;
import com.xxq.mongo.sys.mapper.UserRoleMapper;
import com.xxq.mongo.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public void setRole(List<UserRole> userRoles) {
        userRoleMapper.removeRoles(userRoles.get(0).getUserId());
        saveBatch(userRoles);
    }
}
