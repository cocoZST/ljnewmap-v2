package com.ljnewmap.common.exception;

/**
 * 全局错误码
 */
public interface GlobalErrorCodeConstants {
    //*********系统异常码(0-999)**************
    ErrorCode SUCCESS = new ErrorCode(0, "成功");
    ErrorCode BAD_REQUEST = new ErrorCode(400,"请求参数错误{0}");
    ErrorCode UNAUTHORIZED = new ErrorCode(401,"账号未登录或登录失效");
    ErrorCode NO_PERMISSION = new ErrorCode(403,"当前操作没有权限");
    ErrorCode NOT_FOUND = new ErrorCode(404,"请求未找到");
    ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(500,"服务器内部异常");

    //*********通用状态码(1000-9999)**********
    // 公共 1000-1999
    ErrorCode NOT_NULL = new ErrorCode(1000,"{0}不能为空");
    ErrorCode INVALID_SYMBOL = new ErrorCode(1001,"不能包含非法字符");
    // 数据库 2000-2100
    ErrorCode DB_RECORD_EXISTS = new ErrorCode(2000,"数据库中已存在该记录");
    // 组件 3000-3999
    // Redis
    ErrorCode REDIS_ERROR = new ErrorCode(3000,"Redis服务异常");

    //*********系统业务错误状态码**************
    // 10(模块号) 00(子功能码) 00(模块内业务代码)
    // 具体模块的错误码在具体模块的exception文件夹下

}
