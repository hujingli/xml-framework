package com.xmlframework.controller;

import com.xmlframework.annoscan.anno.XmlController;
import com.xmlframework.annoscan.anno.XmlService;
import com.xmlframework.entity.Order;
import com.xmlframework.entity.common.XmlRespHeader;
import com.xmlframework.entity.seckill.SecKillReq;
import com.xmlframework.entity.seckill.SecKillReqBody;
import com.xmlframework.entity.seckill.SecKillResp;
import com.xmlframework.entity.seckill.SecKillRespBody;
import com.xmlframework.handler.StockHandler;
import com.xmlframework.handler.XmlHandler;
import com.xmlframework.server.BIOServer;
import com.xmlframework.util.TimeUtil;

import javax.xml.bind.JAXBException;

@XmlController
public class XmlSecKillController {

    @XmlService(code = "2002")
    public String doSecKill(SecKillReq req) throws JAXBException {
        // 判断是否仍有商品
        String itemCode = req.getBody().getItemId();
        if (StockHandler.descStock(itemCode)) {
            XmlRespHeader respHeader = XmlHandler.genRespHeader(false, "商品已被全部抢购");
            SecKillResp resp = new SecKillResp();
            resp.setHeader(respHeader);

            return XmlHandler.objectToXmlString(resp);
        }

        // 将 order push进队列待消费
        SecKillReqBody body = req.getBody();
        Order order = new Order();

        order.setItemCode(itemCode);
        order.setOrderQty(body.getQty());
        order.setOrderSequence(body.getOrderSequence());
        order.setOrderUser(body.getOrderUser());

        order.setOrderDate(TimeUtil.getDate());
        order.setOrderTime(TimeUtil.getTime());

        boolean produce = BIOServer.orderHandler.produce(order);
        // 插入失败
        if (!produce) {
            XmlRespHeader respHeader = XmlHandler.genRespHeader(false, "订单生成失败");
            SecKillResp resp = new SecKillResp();
            resp.setHeader(respHeader);

            // 退还商品数量
            StockHandler.incrStock(itemCode);

            return XmlHandler.objectToXmlString(resp);
        }

        // 当前订单返回状态为处理中
        XmlRespHeader respHeader = XmlHandler.genRespHeader(true, "");
        SecKillRespBody respBody = new SecKillRespBody(body.getOrderSequence(), "01");
        SecKillResp resp = new SecKillResp(respHeader, respBody);

        return XmlHandler.objectToXmlString(resp);
    }
}
