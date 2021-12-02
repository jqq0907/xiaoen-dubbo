package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author jiangqiangqiang
 * @description: 用户表
 * @date 2021/10/25 4:04 下午
 */
@Data
@TableName(value = "`user`")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    /**
     * 用户code，数据权限
     */
    @TableField(value = "user_code")
    private String userCode;

    /**
     * 账户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 账户密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 部门id
     */
    @TableField(value = "department_id")
    private Long departmentId;

    /**
     * 部门名称
     */
    @TableField(value = "department_name")
    private String departmentName;

    /**
     * 用户昵称
     */
    @TableField(value = "nick_name")
    private String nickName;

    /**
     * 性别，默认男
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 头像
     */
    @TableField(value = "profile_photo")
    private String profilePhoto;

    /**
     * 头像地址
     */
    @TableField(value = "profile_photo_src")
    private String profilePhotoSrc;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 城市
     */
    @TableField(value = "city")
    private String city;

    /**
     * 状态，1启用，0禁用，默认true
     */
    @TableField(value = "user_enabled")
    private Boolean userEnabled;

    /**
     * 是否是系统管理员admin,默认false
     */
    @TableField(value = "id_admin")
    private Boolean idAdmin;

    /**
     * 最后一次修改密码时间
     */
    @TableField(value = "pwd_reset_time")
    private LocalDateTime pwdResetTime;

    /**
     * 删除标志
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

    /**
     * 部门code，数据权限
     */
    @TableField(value = "department_code")
    private String departmentCode;
}