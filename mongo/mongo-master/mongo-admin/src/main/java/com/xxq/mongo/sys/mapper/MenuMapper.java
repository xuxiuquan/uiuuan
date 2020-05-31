package com.xxq.mongo.sys.mapper;

import com.xxq.mongo.sys.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findByUserName(@Param("username") String userName);

    List<Menu> findAll();

    List<Menu> selectByRoleId(@Param("roleId") Long roleId);
}
