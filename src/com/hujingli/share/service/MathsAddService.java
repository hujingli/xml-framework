package com.hujingli.share.service;

import com.hujingli.share.bean.common.XMLRspHeader;
import com.hujingli.share.bean.service.add.XMLAddReq;
import com.hujingli.share.bean.service.add.XMLAddReqBody;
import com.hujingli.share.bean.service.add.XMLAddRsp;
import com.hujingli.share.bean.service.add.XMLAddRspBody;
import com.hujingli.share.enums.XMLDict;
import com.hujingli.xmler.annotation.XMLService;
import com.hujingli.xmler.bean.TransReqContext;
import com.hujingli.xmler.bean.TransRspContext;
import com.hujingli.xmler.core.AbsTransService;

@XMLService(code = "1001", input = XMLAddReq.class, output = XMLAddRsp.class)
public class MathsAddService extends AbsTransService<XMLAddReq, XMLAddRsp> {

    @Override
    public void valid(TransReqContext<XMLAddReq> req) {
        XMLAddReqBody body = req.getContext().getBody();
        if (body.getAdd1() < 0 || body.getAdd2() < 0) {
            System.out.println("至少有一个数小于0");
        }
    }

    @Override
    public void process(TransReqContext<XMLAddReq> req, TransRspContext<XMLAddRsp> rsp) {
        XMLAddReq xmlAddReq = req.getContext();
        XMLAddRsp xmlAddRsp = rsp.getContext();

        int add1 = xmlAddReq.getBody().getAdd1();
        int add2 = xmlAddReq.getBody().getAdd2();

        xmlAddRsp.setHeader(new XMLRspHeader(XMLDict.REQ_SUCCESS.getCode(), XMLDict.REQ_SUCCESS.getMsg()));
        xmlAddRsp.setBody(new XMLAddRspBody(add1 + add2));
    }
}
