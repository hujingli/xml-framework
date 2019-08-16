package com.xmlframework.annoscan.handler;

import java.util.Set;
import java.util.stream.Collectors;

import com.log.ILog;
import com.log.LogTypeEnum;
import com.log.LoggerFactory;
import com.xmlframework.annoscan.anno.XmlController;
import com.xmlframework.annoscan.util.ClassUtil;
import com.xmlframework.annoscan.util.PropertiesUtil;

/**
 * 自动扫描类管理
 * 
 * @author cyvan
 *
 */
public class ClassHandler {
	
	private static ILog logger = LoggerFactory.getLogger(ClassHandler.class, LogTypeEnum.FILEWITHLOCK);

	/**
	 * 扫描到的类的集合
	 */
	private static final Set<Class<?>> CLASS_SET;

	/**
	 * 静态代码块，类加载时调用扫描逻辑
	 */
	static {
		String basePackage = PropertiesUtil.getPropByName("xml.basepackage");
		if ("".equals(basePackage)) {
			basePackage = "com.homework.socket.controller";
		}
		
		CLASS_SET = ClassUtil.getClassSet(basePackage);
		logger.info("自动扫描包 {}, 获取到 {} 个类", basePackage, CLASS_SET.size());
	}

	public static Set<Class<?>> getClassSet() {
		return CLASS_SET;
	}

	/**
	 * 获取包名下所有XmlController类（其实就是全部）
	 * 
	 * @return
	 */
	public static Set<Class<?>> getXmlControllerClassSet() {
		return CLASS_SET.stream().filter(cls -> cls.isAnnotationPresent(XmlController.class))
				.collect(Collectors.toSet());
	}

}
