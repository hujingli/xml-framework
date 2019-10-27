package com.hujingli.share.bean.service.add;

import com.hujingli.share.bean.common.XMLRspHeader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "resp")
@XmlType(propOrder = { "header", "body" })
public class XMLAddRsp {

    @XmlElement(name = "header")
    private XMLRspHeader header;
    @XmlElement(name = "body")
    private XMLAddRspBody body;

    public XMLRspHeader getHeader() {
        return header;
    }

    public void setHeader(XMLRspHeader header) {
        this.header = header;
    }

    public XMLAddRspBody getBody() {
        return body;
    }

    public void setBody(XMLAddRspBody body) {
        this.body = body;
    }

}