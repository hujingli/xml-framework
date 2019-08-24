package com.xmlframework.util;

public enum ReqCodeEnum {
	// fetch
	ActionFetch("2001"),
	// sec kill
	ActionSecKill("2002"),
	// check
	ActionCheck("2003");

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