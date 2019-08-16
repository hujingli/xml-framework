package com.xmlframework.controller;

import javax.xml.bind.JAXBException;

import com.xmlframework.annoscan.anno.XmlController;
import com.xmlframework.annoscan.anno.XmlService;
import com.xmlframework.entity.add.XmlAddActionReq;
import com.xmlframework.entity.add.XmlAddActionResp;
import com.xmlframework.entity.add.XmlAddActionRespBody;
import com.xmlframework.entity.common.XmlRespHeader;
import com.xmlframework.handler.XmlHandler;
import com.xmlframework.util.RespCodeEnum;

@XmlController
public class XmlAddController {

	@XmlService(code = "1001")
	public String doAdd(XmlAddActionReq req) throws JAXBException {
		int res = req.getBody().getAdd1() + req.getBody().getAdd2();
		
		XmlAddActionResp resp = new XmlAddActionResp();

		XmlRespHeader header = new XmlRespHeader(RespCodeEnum.RespSuccess.getCode(), RespCodeEnum.RespSuccess.getMsg());

		XmlAddActionRespBody body = new XmlAddActionRespBody();
		body.setRes(res);

		resp.setHeader(header);
		resp.setBody(body);

		return XmlHandler.objectToXmlString(resp);
	}
	
}
