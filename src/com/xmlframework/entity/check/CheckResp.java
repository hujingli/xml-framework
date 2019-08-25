package com.xmlframework.entity.check;

import com.xmlframework.entity.common.XmlRespHeader;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "resp")
@XmlType(propOrder = { "header", "body" })
public class CheckResp {

    @XmlElement(name = "header")
    private XmlRespHeader header;
    @XmlElement(name = "body")
    private CheckRespBody body;

    public CheckResp() {}

    public CheckResp(XmlRespHeader header, CheckRespBody body) {
        this.header = header;
        this.body = body;
    }

    @XmlTransient
    public XmlRespHeader getHeader() {
        return header;
    }

    public void setHeader(XmlRespHeader header) {
        this.header = header;
    }

    @XmlTransient
    public CheckRespBody getBody() {
        return body;
    }

    public void setBody(CheckRespBody body) {
        this.body = body;
    }
}
