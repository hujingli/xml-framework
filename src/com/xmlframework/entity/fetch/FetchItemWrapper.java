package com.xmlframework.entity.fetch;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "row" })
public class FetchItemWrapper {

    public FetchItemWrapper() {}

    public FetchItemWrapper(FetchItem row) {
        this.row = row;
    }

    @XmlElement(name = "row")
    private FetchItem row;

    @XmlTransient
    public FetchItem getRow() {
        return row;
    }

    public void setRow(FetchItem row) {
        this.row = row;
    }
}
