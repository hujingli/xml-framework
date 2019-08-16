package com.xmlframework.entity.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "reqCode" })
public class XmlReqHeader {

	@XmlElement(name = "reqCode")
	private String reqCode;

	@XmlTransient
	public String getReqCode() {
		return reqCode;
	}

	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}
}