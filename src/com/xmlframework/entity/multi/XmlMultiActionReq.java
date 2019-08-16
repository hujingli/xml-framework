package com.xmlframework.entity.multi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.xmlframework.entity.common.XmlReqHeader;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "req")
@XmlType(propOrder = { "header", "body" })
public class XmlMultiActionReq {

	@XmlElement(name = "header")
	private XmlReqHeader header;
	@XmlElement(name = "body")
	private XmlMultiActionReqBody body;

	public XmlReqHeader getHeader() {
		return header;
	}

	public void setHeader(XmlReqHeader header) {
		this.header = header;
	}

	public XmlMultiActionReqBody getBody() {
		return body;
	}

	public void setBody(XmlMultiActionReqBody body) {
		this.body = body;
	}

}
