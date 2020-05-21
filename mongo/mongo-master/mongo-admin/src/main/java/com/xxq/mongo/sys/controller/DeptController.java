package com.xxq.mongo.sys.controller;


import com.xxq.mongo.core.http.HttpResult;
import com.xxq.mongo.sys.entity.Dept;
import com.xxq.mongo.sys.service.IDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 机构管理 前端控制器
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private IDeptService iDeptService;

    @PreAuthorize("hasAuthority('sys:dept:add') AND hasAuthority('sys:dept:edit')")
    @PostMapping(value="/save")
    public HttpResult save(@RequestBody Dept record) {
        if(record.getId()==null||record.getId()==0){
            return HttpResult.ok().data(iDeptService.save(record));
        }else {
            record.removeField(record);
            return HttpResult.ok().data(iDeptService.updateById(record));
        }
    }

    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @PostMapping(value="/delete")
    public HttpResult delete(@RequestBody List<Dept> records) {
        return HttpResult.ok().data(iDeptService.removeByIds(records));
    }

    @PreAuthorize("hasAuthority('sys:dept:view')")
    @GetMapping(value="/findTree")
    public HttpResult findTree() {
        return HttpResult.ok().data(iDeptService.findTree());
    }
}
