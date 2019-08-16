package com.xmlframework.annoscan.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface XmlService {
	/**
	 * 请求码，即业务逻辑码
	 * 
	 * @return
	 */
	String code();
}
