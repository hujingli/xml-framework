package com.xmlframework.entity.multi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "multi1", "multi2" })
public class XmlMultiActionReqBody {

	@XmlElement(name = "multi1")
	private int multi1;
	@XmlElement(name = "multi2")
	private int multi2;

	@XmlTransient
	public int getMulti1() {
		return multi1;
	}

	public void setMulti1(int multi1) {
		this.multi1 = multi1;
	}

	@XmlTransient
	public int getMulti2() {
		return multi2;
	}

	public void setMulti2(int multi2) {
		this.multi2 = multi2;
	}

}
