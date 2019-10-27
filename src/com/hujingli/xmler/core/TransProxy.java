package com.hujingli.xmler.core;

import com.hujingli.xmler.bean.TransDefine;
import com.hujingli.xmler.bean.TransReqContext;
import com.hujingli.xmler.bean.TransRspContext;
import com.hujingli.xmler.handler.XMLServiceAnnotationHandler;
import com.hujingli.xmler.util.MessageUtil;
import com.hujingli.xmler.util.ReflectUtil;

import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransProxy {

    @SuppressWarnings("unchecked")
    public static String call(Socket socket) throws Exception {
        // get data
        String data = MessageUtil.checkoutData(socket.getInputStream());

        // process
        String requestCode = extractCode(data);
        TransDefine transDefine = XMLServiceAnnotationHandler.getByRequestCode(requestCode);

        Object inputBean = MessageUtil.xmlStringToObject(data, transDefine.getInputType());
        Object outputBean = transDefine.getOutputType().newInstance();

        TransReqContext transReqContext = new TransReqContext(inputBean);
        TransRspContext transRspContext = new TransRspContext(outputBean);

        ITransService transService = (ITransService) ReflectUtil.getBean(transDefine.getService());
        transService.execute(transReqContext, transRspContext);

        return MessageUtil.objectToXmlStringWithPrefix(transRspContext.getContext());
    }

    private static String extractCode(String data) {

        String pattern = "(<reqCode>)(.*?)(</reqCode>)";

        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(data);

        if (m.find()) {
            return m.group(2);
        }

        return "";
    }

}
