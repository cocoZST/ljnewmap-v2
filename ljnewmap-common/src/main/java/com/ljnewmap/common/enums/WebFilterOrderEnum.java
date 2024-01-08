package com.ljnewmap.common.enums;

public interface WebFilterOrderEnum {
    //order 值越小优先级越高

    int SHIRO_FILTER = -100; //登录验证过滤

    int XSS_FILTER = -99; //非法字符过滤

    int FLOWABLE_FILTER = -98; // 需要保证在 SHIRO_FILTER 过滤后面
}
