package com.xxq.mongo.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.vo.MenuVo;

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

    List<MenuVo> findTree(String username, int i,String... name);

    List<Menu> findByUser(String userName);

}
