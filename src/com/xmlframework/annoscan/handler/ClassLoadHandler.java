package com.xmlframework.annoscan.handler;

import com.xmlframework.annoscan.util.ClassUtil;

/**
 * 加载类，调用静态代码块内逻辑 autoscan的加载器，进行初始化操作
 * 
 * @author cyvan
 *
 */
public class ClassLoadHandler {

	/**
	 * 完成类加载，完成静态代码块内逻辑
	 */
	public static void init() {
		Class<?>[] classList = { ClassHandler.class, ControllerHandler.class };

		for (Class<?> clz : classList) {
			ClassUtil.loadClass(clz.getName(), false);
		}
	}

}
