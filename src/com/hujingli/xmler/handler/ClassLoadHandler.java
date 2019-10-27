package com.hujingli.xmler.handler;

import com.hujingli.xmler.util.ClassUtil;

public class ClassLoadHandler {

    /**
     * 完成类加载，完成静态代码块内逻辑
     */
    public static void init() {
        Class<?>[] classList = { ClassHandler.class, XMLServiceAnnotationHandler.class };

        for (Class<?> clz : classList) {
            ClassUtil.loadClass(clz.getName(), false);
        }
    }

}
