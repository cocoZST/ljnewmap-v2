package com.ljnewmap.common.utils;

import cn.hutool.core.util.StrUtil;
import com.ljnewmap.common.exception.ErrorCode;
import com.ljnewmap.common.exception.GlobalErrorCodeConstants;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * 响应数据
 *
 */
@Schema(description = "响应")
public class RT<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 编码：0表示成功，其他值表示失败
    */
    @Schema(description = "编码：0表示成功，其他值表示失败")
    private int code = 0;
    /**
     * 消息内容
    */
    @Schema(description = "消息内容")
    private String msg = "success";
    /**
     * 响应数据
    */
    @Schema(description = "响应数据")
    private T data;

    public RT<T> ok() {
        return this;
    }

    public RT<T> ok(T data) {
        this.setData(data);
        return this;
    }

    public RT<T> error() {
        ErrorCode errorCode = GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;
        this.code = errorCode.code();
        this.msg = errorCode.msg();
        return this;
    }

    public RT<T> error(String msg) {
        ErrorCode errorCode = GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;
        this.code = errorCode.code();
        this.msg = msg;
        return this;
    }

    public RT<T> error(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public RT<T> error(ErrorCode errorCode){
        this.code = errorCode.code();
        this.msg = errorCode.msg();
        return this;
    }

    public RT<T> error(ErrorCode errorCode,String... params) {
        this.code = errorCode.code();
        this.msg = StrUtil.indexedFormat(errorCode.msg(),params);
        return this;
    }

    public RT<T> ExRrror(ErrorCode errorCode,String msg) {
        this.code = errorCode.code();
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
