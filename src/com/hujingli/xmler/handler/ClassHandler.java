package com.hujingli.xmler.handler;


import com.hujingli.xmler.annotation.XMLService;
import com.hujingli.xmler.util.ConfigUtil;
import com.hujingli.xmler.util.ClassUtil;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 类处理器
 */
public class ClassHandler {

    /**
     * 扫描到的类的集合
     */
    private static final Set<Class<?>> CLASS_SET;

    /**
     * 静态代码块，类加载时调用扫描逻辑
     */
    static {
        String basePackage = ConfigUtil.getString("xml.basepackage");
        if ("".equals(basePackage)) {
            basePackage = "com";
        }

        CLASS_SET = ClassUtil.scanClassByPackage(basePackage);
    }

    /**
     * 根据注解filter
     *
     * @return
     */
    public static Set<Class<?>> filter() {
        return CLASS_SET.stream().filter(cls -> cls.isAnnotationPresent(XMLService.class))
                .collect(Collectors.toSet());
    }

}
