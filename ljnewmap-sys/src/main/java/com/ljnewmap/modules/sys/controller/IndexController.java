package com.ljnewmap.modules.sys.controller;

import com.ljnewmap.common.utils.RT;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页提示
 *
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public RT<String> index(){
        String tips = "服务端已启动，请启动前端才能访问页面！";
        return new RT<String>().ok(tips);
    }
}
