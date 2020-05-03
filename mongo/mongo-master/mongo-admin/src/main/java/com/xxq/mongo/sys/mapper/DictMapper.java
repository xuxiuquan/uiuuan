package com.xxq.mongo.sys.mapper;

import com.xxq.mongo.sys.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 分页查询
     * @return
     */
    List<Dict> findPage();

    /**
     * 根据标签名查询
     * @param label
     * @return
     */
    List<Dict> findByLabel(@Param(value = "label")String label);

    /**
     * 根据标签名称分页查询
     * @param label
     * @return
     */
    List<Dict> findPageByLabel(@Param(value = "label")String label);
}
