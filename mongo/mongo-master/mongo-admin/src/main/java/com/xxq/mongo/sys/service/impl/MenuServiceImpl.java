package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.sys.constant.SysConstants;
import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.mapper.MenuMapper;
import com.xxq.mongo.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxq.mongo.sys.vo.MenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
    public List<MenuVo> findTree(String username, int menuType,String... menuname) {
        List<MenuVo> sysMenus = new ArrayList<>();
        List<Menu> menus;
        menus = findByUser(username);
        for (Menu menu : menus) {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(menu,menuVo);
            if (menuVo.getParentId() == null || menuVo.getParentId() == 0) {
                menuVo.setLevel(0);
                if(!exists(sysMenus, menuVo)) {
                    sysMenus.add(menuVo);
                }
            }
        }
        sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
        findChildren(sysMenus, menus, menuType);
        if(menuname.length!=0&&!"".equals(menuname[0])&&menuname[0]!=null){
            String menuName = menuname[0];
            matcherMenuName(sysMenus,menuName);
        }
        return sysMenus;
    }

    private void matcherMenuName(List<MenuVo> menus,String menuName) {
        Iterator<MenuVo> iterator = menus.iterator();
        while (iterator.hasNext()){
            MenuVo menuVo = iterator.next();
            if(menuVo.getChildren().size()!=0){
                matcherMenuName(menuVo.getChildren(),menuName);
            }
            //如果是菜单，菜单名不含有搜索关键字，则移除
            if(!menuVo.getName().contains(menuName)&&menuVo.getType()==1){
                iterator.remove();
            }
            //如果是目录，目录下没有菜单，则移除
            if(menuVo.getType()==0&&menuVo.getChildren().size()==0){
                iterator.remove();
            }
        }
    }

    @Override
    public List<Menu> findByUser(String userName) {
        if(userName == null || "".equals(userName) || SysConstants.ADMIN.equalsIgnoreCase(userName)) {
            return menuMapper.findAll();
        }
        return menuMapper.findByUserName(userName);
    }

    private void findChildren(List<MenuVo> SysMenus, List<Menu> menus, int menuType) {
        for (MenuVo Menu : SysMenus) {
            List<MenuVo> children = new ArrayList<>();
            for (Menu menu : menus) {
                MenuVo menuVo = new MenuVo();
                BeanUtils.copyProperties(menu,menuVo);
                if(menuType == 1 && menuVo.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue ;
                }
                if (Menu.getId() != null && Menu.getId().equals(menuVo.getParentId())) {
                    menuVo.setParentName(Menu.getName());
                    menuVo.setLevel(Menu.getLevel() + 1);
                    if(!exists(children, menuVo)) {
                        children.add(menuVo);
                    }
                }
            }
            Menu.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
            findChildren(children, menus, menuType);
        }
    }

    private boolean exists(List<MenuVo> sysMenus, Menu sysMenu) {
        boolean exist = false;
        for(Menu menu:sysMenus) {
            if(menu.getId().equals(sysMenu.getId())) {
                exist = true;
            }
        }
        return exist;
    }
}
