package com.ljnewmap.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础实体类，所有实体都需要继承
 *
 */
@Data
public abstract class BaseCreateEntity implements Serializable {
    /**
     * 创建者
    */
    @TableField(fill = FieldFill.INSERT)
    private Long  creator;
    /**
     * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
}