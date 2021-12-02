package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

  /**  
    * @Description: 
    * @author jiangqiangqiang
    * @date 2021/10/25 5:34 下午
    */
/**
    * 角色表
    */
@TableName(value = "`role`")
@EqualsAndHashCode
public class Role {
    /**
     * 角色主键id
     */
    @TableId(value = "role_id", type = IdType.INPUT)
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;

    /**
     * 角色中文名称
     */
    @TableField(value = "role_name_zh")
    private String roleNameZh;

    /**
     * 用户code
     */
    @TableField(value = "user_code")
    private String userCode;

    /**
     * 数据范围
     */
    @TableField(value = "data_scope")
    private String dataScope;

    /**
     * 描述
     */
    @TableField(value = "comments")
    private String comments;

    /**
     * 删除标志
     */
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 创建人id
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 更新人id
     */
    @TableField(value = "update_id")
    private Long updateId;

    /**
     * 部门code
     */
    @TableField(value = "department_code")
    private String departmentCode;

    /**
     * 获取角色主键id
     *
     * @return role_id - 角色主键id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * 设置角色主键id
     *
     * @param roleId 角色主键id
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取角色名称
     *
     * @return role_name - 角色名称
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置角色名称
     *
     * @param roleName 角色名称
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取角色中文名称
     *
     * @return role_name_zh - 角色中文名称
     */
    public String getRoleNameZh() {
        return roleNameZh;
    }

    /**
     * 设置角色中文名称
     *
     * @param roleNameZh 角色中文名称
     */
    public void setRoleNameZh(String roleNameZh) {
        this.roleNameZh = roleNameZh;
    }

    /**
     * 获取用户code
     *
     * @return user_code - 用户code
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 设置用户code
     *
     * @param userCode 用户code
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * 获取数据范围
     *
     * @return data_scope - 数据范围
     */
    public String getDataScope() {
        return dataScope;
    }

    /**
     * 设置数据范围
     *
     * @param dataScope 数据范围
     */
    public void setDataScope(String dataScope) {
        this.dataScope = dataScope;
    }

    /**
     * 获取描述
     *
     * @return comments - 描述
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置描述
     *
     * @param comments 描述
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * 获取删除标志
     *
     * @return is_deleted - 删除标志
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置删除标志
     *
     * @param isDeleted 删除标志
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新人
     *
     * @return update_by - 更新人
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置更新人
     *
     * @param updateBy 更新人
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建人id
     *
     * @return create_id - 创建人id
     */
    public Long getCreateId() {
        return createId;
    }

    /**
     * 设置创建人id
     *
     * @param createId 创建人id
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * 获取更新人id
     *
     * @return update_id - 更新人id
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * 设置更新人id
     *
     * @param updateId 更新人id
     */
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取部门code
     *
     * @return department_code - 部门code
     */
    public String getDepartmentCode() {
        return departmentCode;
    }

    /**
     * 设置部门code
     *
     * @param departmentCode 部门code
     */
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}