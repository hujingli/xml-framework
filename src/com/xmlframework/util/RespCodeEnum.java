package com.xmlframework.util;

/**
 * 任务完成结果枚举类
 * 
 * @author cyvan
 *
 */
public enum RespCodeEnum {
	RespSuccess("1001", "响应成功"), RespError("1002", "响应失败");

	private String code;
	private String msg;

	RespCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
