package com.ljnewmap.common.config;

import com.ljnewmap.common.constant.Constant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SysOpenApiConfig {

    @Bean
    public GroupedOpenApi sysApi() {
        return GroupedOpenApi.builder()
                .group("系统模块")
                .pathsToMatch("/sys/**","/login","/logout","/captcha")
                // 添加自定义配置，这里添加了一个用户认证的 header，否则 knife4j 里会没有 header
                .addOperationCustomizer((operation, handlerMethod) -> {
                    //通过operation.operationId来判断是否需要填过权限token参数附带
                    //登录和验证码无需附带token
                    if(StringUtils.equals(operation.getOperationId(),"login") || StringUtils.equals(operation.getOperationId(),"captcha") ){
                        return operation;
                    }
                    return operation.security(Collections.singletonList(new SecurityRequirement().addList(Constant.TOKEN_HEADER)));
                })
                .build();
    }
}
