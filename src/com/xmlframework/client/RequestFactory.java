package com.xmlframework.client;

import com.xmlframework.entity.check.CheckReq;
import com.xmlframework.entity.check.CheckReqBody;
import com.xmlframework.entity.common.XmlReqHeader;
import com.xmlframework.entity.fetch.FetchReq;
import com.xmlframework.entity.seckill.SecKillReq;
import com.xmlframework.entity.seckill.SecKillReqBody;
import com.xmlframework.util.ReqCodeEnum;
import com.xmlframework.util.TimeUtil;

public class RequestFactory {

    public static FetchReq genFetchReq() {
        XmlReqHeader header = genHeaderByCode(ReqCodeEnum.ActionFetch.getCode());
        return new FetchReq(header);
    }

    public static SecKillReq genSecKillReq(String user, String itemId) {
        XmlReqHeader header = genHeaderByCode(ReqCodeEnum.ActionSecKill.getCode());

        String sequence = String.format("%s%d", user, System.currentTimeMillis());
        SecKillReqBody body = new SecKillReqBody(itemId, 1, user, sequence);

        return new SecKillReq(header, body);
    }

    public static CheckReq genCheckReq(String sequence) {
        XmlReqHeader header = genHeaderByCode(ReqCodeEnum.ActionCheck.getCode());
        CheckReqBody body = new CheckReqBody(sequence);

        return new CheckReq(header, body);
    }

    private static XmlReqHeader genHeaderByCode(String code) {
        return new XmlReqHeader(code, TimeUtil.getDate(), TimeUtil.getTime());
    }

}
