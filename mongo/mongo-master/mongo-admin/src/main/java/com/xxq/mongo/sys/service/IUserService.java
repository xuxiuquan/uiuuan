package com.xxq.mongo.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;
import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.entity.Role;
import com.xxq.mongo.sys.entity.User;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户管理 服务类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
public interface IUserService extends IService<User> {
    /**
     * 查找所有用户
     * @return
     */
    PageResult findPage(PageRequest pageRequest);

    Set<String> findPermissions(String name);

    List<Role> findUserRoles(Long userId);

    File createUserExcelFile(PageRequest pageRequest);

    User findByName(String username);
}
