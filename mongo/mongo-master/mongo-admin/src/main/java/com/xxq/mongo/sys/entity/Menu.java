package com.xxq.mongo.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author XuXiuquan
 * @since 2020-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 菜单名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 父菜单ID， 一级菜单ID为0
     */
    private Long parentId;

    /**
     * 菜单url,类型：1.普通页面（如用户管理，/sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标url(如SQL监控， 		     iframe：/druid/login.html, iframe:前缀会替换成服务器地址)
     */
    private String url;

    /**
     * 授权（多个用逗号分隔，如:sys:user:add,sys:user:edit）
     */
    private String perms;

    /**
     * 类型 0：目录 1：菜单 2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

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
    //private String parentName;
    // 非数据库字段
    //private Integer level;
    // 非数据库字段
    //private List<Menu> children;


}
