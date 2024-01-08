package com.ljnewmap.common.exception;

public class ErrorCode {
    private Integer code;
    private String msg;

    public Integer code(){
        return this.code;
    }

    public String msg(){
        return this.msg;
    }

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }
}
