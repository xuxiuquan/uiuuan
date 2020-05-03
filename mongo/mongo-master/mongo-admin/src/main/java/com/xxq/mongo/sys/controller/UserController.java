package com.xxq.mongo.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxq.mongo.common.FileUtils;
import com.xxq.mongo.core.http.HttpResult;
import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.sys.entity.User;
import com.xxq.mongo.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * <p>
 * 用户管理 前端控制器
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    //@PreAuthorize("hasAuthority('sys:dict:add') AND hasAuthority('sys:dict:edit')")
    @PostMapping("/save")
    public HttpResult save(@RequestBody User user){
        return HttpResult.ok().data(userService.save(user));
    }

    //@PreAuthorize("hasAuthority('sys:dict:delete')")
    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<String> ids){
        return HttpResult.ok().data(userService.removeByIds(ids));
    }

    //@PreAuthorize("hasAuthority('sys:dict:view')")
    @GetMapping(value = "/findAll")
    public Object findAll(){
        List<User> userList = userService.list();
        return userList;
    }

    //@PreAuthorize("hasAuthority('sys:dict:view')")
    @PostMapping(value = "/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok().data(userService.findPage(pageRequest));
    }

    @GetMapping("/findByName")
    public HttpResult findByName(String username){
        return HttpResult.ok().data(userService.findByName(username));
    }

    @GetMapping("/findPermissions")
    public HttpResult findPermissions(String name){
        return HttpResult.ok().data(userService.findPermissions(name));
    }

    @GetMapping("/findUserRoles")
    public HttpResult findUserRoles(Long userId){
        return HttpResult.ok().data(userService.findUserRoles(userId));
    }

    @PostMapping("exportExcel")
    public void exportExcel(@RequestBody PageRequest pageRequest, HttpServletResponse res){
        File file = userService.createUserExcelFile(pageRequest);
        FileUtils.downloadFile(res,file,file.getName());
    }
}
