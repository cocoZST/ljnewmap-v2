package com.ljnewmap.modules.bus;

import com.ljnewmap.common.constant.Constant;
import com.ljnewmap.common.utils.RT;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/test")
@RestController
@Tag(name = "测试控制类")
public class TestController {


    @Parameter(name = "name",description = "姓名",required = true)
    @Operation(summary ="测试是否能携带token")
    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@Parameter(name = "name")String name){
        return ResponseEntity.ok("Hi:"+name);
    }

    @Parameter(name = "name",description = "姓名2",required = true)
    @Operation(summary ="测试是否能携带token2")
    @GetMapping("/sayHi2")
    public ResponseEntity<String> sayHi2(@Parameter(name = "name")String name){
        return ResponseEntity.ok("Hi2:"+name);
    }

    @GetMapping("page")
    @Operation(summary = "分页")
    @Parameters({
            @Parameter(name = Constant.PAGE, description = "当前页码，从1开始", in = ParameterIn.QUERY, required = true,schema = @Schema(type = "integer")),
            @Parameter(name = Constant.LIMIT, description = "每页显示记录数", in = ParameterIn.QUERY, required = true),
            @Parameter(name = Constant.ORDER_FIELD, description = "排序字段", in = ParameterIn.QUERY),
            @Parameter(name = Constant.ORDER, description = "排序方式，可选值(asc、desc)", in = ParameterIn.QUERY),
            @Parameter(name = "status", description = "状态  0：失败    1：成功    2：账号已锁定", in = ParameterIn.QUERY),
            @Parameter(name = "creatorName", description = "用户名", in = ParameterIn.QUERY)
    })
    public RT<String> page(@Parameter(hidden = true) @RequestParam Map<String, Object> params) {
        return new RT().ok("true");
    }
}
