package com.xmlframework.entity.fetch;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "itemId", "qty" })
public class FetchItem {

    @XmlElement(name = "itemId")
    private String itemId;
    @XmlElement(name = "qty")
    private int qty;

    public FetchItem() {}

    public FetchItem(String itemId, int qty) {
        this.itemId = itemId;
        this.qty = qty;
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
}
