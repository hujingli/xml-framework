package com.xmlframework.entity.seckill;

import com.xmlframework.entity.common.XmlReqHeader;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "req")
@XmlType(propOrder = { "header", "body" })
public class SecKillReq {

    @XmlElement(name = "header")
    private XmlReqHeader header;
    @XmlElement(name = "body")
    private SecKillReqBody body;

    public SecKillReq() {}

    public SecKillReq(XmlReqHeader header, SecKillReqBody body) {
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

    @XmlTransient
    public SecKillReqBody getBody() {
        return body;
    }

    public void setBody(SecKillReqBody body) {
        this.body = body;
    }
}
