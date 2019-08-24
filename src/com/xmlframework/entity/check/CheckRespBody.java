package com.xmlframework.entity.check;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"orderStatus"})
public class CheckRespBody {
    @XmlElement(name = "orderStatus")
    private String orderStatus;

    public CheckRespBody() {}

    public CheckRespBody(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @XmlTransient
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
