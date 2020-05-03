package com.xxq.mongo.sys.mapper;

import com.xxq.mongo.sys.entity.Log;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 系统操作日志表 Mapper 接口
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Mapper
public interface LogMapper extends BaseMapper<Log> {

}
