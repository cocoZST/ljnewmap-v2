package com.ljnewmap.modules.oss.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
@Schema(title = "文件表单", description = "上传表单")
public class DataCommonDTO {

//   @Schema(description = "数据名称", type = "String")
    @NotNull(message = "名称不能为空")
    private String name;

//    @Schema(name = "dataPattern",description = "数据格式", type = "String")
    @NotNull(message = "格式不能为空")
    private String dataPattern;

//    @Schema(description = "数据类别", type = "String")
    private String dataCategory;

//    @Schema(description = "是否公开", type = "boolean")
    @NotNull(message = "不能为空")
    private Integer isOpen;

//    @Schema(description = "描述", type = "String")
    private String description;

//    @Schema(description = "上传文件", type = "MultipartFile")
    @NotNull(message = "文件不能为空")
    private MultipartFile file;

//    @Schema(description = "标签", type = "String")
    private String tag;

}
