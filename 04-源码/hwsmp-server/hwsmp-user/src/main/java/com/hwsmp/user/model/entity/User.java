package com.hwsmp.user.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hwsmp.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 系统用户实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class User extends BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String username;
    private String realName;
    private String phone;
    private String password;
    private String avatar;
    private Integer gender;
    private String email;
    private Long regionId;
    private String roleCode;
    private Integer status;
    private LocalDateTime lastLoginAt;
    private String lastLoginIp;
}
