package com.xxq.mongo.sys.service;

import com.xxq.mongo.sys.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
public interface IUserRoleService extends IService<UserRole> {

    void setRole(List<UserRole> userRoles);
}
