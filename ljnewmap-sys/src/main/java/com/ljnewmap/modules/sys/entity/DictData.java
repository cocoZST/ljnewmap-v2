package com.ljnewmap.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 *  字典数据
 *
 */
@Data
@Schema(description = "字典数据")
public class DictData {
    @JsonIgnore
    private Long dictTypeId;
    @Schema(description = "字典标签")
    private String dictLabel;
    @Schema(description = "字典值")
    private String dictValue;
}
