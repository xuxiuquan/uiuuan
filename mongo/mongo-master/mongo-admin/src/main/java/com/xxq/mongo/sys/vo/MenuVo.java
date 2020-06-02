package com.xxq.mongo.sys.vo;

import com.xxq.mongo.sys.entity.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 21:09 2020/6/1
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@Setter
@Getter
public class MenuVo extends Menu {
    // 非数据库字段
    private String parentName;
    // 非数据库字段
    private Integer level;
    // 非数据库字段
    private List<MenuVo> children;
}
