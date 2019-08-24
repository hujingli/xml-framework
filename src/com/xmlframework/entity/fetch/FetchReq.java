package com.xmlframework.entity.fetch;

import com.xmlframework.entity.common.XmlReqHeader;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "req")
@XmlType(propOrder = { "header" })
public class FetchReq {

    @XmlElement(name = "header")
    private XmlReqHeader header;
    public FetchReq() {}

    public FetchReq(XmlReqHeader header) {
        this.header = header;
    }

    @XmlTransient
    public XmlReqHeader getHeader() {
        return header;
    }

    public void setHeader(XmlReqHeader header) {
        this.header = header;
    }
}
