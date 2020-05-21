package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.sys.constant.SysConstants;
import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.mapper.MenuMapper;
import com.xxq.mongo.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> findNavTree(String username, int menuType) {
        List<Menu> sysMenus = new ArrayList<>();
        List<Menu> menus = findByUser(username);
        for (Menu menu : menus) {
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                if(!exists(sysMenus, menu)) {
                    sysMenus.add(menu);
                }
            }
        }
        sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
        findChildren(sysMenus, menus, menuType);
        return sysMenus;
    }

    @Override
    public List<Menu> findByUser(String userName) {
        if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
            return menuMapper.findAll();
        }
        return menuMapper.findByUserName(userName);
    }

    private void findChildren(List<Menu> SysMenus, List<Menu> menus, int menuType) {
        for (Menu Menu : SysMenus) {
            List<Menu> children = new ArrayList<>();
            for (Menu menu : menus) {
                if(menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue ;
                }
                if (Menu.getId() != null && Menu.getId().equals(menu.getParentId())) {
                    menu.setParentName(Menu.getName());
                    menu.setLevel(Menu.getLevel() + 1);
                    if(!exists(children, menu)) {
                        children.add(menu);
                    }
                }
            }
            Menu.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
            findChildren(children, menus, menuType);
        }
    }

    private boolean exists(List<Menu> sysMenus, Menu sysMenu) {
        boolean exist = false;
        for(Menu menu:sysMenus) {
            if(menu.getId().equals(sysMenu.getId())) {
                exist = true;
            }
        }
        return exist;
    }
}
