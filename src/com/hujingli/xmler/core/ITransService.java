package com.hujingli.xmler.core;

import com.hujingli.xmler.bean.TransReqContext;
import com.hujingli.xmler.bean.TransRspContext;

public interface ITransService<Req, Rsp> {

    void execute(TransReqContext<Req> req, TransRspContext<Rsp> rsp);

}
