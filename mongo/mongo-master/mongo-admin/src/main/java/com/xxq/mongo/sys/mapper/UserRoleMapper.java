package com.xxq.mongo.sys.mapper;

import com.xxq.mongo.sys.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    void removeRoles(@Param("userId") Long userId);
}
