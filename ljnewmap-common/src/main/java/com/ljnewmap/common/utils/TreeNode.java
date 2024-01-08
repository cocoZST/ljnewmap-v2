package com.ljnewmap.common.utils;

import com.ljnewmap.common.validator.group.AddGroup;
import com.ljnewmap.common.validator.group.DefaultGroup;
import com.ljnewmap.common.validator.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树节点，所有需要实现树节点的，都需要继承该类
 *
 */
@Data
public class TreeNode<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
    */
    @Schema(description = "id")
    @Null(message="ID必须为空", groups = AddGroup.class)
    @NotNull(message="ID不能为空", groups = UpdateGroup.class)
    private Long id;
    /**
     * 上级ID
    */
    @Schema(description = "上级ID")
    @NotNull(message="请选择上级菜单", groups = DefaultGroup.class)
    private Long pid;
    /**
     * 子节点列表
    */
    @Schema(description = "子节点列表")
    private List<T> children = new ArrayList<>();
}