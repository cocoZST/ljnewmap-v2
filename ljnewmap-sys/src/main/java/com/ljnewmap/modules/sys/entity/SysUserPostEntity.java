package com.ljnewmap.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljnewmap.common.entity.BaseCreateEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户岗位关系表
 */
@Data
@EqualsAndHashCode(callSuper=true)
@TableName(value = "sys_user_post")
public class SysUserPostEntity extends BaseCreateEntity {

    /**
     * id
     */
    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 岗位ID
     */
    private Long postId;

    /**
     * 任职类型: 0: 全职 1: 兼职 2: 3:实习
     */
    private Integer status;

}