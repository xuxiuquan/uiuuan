package com.xxq.mongo.sys.controller;


import com.xxq.mongo.core.http.HttpResult;
import com.xxq.mongo.sys.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    IMenuService iMenuService;

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping("/findNavTree")
    public HttpResult findNavTree(@RequestParam(value = "username") String username){
        return HttpResult.ok().data(iMenuService.findNavTree(username,1));
    }

}
