package com.ljnewmap.common.exception;

public interface SysErrorCodeConstants {
    // 10(模块号) 00(子功能码) 00(模块内业务代码)
    // 10: 系统模块
    // 10 00 xx: 登录功能
    ErrorCode CAPTCHA_ERROR = new ErrorCode(100000,"验证码不正确");
    ErrorCode CAPTCHA_NOT_NULL = new ErrorCode(100001,"验证码唯一标识不能为空");
    ErrorCode ACCOUNT_PASSWORD_ERROR = new ErrorCode(100002,"账号或密码错误");
    ErrorCode ACCOUNT_DISABLE = new ErrorCode(100003,"账号已被停用");
    ErrorCode PASSWORD_ERROR = new ErrorCode(100004,"原密码不正确");
    // 10 01 xx: 菜单功能
    ErrorCode MENU_SUB_EXIST = new ErrorCode(100100,"先删除子菜单或按钮");
    ErrorCode MENU_SUPERIOR_ERROR = new ErrorCode(100101,"上级菜单不能为自身");
    // 10 02 xx: 部门功能
    ErrorCode DEPT_SUPERIOR_ERROR = new ErrorCode(100200,"上级部门选择错误");
    ErrorCode DEPT_SUB_DELETE_ERROR = new ErrorCode(100201,"请先删除下级部门");
    ErrorCode DEPT_USER_DELETE_ERROR = new ErrorCode(100202,"请先删除部门下的用户");
    // 10 03 xx: 数据权限
    ErrorCode DATA_SCOPE_PARAMS_ERROR = new ErrorCode(100300,"数据权限接口，只能是Map类型参数");
    // 10 04 xx: 参数功能
    ErrorCode PARAMS_GET_ERROR = new ErrorCode(100400,"获取参数失败");
    // 10 05 xx: 定时任务
    ErrorCode JOB_ERROR = new ErrorCode(100500,"定时任务失败");
}
