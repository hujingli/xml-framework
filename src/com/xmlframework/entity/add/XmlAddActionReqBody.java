package com.xmlframework.entity.add;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "add1", "add2" })
public class XmlAddActionReqBody {

	@XmlElement(name = "add1")
	private int add1;
	@XmlElement(name = "add2")
	private int add2;

	@XmlTransient
	public int getAdd1() {
		return add1;
	}

	public void setAdd1(int add1) {
		this.add1 = add1;
	}

	@XmlTransient
	public int getAdd2() {
		return add2;
	}

	public void setAdd2(int add2) {
		this.add2 = add2;
	}

}
