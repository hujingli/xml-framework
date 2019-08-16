package com.xmlframework.annoscan.entity;

/**
 * request封装体，当前仅存储reqCode，表示一个请求
 * 
 * @author cyvan
 *
 */
public class XmlReq {
	private String reqCode;

	public XmlReq(String code) {
		this.reqCode = code;
	}

	public String getReqCode() {
		return reqCode;
	}

	public void setReqCode(String reqCode) {
		this.reqCode = reqCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reqCode == null) ? 0 : reqCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		XmlReq other = (XmlReq) obj;
		if (reqCode == null) {
			if (other.reqCode != null)
				return false;
		} else if (!reqCode.equals(other.reqCode))
			return false;
		return true;
	}

}
