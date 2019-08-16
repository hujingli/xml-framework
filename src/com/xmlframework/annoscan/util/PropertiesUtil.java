package com.xmlframework.annoscan.util;

import java.util.ResourceBundle;

public class PropertiesUtil {

	private static final ResourceBundle resource = ResourceBundle.getBundle("xml-framework");
	
	/**
	 * 根据name返回prop值
	 * 
	 * @param name
	 * @return
	 */
	public static String getPropByName(String name) {
		return resource.getString(name);
	}

}
