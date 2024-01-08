package com.ljnewmap.common.exception;


import cn.hutool.core.util.StrUtil;
import com.ljnewmap.common.utils.MessageUtils;

/**
 * 自定义异常
 *
 */
public class RRException extends RuntimeException {
	private static final long serialVersionUID = 1L;

    private int code;
	private String msg;

	public RRException(ErrorCode errorCode){
		this.code = errorCode.code();
		this.msg = errorCode.msg();
	}

	public RRException(ErrorCode errorCode, String... params){
		this.code = errorCode.code();
		this.msg = StrUtil.indexedFormat(errorCode.msg(),params);
	}

	public RRException(ErrorCode errorCode, Throwable e) {
		super(e);
		this.code = errorCode.code();
		this.msg = errorCode.msg();
	}

	public RRException(ErrorCode errorCode, Throwable e, String... params) {
		super(e);
		this.code = errorCode.code();
		this.msg = StrUtil.indexedFormat(errorCode.msg(),params);
	}

	public RRException(String msg) {
		super(msg);
		ErrorCode errorCode = GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;
		this.code = errorCode.code();
		this.msg = msg;
	}

	public RRException(String msg, Throwable e) {
		super(msg, e);
		ErrorCode errorCode = GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;
		this.code = errorCode.code();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}