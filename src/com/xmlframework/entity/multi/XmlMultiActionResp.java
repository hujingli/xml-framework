package com.xmlframework.entity.multi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.xmlframework.entity.common.XmlRespHeader;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "resp")
@XmlType(propOrder = { "header", "body" })
public class XmlMultiActionResp {

	@XmlElement(name = "header")
	private XmlRespHeader header;
	@XmlElement(name = "body")
	private XmlMultiActionRespBody body;

	public XmlRespHeader getHeader() {
		return header;
	}

	public void setHeader(XmlRespHeader header) {
		this.header = header;
	}

	public XmlMultiActionRespBody getBody() {
		return body;
	}

	public void setBody(XmlMultiActionRespBody body) {
		this.body = body;
	}

}
