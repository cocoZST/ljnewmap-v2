package com.ljnewmap.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典类型
 *
 */
@Data
@Schema(description = "字典类型")
public class DictType {
    @JsonIgnore
    private Long id;
    @Schema(description = "字典类型")
    private String dictType;
    @Schema(description = "类型列表")
    private List<DictData> dataList = new ArrayList<>();
}
