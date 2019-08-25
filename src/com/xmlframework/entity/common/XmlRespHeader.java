package com.xmlframework.entity.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "respCode", "respMsg", "respDate", "respTime" })
public class XmlRespHeader {

	@XmlElement(name = "respCode")
	private String respCode;
	@XmlElement(name = "respMsg")
	private String respMsg;
	@XmlElement(name = "respDate")
	private String respDate;
	@XmlElement(name = "respTime")
	private String respTime;

	public XmlRespHeader() {}

	public XmlRespHeader(String respCode, String respMsg, String respDate, String respTime) {
		this.respCode = respCode;
		this.respMsg = respMsg;
		this.respDate = respDate;
		this.respTime = respTime;
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

	@XmlTransient
	public String getRespDate() {
		return respDate;
	}

	public void setRespDate(String respDate) {
		this.respDate = respDate;
	}

	@XmlTransient
	public String getRespTime() {
		return respTime;
	}

	public void setRespTime(String respTime) {
		this.respTime = respTime;
	}
}
