package com.ljnewmap.modules.oss.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljnewmap.common.entity.BaseAllEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("re_data_common")
public class DataCommonEntity extends BaseAllEntity {

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private String name;

    @TableField(fill = FieldFill.INSERT)
    private String dataPattern;

    @TableField(fill = FieldFill.INSERT)
    private String dataCategory;

    @TableField(fill = FieldFill.INSERT)
    private boolean isOpen;

    @TableField(fill = FieldFill.INSERT)
    private String description;

    @TableField(fill = FieldFill.INSERT)
    private String url;

    @TableField(fill = FieldFill.INSERT)
    private String tag;

}
