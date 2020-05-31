package com.xxq.mongo.sys.controller;


import com.xxq.mongo.core.http.HttpResult;
import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.sys.constant.SysConstants;
import com.xxq.mongo.sys.entity.Role;
import com.xxq.mongo.sys.entity.RoleMenu;
import com.xxq.mongo.sys.service.IRoleMenuService;
import com.xxq.mongo.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色管理 前端控制器
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService iRoleService;

    @Autowired
    IRoleMenuService iRoleMenuService;

    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok().data(iRoleService.findPage(pageRequest));
    }

    @PreAuthorize("hasAuthority('sys:role:add') AND hasAuthority('sys:role:edit')")
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody Role record) {
        return HttpResult.ok().data(iRoleService.saveOrUpdate(record));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value="/findRoleMenus")
    public HttpResult findRoleMenus(@RequestParam Long roleId) {
        return HttpResult.ok().data(iRoleService.findRoleMenus(roleId));
    }

    @PreAuthorize("hasAuthority('sys:role:view')")
    @PostMapping(value="/saveRoleMenus")
    public HttpResult saveRoleMenus(@RequestBody List<RoleMenu> records) {
        for(RoleMenu record:records) {
            Role sysRole = iRoleService.getById(record.getRoleId());
            if(SysConstants.ADMIN.equalsIgnoreCase(sysRole.getName())) {
                // 如果是超级管理员，不允许修改
                return HttpResult.error("超级管理员拥有所有菜单权限，不允许修改！");
            }
        }
        iRoleMenuService.removeByRoleId(records.get(0).getRoleId());
        return HttpResult.ok().data(iRoleMenuService.saveBatch(records));
    }

    @PreAuthorize("hasAuthority('sys:role:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<Role> records) {
        return HttpResult.ok().data(iRoleService.removeByIds(records.stream().map(Role::getId).collect(Collectors.toList())));
    }
}
