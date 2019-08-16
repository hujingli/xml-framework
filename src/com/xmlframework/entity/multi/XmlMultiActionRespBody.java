package com.xmlframework.entity.multi;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "res" })
public class XmlMultiActionRespBody {

	@XmlElement(name = "res")
	private int res;

	@XmlTransient
	public int getRes() {
		return res;
	}

	public void setRes(int res) {
		this.res = res;
	}

}
