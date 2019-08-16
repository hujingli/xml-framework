package com.xmlframework.entity.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "respCode", "respMsg" })
public class XmlRespHeader {

	@XmlElement(name = "respCode")
	private String respCode;
	@XmlElement(name = "respMsg")
	private String respMsg;

	public XmlRespHeader(String respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

	@XmlTransient
	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	@XmlTransient
	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
}
