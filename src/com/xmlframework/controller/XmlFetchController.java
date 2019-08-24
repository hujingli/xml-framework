package com.xmlframework.controller;

import com.xmlframework.annoscan.anno.XmlController;
import com.xmlframework.annoscan.anno.XmlService;
import com.xmlframework.entity.common.XmlRespHeader;
import com.xmlframework.entity.fetch.*;
import com.xmlframework.handler.StockHandler;
import com.xmlframework.handler.XmlHandler;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@XmlController
public class XmlFetchController {

    @XmlService(code = "2001")
    public String doFetch(FetchReq req) throws JAXBException {
        Map<String, Integer> map = StockHandler.getStock();


        FetchResp resp = new FetchResp();
        XmlRespHeader header = XmlHandler.genRespHeader(true, "");

        FetchRespBody body = new FetchRespBody();
        List<FetchItemWrapper> row = new ArrayList<>();

        int numCount = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            numCount++;
            row.add(new FetchItemWrapper(new FetchItem(entry.getKey(), entry.getValue())));
        }
        body.setNums(numCount);
        body.setRows(row);

        resp.setHeader(header);
        resp.setBody(body);

        return XmlHandler.objectToXmlString(resp);
    }

}
