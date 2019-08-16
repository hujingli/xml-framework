package com.xmlframework.controller;

import javax.xml.bind.JAXBException;

import com.xmlframework.annoscan.anno.XmlController;
import com.xmlframework.annoscan.anno.XmlService;
import com.xmlframework.entity.common.XmlRespHeader;
import com.xmlframework.entity.multi.XmlMultiActionReq;
import com.xmlframework.entity.multi.XmlMultiActionResp;
import com.xmlframework.entity.multi.XmlMultiActionRespBody;
import com.xmlframework.handler.XmlHandler;
import com.xmlframework.util.RespCodeEnum;

@XmlController
public class XmlMultiController {

	@XmlService(code = "1002")
	public String doMulti(XmlMultiActionReq req) throws JAXBException {
		int res = req.getBody().getMulti1() * req.getBody().getMulti2();
		
		// 封装返回对象
		XmlMultiActionResp resp = new XmlMultiActionResp();

		XmlRespHeader header = new XmlRespHeader(RespCodeEnum.RespSuccess.getCode(), RespCodeEnum.RespSuccess.getMsg());

		XmlMultiActionRespBody body = new XmlMultiActionRespBody();
		body.setRes(res);

		resp.setHeader(header);
		resp.setBody(body);

		return XmlHandler.objectToXmlString(resp);
	}

}
