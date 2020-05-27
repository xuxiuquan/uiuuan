package com.xxq.mongo.sys.vo;

import com.xxq.mongo.sys.entity.Role;
import com.xxq.mongo.sys.entity.User;
import com.xxq.mongo.sys.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 21:16 2020/5/23
 * @ Description：
 * @ Modified By：
 * @Version : 1.0
 */
@Setter
@Getter
public class UserVO extends User {
    // 非数据库字段
    private String deptName;
    // 非数据库字段
    private String roleNames;
    // 非数据库字段
    private List<Role> roles = new ArrayList<>();
    // 非数据库字段
    private List<UserRole> UserRoles = new ArrayList<>();
}
