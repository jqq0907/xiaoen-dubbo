package com.example.base;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author jiangqiangqiang
 * @Description: base entity
 * @date 2021/10/28 5:07 下午
 */
@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    /**
     * 创建人名称
     */
    @TableField(value = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新人名称
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
     * 修改人id
     */
    @TableField(value = "update_id")
    private Long updateId;
}
