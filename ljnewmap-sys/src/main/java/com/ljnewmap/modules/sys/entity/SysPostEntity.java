package com.ljnewmap.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ljnewmap.common.entity.BaseAllEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_post")
public class SysPostEntity extends BaseAllEntity {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 岗位编码
     */
    private String code;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 状态（0正常 1停用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer sort;

}