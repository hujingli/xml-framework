package com.xmlframework.entity.seckill;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = { "itemId", "qty", "orderUser", "orderSequence" })
public class SecKillReqBody {

    @XmlElement(name = "itemId")
    private String itemId;
    @XmlElement(name = "qty")
    private int qty;
    @XmlElement(name = "orderUser")
    private String orderUser;
    @XmlElement(name = "orderSequence")
    private String orderSequence;

    public SecKillReqBody() {}

    public SecKillReqBody(String itemId, int qty, String orderUser, String orderSequence) {
        this.itemId = itemId;
        this.qty = qty;
        this.orderUser = orderUser;
        this.orderSequence = orderSequence;
    }

    @XmlTransient
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    @XmlTransient
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
    @XmlTransient
    public String getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(String orderUser) {
        this.orderUser = orderUser;
    }
    @XmlTransient
    public String getOrderSequence() {
        return orderSequence;
    }

    public void setOrderSequence(String orderSequence) {
        this.orderSequence = orderSequence;
    }
}
