package com.hujingli.share.bean.common;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "reqCode" })
public class XMLReqHeader {

    @XmlElement(name = "reqCode")
    private String reqCode;

    public XMLReqHeader() {
    }

    public XMLReqHeader(String reqCode) {
        this.reqCode = reqCode;
    }

    @XmlTransient
    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }
}