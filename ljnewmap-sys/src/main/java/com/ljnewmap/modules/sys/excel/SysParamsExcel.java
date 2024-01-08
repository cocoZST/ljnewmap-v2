package com.ljnewmap.modules.sys.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 参数管理
 *
 */
@Data
public class SysParamsExcel {
    @ExcelProperty("参数编码")
    private String paramCode;
    @ExcelProperty("参数值")
    private String paramValue;
    @ExcelProperty("备注")
    private String remark;

}