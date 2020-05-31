package com.xxq.mongo.sys.service;

import com.xxq.mongo.sys.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findTree(String username, int i);

    List<Menu> findByUser(String userName);

}
