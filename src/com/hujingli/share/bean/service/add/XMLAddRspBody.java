package com.hujingli.share.bean.service.add;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "res" })
public class XMLAddRspBody {

    @XmlElement(name = "res")
    private int res;

    public XMLAddRspBody() {
    }

    public XMLAddRspBody(int res) {
        this.res = res;
    }

    @XmlTransient
    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

}