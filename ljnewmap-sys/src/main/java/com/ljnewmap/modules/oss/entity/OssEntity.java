package com.ljnewmap.modules.oss.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljnewmap.common.entity.BaseAllEntity;
import com.ljnewmap.common.entity.BaseCreateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("oss")
public class OssEntity extends BaseCreateEntity {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 存放路径
     */
    @TableField(fill = FieldFill.INSERT)
    private String path;

    /**
     * 存放uuid名
     */
    @TableField(fill = FieldFill.INSERT)
    private String uuidName;

    /**
     * 源文件名
     */
    @TableField(fill = FieldFill.INSERT)
    private String originalName;
}
