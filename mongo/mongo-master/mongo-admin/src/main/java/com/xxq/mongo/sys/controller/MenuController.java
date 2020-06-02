package com.xxq.mongo.sys.controller;


import com.xxq.mongo.core.http.HttpResult;
import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

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

    @PreAuthorize("hasAuthority('sys:menu:add') AND hasAuthority('sys:menu:edit')")
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody Menu record) {
        return HttpResult.ok().data(iMenuService.saveOrUpdate(record));
    }

    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<Menu> records) {
        return HttpResult.ok().data(iMenuService.removeByIds(records.stream().map(Menu::getId).collect(Collectors.toList())));
    }

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping("/findNavTree")
    public HttpResult findNavTree(@RequestParam(value = "username") String username){
        return HttpResult.ok().data(iMenuService.findTree(username,1));
    }

    @PreAuthorize("hasAuthority('sys:menu:view')")
    @GetMapping(value="/findMenuTree")
    public HttpResult findMenuTree(HttpServletRequest request) {
        String menuname = request.getParameter("name");
        return HttpResult.ok().data(iMenuService.findTree(null, 0,menuname));
    }

}
