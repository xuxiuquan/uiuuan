package com.xxq.mongo.sys.mapper;

import com.xxq.mongo.sys.entity.Menu;
import com.xxq.mongo.sys.entity.Role;
import com.xxq.mongo.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 用户管理 Mapper 接口
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> getAll();

    List<User> findPage();

    Set<Menu> findPermissions(@Param("username") String username);

    List<Role> findUserRoles(@Param("userId") Long userId);
}
