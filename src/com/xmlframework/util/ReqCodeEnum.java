package com.xmlframework.util;

public enum ReqCodeEnum {
	// 加法
	ActionAdd("1001"),
	// 乘法
	ActionMulti("1002");

	private String code;

	ReqCodeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}