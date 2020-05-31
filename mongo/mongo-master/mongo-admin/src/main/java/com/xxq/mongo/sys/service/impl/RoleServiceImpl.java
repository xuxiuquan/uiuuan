package com.xxq.mongo.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxq.mongo.core.page.MybatisPageHelper;
import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;
import com.xxq.mongo.sys.constant.SysConstants;
import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.entity.Role;
import com.xxq.mongo.sys.mapper.MenuMapper;
import com.xxq.mongo.sys.mapper.RoleMapper;
import com.xxq.mongo.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.List;

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
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    MenuMapper menuMapper;

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        PageResult pageResult = MybatisPageHelper.findPage(pageRequest,roleMapper);
        return pageResult;
    }

    @Override
    public List<Menu> findRoleMenus(Long roleId) {
        Role sysRole = roleMapper.selectById(roleId);
        if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
            // 如果是超级管理员，返回全部
            return menuMapper.findAll();
        }
        return menuMapper.selectByRoleId(roleId);
    }
}
