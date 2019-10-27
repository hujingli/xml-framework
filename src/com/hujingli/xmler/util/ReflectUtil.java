package com.hujingli.xmler.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReflectUtil {

    private static final Map<Class, Object> CLASS_BEAN = new ConcurrentHashMap<>();

    public static Object getBean(Class clz) throws IllegalAccessException, InstantiationException {
        if (CLASS_BEAN.get(clz) == null) {
            CLASS_BEAN.put(clz, clz.newInstance());
        }

        return CLASS_BEAN.get(clz);
    }


}
