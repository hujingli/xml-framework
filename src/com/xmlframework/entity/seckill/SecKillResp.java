package com.xmlframework.entity.seckill;

import com.xmlframework.entity.common.XmlRespHeader;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "resp")
@XmlType(propOrder = { "header", "body" })
public class SecKillResp {

    @XmlElement(name = "header")
    private XmlRespHeader header;
    @XmlElement(name = "body")
    private SecKillRespBody body;

    public SecKillResp() {}

    public SecKillResp(XmlRespHeader header, SecKillRespBody body) {
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
    public SecKillRespBody getBody() {
        return body;
    }

    public void setBody(SecKillRespBody body) {
        this.body = body;
    }
}
