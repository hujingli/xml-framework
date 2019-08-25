package com.xmlframework.entity.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "reqCode", "reqDate", "reqTime" })
public class XmlReqHeader {
	@XmlElement(name = "reqCode")
	private String reqCode;
	@XmlElement(name = "reqDate")
	private String reqDate;
	@XmlElement(name = "reqTime")
	private String reqTime;

	public XmlReqHeader() {}

	public XmlReqHeader(String reqCode, String reqDate, String reqTime) {
		this.reqCode = reqCode;
		this.reqDate = reqDate;
		this.reqTime = reqTime;
	}

	@XmlTransient
	public String getReqCode() {
		return reqCode;
	}

	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	@XmlTransient
	public String getReqDate() {
		return reqDate;
	}

	public void setReqDate(String reqDate) {
		this.reqDate = reqDate;
	}

	@XmlTransient
	public String getReqTime() {
		return reqTime;
	}

	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}
}