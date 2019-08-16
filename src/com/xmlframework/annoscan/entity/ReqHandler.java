package com.xmlframework.annoscan.entity;

import java.lang.reflect.Method;

/**
 * request handler 处理类 维护了request所应调用方法的类和方法信息，方便反射调用
 * 
 * @author cyvan
 *
 */
public class ReqHandler {

	private Class<?> controllerClass;

	private Method actionMethod;

	public ReqHandler(Class<?> controllerClass, Method method) {
		this.controllerClass = controllerClass;
		this.actionMethod = method;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Method getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(Method actionMethod) {
		this.actionMethod = actionMethod;
	}

}
