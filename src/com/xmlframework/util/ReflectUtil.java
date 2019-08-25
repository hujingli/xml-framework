package com.xmlframework.util;

import com.xmlframework.handler.XmlHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ReflectUtil {

	/**
	 * 简单类型
	 */
	private static List<Class<?>> commonClz = Arrays.asList(byte.class, Byte.class, short.class, Short.class,
			char.class, Character.class, int.class, Integer.class, boolean.class, Boolean.class, float.class,
			Float.class, long.class, Long.class, double.class, Double.class, String.class);

	/**
	 * invoke method
	 * 
	 * @throws Exception e
	 */
	public static Object invokeMethod(Class<?> clz, Method method, String xmlStr) throws Exception {
		Object obj = clz.newInstance();

		// controller 层介于socket与用户逻辑之间，所以controller的方法仅接受一个参数，即xmlString
		Parameter[] params = method.getParameters();
		if (params.length > 1) {
			throw new Exception("xml service should have only 1 param");
		}
		
		Parameter param = params[0];
		// 完成object的封装
		Class<?> paramType = param.getType();
		Object methodParam = XmlHandler.xmlStringToObject(xmlStr, paramType);
		Object ret = method.invoke(obj, methodParam);

		return ret;
	}

	/**
	 * 根据 type T 返回一个 T 的对象，通过 map 的 key 与 T 的属性名称完全匹配来构造
	 * (这么做，好像有点驴，构造map的时间都把对象new完了)
	 * 
	 * @param <T> from class
	 * @param clz to class
	 * @param map mapping
	 * @return T.class
	 * @throws IllegalAccessException e
	 * @throws InstantiationException e
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mockObject(Class<T> clz, Map<String, Object> map)
			throws InstantiationException, IllegalAccessException {
		T t = clz.newInstance();
		for (Field field : clz.getDeclaredFields()) {
			field.setAccessible(true);
			Object matched = getMatchedObject(map, field.getName());
			// 未找到匹配属性
			if (matched == null) {
				continue;
			}
			Class<?> fieldClz = field.getType();
			// 简单对象直接赋值
			if (commonClz.contains(fieldClz)) {
				field.set(t, matched);
				// 复杂对象递归调用mock方法
			} else {
				field.set(t, mockObject(fieldClz, (Map<String, Object>) matched));
			}
		}
		return t;
	}

	/**
	 * 根据 field 的名称获取 map 中对应的 value
	 * 
	 * @param map mapping
	 * @param fieldName filed name
	 * @return obj
	 */
	public static Object getMatchedObject(Map<String, Object> map, String fieldName) {
		for (Entry<String, Object> entry : map.entrySet()) {
			if (fieldName.equals(entry.getKey())) {
				return entry.getValue();
			}
		}

		return null;
	}
}
