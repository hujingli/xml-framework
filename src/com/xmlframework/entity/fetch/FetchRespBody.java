package com.xmlframework.entity.fetch;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType(propOrder = { "nums", "rows" })
public class FetchRespBody {

    @XmlElement(name = "nums")
    private int nums;
    @XmlElement(name = "rows")
    private List<FetchItemWrapper> rows;

    public FetchRespBody() {}

    public FetchRespBody(int nums, List<FetchItemWrapper> rows) {
        this.nums = nums;
        this.rows = rows;
    }

    @XmlTransient
    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    @XmlTransient
    public List<FetchItemWrapper> getRows() {
        return rows;
    }

    public void setRows(List<FetchItemWrapper> rows) {
        this.rows = rows;
    }
}
