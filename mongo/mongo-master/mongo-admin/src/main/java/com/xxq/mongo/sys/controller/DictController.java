package com.xxq.mongo.sys.controller;


import com.xxq.mongo.core.http.HttpResult;
import com.xxq.mongo.core.page.PageRequest;
import com.xxq.mongo.core.page.PageResult;
import com.xxq.mongo.sys.entity.Dict;
import com.xxq.mongo.sys.service.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@RestController
@RequestMapping("/sys/dict")
public class DictController {
    @Autowired
    private IDictService dictService;

    @PostMapping("/save")
    public HttpResult save(@RequestBody Dict record){
        return HttpResult.ok().data(dictService.save(record));
    }

    @PostMapping("/delete")
    public HttpResult delete(@RequestBody List<String> records){
        return HttpResult.ok().data(dictService.removeByIds(records));
    }

    @PostMapping("/deleteById")
    public HttpResult deleteById(@RequestBody Long id){
        return HttpResult.ok().data(dictService.removeById(id));
    }

    @PostMapping("/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest){
        return HttpResult.ok().data(dictService.findPage(pageRequest));
    }

    @GetMapping("/findByLabel")
    public List<Dict> findByLabel(String label){
        return dictService.findByLable(label);
    }
}
