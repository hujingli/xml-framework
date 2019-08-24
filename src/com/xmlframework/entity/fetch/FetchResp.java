package com.xmlframework.entity.fetch;

import com.xmlframework.entity.common.XmlRespHeader;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "resp")
@XmlType(propOrder = { "header", "body" })
public class FetchResp {

    @XmlElement(name = "header")
    private XmlRespHeader header;
    @XmlElement(name = "body")
    private FetchRespBody body;

    public FetchResp () {}

    public FetchResp(XmlRespHeader header, FetchRespBody body) {
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
    public FetchRespBody getBody() {
        return body;
    }

    public void setBody(FetchRespBody body) {
        this.body = body;
    }
}
