package com.xmlframework.controller;

import com.jdbc.OrderDao;
import com.xmlframework.annoscan.anno.XmlController;
import com.xmlframework.annoscan.anno.XmlService;
import com.xmlframework.entity.Order;
import com.xmlframework.entity.check.CheckReq;
import com.xmlframework.entity.check.CheckResp;
import com.xmlframework.entity.check.CheckRespBody;
import com.xmlframework.entity.common.XmlRespHeader;
import com.xmlframework.handler.XmlHandler;
import com.xmlframework.server.BIOServer;

import javax.xml.bind.JAXBException;
import java.sql.SQLException;

@XmlController
public class XmlCheckController {

    @XmlService(code = "2003")
    public String doCheck(CheckReq req) throws JAXBException {
        String sequence = req.getBody().getOrderSequence();

        // 查内存
        if (BIOServer.orderHandler.checkSequence(sequence)) {
            // 待处理
            XmlRespHeader respHeader = XmlHandler.genRespHeader(true, "");
            CheckRespBody respBpdy = new CheckRespBody("01");
            CheckResp resp = new CheckResp(respHeader, respBpdy);

            return XmlHandler.objectToXmlString(resp);
        }

        // 查db
        try {
            Order order = OrderDao.getBySequence(sequence);
            XmlRespHeader respHeader = XmlHandler.genRespHeader(true, "");
            CheckRespBody respBpdy = null;
            if (order != null) {
                respBpdy = new CheckRespBody(System.out.format("%02d", order.getOrderStatus()).toString());
            } else {
                respBpdy = new CheckRespBody("03");
            }
            CheckResp resp = new CheckResp(respHeader, respBpdy);
            return XmlHandler.objectToXmlString(resp);
        } catch (SQLException e) {
            e.printStackTrace();
            XmlRespHeader respHeader = XmlHandler.genRespHeader(false, "服务端异常");
            CheckResp resp = new CheckResp();
            resp.setHeader(respHeader);
            return XmlHandler.objectToXmlString(resp);
        }
    }

}
