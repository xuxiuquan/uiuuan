package com.xxq.mongo.sys.mapper;

import com.xxq.mongo.sys.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 机构管理 Mapper 接口
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    int deleteByPrimaryKey(Long id);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);

    List<Dept> findPage();

    List<Dept> findAll();
}
