package com.xxq.mongo.sys.service.impl;

import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.mapper.MenuMapper;
import com.xxq.mongo.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
