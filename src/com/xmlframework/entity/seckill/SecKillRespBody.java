package com.xmlframework.entity.seckill;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "orderSequence", "orderStatus" })
public class SecKillRespBody {

    @XmlElement(name = "orderSequence")
    private String orderSequence;
    @XmlElement(name = "orderStatus")
    private String orderStatus;

    public SecKillRespBody() {}

    public SecKillRespBody(String orderSequence, String orderStatus) {
        this.orderSequence = orderSequence;
        this.orderStatus = orderStatus;
    }

    @XmlTransient
    public String getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(String orderSequence) {
        this.orderSequence = orderSequence;
    }

    @XmlTransient
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
