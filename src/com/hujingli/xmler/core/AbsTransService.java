package com.hujingli.xmler.core;

import com.hujingli.xmler.bean.TransReqContext;
import com.hujingli.xmler.bean.TransRspContext;

public abstract class AbsTransService<Req, Rsp> implements ITransService<Req, Rsp> {

    @Override
    public void execute(TransReqContext<Req> req, TransRspContext<Rsp> rsp) {
        valid(req);
        process(req, rsp);
    }

    public abstract void valid(TransReqContext<Req> req);

    public abstract void process(TransReqContext<Req> req, TransRspContext<Rsp> rsp);
}
