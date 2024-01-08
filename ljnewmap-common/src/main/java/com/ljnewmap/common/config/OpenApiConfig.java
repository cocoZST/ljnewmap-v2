package com.ljnewmap.common.config;

import com.ljnewmap.common.constant.Constant;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI springOpenApi() {
        OpenAPI openApi = new OpenAPI()
                .info(apiInfo())
                //添加验证参数(该设置在接口文档中貌似并未生效)
                .addSecurityItem(new SecurityRequirement().addList(Constant.TOKEN_HEADER))
                //设置全局Authorizethon列表(注意: 仅设置这样knife4j无法进行请求时的参数附带)
                .components(components());
        return openApi;
    }

    /**
     * 接口文档说明
     * @return
    */
    private Info apiInfo() {
        return new Info()
                .title("珞珈新图")
                .description("珞珈新图接口文档")
                .termsOfService("http://www.ljnewmap.com/")
                .version("v2");
    }

    /**
     * 声明验证参数列表
     * @return
    */
    private Components components(){
        return new Components()
                .addSecuritySchemes(Constant.TOKEN_HEADER,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                                .name(Constant.TOKEN_HEADER));
    }
}
