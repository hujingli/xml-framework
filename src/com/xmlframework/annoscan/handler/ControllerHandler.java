package com.xmlframework.annoscan.handler;

import com.log.ILog;
import com.log.LogTypeEnum;
import com.log.LoggerFactory;
import com.xmlframework.annoscan.anno.XmlService;
import com.xmlframework.annoscan.entity.ReqHandler;
import com.xmlframework.annoscan.entity.XmlReq;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 使得每一个xml的req都与一个handler对应 即一个reqCode对应一个类中的一个方法
 * 
 * @author cyvan
 *
 */
public class ControllerHandler {
	
	private static ILog logger = LoggerFactory.getLogger(ControllerHandler.class, LogTypeEnum.FILEWITHLOCK);

	private static final Map<XmlReq, ReqHandler> ACTION_MAP = new HashMap<>();

	static {
		// 获取所有被注解描述的controller类
		Set<Class<?>> controllerClassSet = ClassHandler.getXmlControllerClassSet();
		if (controllerClassSet != null && !controllerClassSet.isEmpty()) {
			for (Class<?> controllerClass : controllerClassSet) {
				Method[] methods = controllerClass.getDeclaredMethods();
				if (methods != null && methods.length > 0) {
					for (Method method : methods) {
						// 遍历出被注解描述的service方法
						if (method.isAnnotationPresent(XmlService.class)) {
							XmlService action = method.getAnnotation(XmlService.class);
							XmlReq req = new XmlReq(action.code());
							ReqHandler handler = new ReqHandler(controllerClass, method);
							// 配对cache
							ACTION_MAP.put(req, handler);
						}
					}
				}
			}
		}
		
		logger.info("匹配 XmlController.XmlService 与 reqCode");
	}

	public static ReqHandler getHandler(String reqCode) {
		return ACTION_MAP.get(new XmlReq(reqCode));
	}
}
