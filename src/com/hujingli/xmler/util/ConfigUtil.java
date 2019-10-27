package com.hujingli.xmler.util;

import java.util.ResourceBundle;

public class ConfigUtil {

    private static final ResourceBundle resource = ResourceBundle.getBundle("xmler");

    /**
     * 根据name返回prop值
     *
     * @param name
     * @return
     */
    public static String getString(String name) {
        return resource.getString(name);
    }

}
