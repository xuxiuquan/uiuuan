package com.xxq.mongo.sys.controller;


import com.xxq.mongo.core.http.HttpResult;
import com.xxq.mongo.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @PreAuthorize("hasAuthority('sys:role:view')")
    @GetMapping(value="/findAll")
    public HttpResult findAll() {
        return HttpResult.ok().data(iRoleService.list());
    }
}
