package com.xmlframework.entity.add;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.xmlframework.entity.common.XmlReqHeader;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "req")
@XmlType(propOrder = { "header", "body" })
public class XmlAddActionReq {

	@XmlElement(name = "header")
	private XmlReqHeader header;
	@XmlElement(name = "body")
	private XmlAddActionReqBody body;

	public XmlReqHeader getHeader() {
		return header;
	}

	public void setHeader(XmlReqHeader header) {
		this.header = header;
	}

	public XmlAddActionReqBody getBody() {
		return body;
	}

	public void setBody(XmlAddActionReqBody body) {
		this.body = body;
	}

}
