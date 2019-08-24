package com.xmlframework.entity.check;

import com.xmlframework.entity.common.XmlReqHeader;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "req")
@XmlType(propOrder = { "header" })
public class CheckReq {

    @XmlElement(name = "header")
    private XmlReqHeader header;
    @XmlElement(name = "body")
    private CheckReqBody body;

    public CheckReq() {}

    public CheckReq(XmlReqHeader header, CheckReqBody body) {
        this.header = header;
        this.body = body;
    }

    @XmlTransient
    public XmlReqHeader getHeader() {
        return header;
    }

    public void setHeader(XmlReqHeader header) {
        this.header = header;
    }

    public CheckReqBody getBody() {
        return body;
    }

    public void setBody(CheckReqBody body) {
        this.body = body;
    }
}
