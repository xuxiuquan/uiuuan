package com.xxq.mongo.sys.service;

import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;
import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色管理 服务类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
public interface IRoleService extends IService<Role> {

    PageResult findPage(PageRequest pageRequest);

    List<Menu> findRoleMenus(Long roleId);
}
