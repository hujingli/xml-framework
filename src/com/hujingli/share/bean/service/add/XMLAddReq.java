package com.hujingli.share.bean.service.add;

import com.hujingli.share.bean.common.XMLReqHeader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "req")
@XmlType(propOrder = { "header", "body" })
public class XMLAddReq {

    @XmlElement(name = "header")
    private XMLReqHeader header;
    @XmlElement(name = "body")
    private XMLAddReqBody body;

    public XMLAddReq() {
    }

    public XMLAddReq(XMLReqHeader header, XMLAddReqBody body) {
        this.header = header;
        this.body = body;
    }

    public XMLReqHeader getHeader() {
        return header;
    }

    public void setHeader(XMLReqHeader header) {
        this.header = header;
    }

    public XMLAddReqBody getBody() {
        return body;
    }

    public void setBody(XMLAddReqBody body) {
        this.body = body;
    }

}