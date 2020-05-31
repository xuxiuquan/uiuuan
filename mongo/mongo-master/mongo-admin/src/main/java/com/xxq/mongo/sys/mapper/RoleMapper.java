package com.xxq.mongo.sys.mapper;

import com.xxq.mongo.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色管理 Mapper 接口
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findPage(@Param("params") Map params);
}
