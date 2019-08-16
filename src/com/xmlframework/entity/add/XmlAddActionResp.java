package com.xmlframework.entity.add;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.xmlframework.entity.common.XmlRespHeader;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "resp")
@XmlType(propOrder = { "header", "body" })
public class XmlAddActionResp {

	@XmlElement(name = "header")
	private XmlRespHeader header;
	@XmlElement(name = "body")
	private XmlAddActionRespBody body;

	public XmlRespHeader getHeader() {
		return header;
	}

	public void setHeader(XmlRespHeader header) {
		this.header = header;
	}

	public XmlAddActionRespBody getBody() {
		return body;
	}

	public void setBody(XmlAddActionRespBody body) {
		this.body = body;
	}

}
