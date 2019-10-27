package com.hujingli.xmler.handler;

import com.hujingli.xmler.annotation.XMLService;
import com.hujingli.xmler.bean.TransDefine;

import java.util.HashMap;
import java.util.Map;

public class XMLServiceAnnotationHandler {

    private static final Map<String, TransDefine> ACTION_MAP = new HashMap<>();

    static {
        // 获取所有被注解描述的service类
        ClassHandler.filter().forEach(service -> {
                XMLService xmlService = service.getAnnotation(XMLService.class);
                ACTION_MAP.put(xmlService.code(), new TransDefine(service, xmlService.input(), xmlService.output()));
            }
        );
    }

    public static TransDefine getByRequestCode(String reqCode) {
        return ACTION_MAP.get(reqCode);
    }

}
