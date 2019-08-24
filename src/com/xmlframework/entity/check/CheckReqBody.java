package com.xmlframework.entity.check;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"orderSequence"})
public class CheckReqBody {

    @XmlElement(name = "orderSequence")
    private String orderSequence;

    public CheckReqBody() {}

    public CheckReqBody(String orderSequence) {
        this.orderSequence = orderSequence;
    }

    @XmlTransient
    public String getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(String orderSequence) {
        this.orderSequence = orderSequence;
    }
}
