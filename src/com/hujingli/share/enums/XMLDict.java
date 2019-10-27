package com.hujingli.share.enums;

public enum XMLDict {

    REQ_SUCCESS("9999", "响应成功"),
    REQ_FAILED("9998", "响应失败");

    private String code;
    private String msg;

    XMLDict(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return  this.msg;
    }

}
