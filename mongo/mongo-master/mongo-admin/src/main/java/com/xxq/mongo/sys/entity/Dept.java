package com.xxq.mongo.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 机构管理
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 机构名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 上级机构id，一级机构为0
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    private Date lastUpdateTime;

    /**
     * 是否删除 -1： 已删除 0： 正常
     */
    private Integer delFlag;

    // 非数据库字段
    private List<Dept> children;
    // 非数据库字段
    private String parentName;
    // 非数据库字段
    private Integer level;

    public void removeField(Dept record) {
        record.setChildren(null);
        record.setParentName(null);
        record.setLevel(null);
    }
}
