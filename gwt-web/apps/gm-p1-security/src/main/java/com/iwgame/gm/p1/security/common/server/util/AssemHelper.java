/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SomeHelper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.server.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * @简述： 将bean重新装配为Map对象
 * @作者： 张 扬
 * @版本： 1.0
 * @邮箱： zhang_yang@iwgame.com
 * @修改时间：2012-5-16 下午06:25:24
 */
public class AssemHelper {
	/**
	 * 最高获取的父类属性层级数
	 */
	private static final int MAX_LEVEL = 9;

	/**
	 * 根类名称
	 */
	private static final String ROOT_CLASS_NAME = "java.lang.Object";

	/**
	 * @简述: 数据装配-属性全部转换为String类型
	 * @param assemNull
	 *            是否装配null值,true:值为null的属性将会被装配
	 * @param assemEmpty
	 *            是否装配空值,true:值为empty的属性将会被装配
	 * @param containSuperProps
	 *            是否包含父类属性,最多三级,且不包含Object
	 * @return Map Object
	 */
	public static Map<String, String> assemMapStrData(Object obj,
			boolean assemNull, boolean assemEmpty, boolean containSuperProps) {
		Object[] fields = assemFieldArray(obj, containSuperProps);
		if (fields.length == 0) {
			return null;
		}
		Object value = null;
		String str;
		Map<String, String> map = new HashMap<String, String>();
		Field field;
		for (Object o : fields) {
			field = (Field) o;
			field.setAccessible(true);
			try {
				value = field.get(obj);
			} catch (Exception e) {
				// ignore
			}
			// 全部装配或者装配null
			if ((assemNull && assemEmpty) || assemNull) {
				map.put(field.getName(),
						value == null ? "" : String.valueOf(value));
			}
			// 装配空值
			else if (assemEmpty) {
				if (value != null) {
					map.put(field.getName(), String.valueOf(value));
				}
			}
			// 不装配空值或者null值
			else {
				if (value != null
						&& StringUtils.isNotEmpty(str = String.valueOf(value))) {
					map.put(field.getName(), str);
				}
			}
		}
		str = null;
		fields = null;
		value = null;
		return map;
	}

	/**
	 * @简述: 数据装配,java.util.Date类型转换为yyyy-MM-dd HH-mm-ss格式
	 * @param assemNull
	 *            是否装配null值,true:值为null的属性将会被装配
	 * @param assemEmpty
	 *            是否装配空值,true:值为empty的属性将会被装配
	 * @param containSuperProps
	 *            是否包含父类属性,最多三级,且不包含Object
	 * @return Map Object
	 */
	public static Map<String, Object> assemMapData(Object obj,
			boolean assemNull, boolean assemEmpty, boolean containSuperProps) {
		Object[] fields = assemFieldArray(obj, containSuperProps);
		if (fields.length == 0) {
			return null;
		}
		Object value = null;
		String str;
		Map<String, Object> map = new HashMap<String, Object>();
		Field field;
		for (Object o : fields) {
			field = (Field) o;
			field.setAccessible(true);
			try {
				value = field.get(obj);
				if (value != null) {
					//System.out.println("class "+value.getClass().toString());
				}
			} catch (Exception e) {
				// ignore
			}
			// 全部装配或者装配null
			if ((assemNull && assemEmpty) || assemNull) {
				map.put(field.getName(), value);
			}
			// 装配空值
			else if (assemEmpty) {
				if (value != null) {
					map.put(field.getName(), value);
				}
			}
			// 不装配空值或者null值
			else {
				if (value != null) {
					// String类型
					if ("java.lang.String".equals(value.getClass().toString())&& StringUtils.isNotEmpty(str = String
									.valueOf(value))) {
						map.put(field.getName(), str);
					} 
					else {
						map.put(field.getName(), value);
					}
				}
			}
		}
		str = null;
		fields = null;
		value = null;
		return map;
	}

	/**
	 * 
	 * @简述: 数据装配
	 * @param ignoreNull
	 *            是否忽略null值,true:值为null的属性将会被装配
	 * @param ignoreEmpty
	 *            是否忽略空值,true:值为empty的属性将会被装配
	 * @param containSuperProps
	 *            是否包含父类属性,最多三级,且不包含Object
	 * @return list object
	 */
	public static List<Object> assemListFieldData(Object obj,
			boolean ignoreNull, boolean ignoreEmpty, boolean containSuperProps) {
		Object[] fields = assemFieldArray(obj, containSuperProps);
		if (fields.length == 0) {
			return null;
		}
		Object value = null;
		List<Object> list = new ArrayList<Object>();
		Field field;
		for (Object o : fields) {
			field = (Field) o;
			field.setAccessible(true);
			try {
				value = field.get(obj);
			} catch (Exception e) {
				// ignore
			}
			// 全部忽略或者忽略null
			if ((ignoreNull && ignoreEmpty) || ignoreNull) {
				list.add(field.getName());
			}
			// 忽略空值
			else if (ignoreEmpty) {
				if (value != null) {
					list.add(field.getName());
				}
			}
			// 限制空值或者null值
			else {
				if (value != null
						&& StringUtils.isNotEmpty(String.valueOf(value))) {
					list.add(field.getName());
				}
			}
		}
		fields = null;
		value = null;
		return list;
	}

	/**
	 * @简述: 提取Field数组
	 * @param obj
	 * @param containSuperProps
	 * @return Object[]
	 */
	private static Object[] assemFieldArray(Object obj,
			boolean containSuperProps) {
		if (containSuperProps) {
			List<Field> tempList = new ArrayList<Field>();
			Field[] fields;
			int count = 0;
			Class<?> tempClass = obj.getClass();
			while (true) {
				fields = tempClass.getDeclaredFields();
				for (Field field : fields) {
					tempList.add(field);
				}
				tempClass = tempClass.getSuperclass();
				if (tempClass == null || count >= MAX_LEVEL
						|| ROOT_CLASS_NAME.equals(tempClass.getName())) {
					break;
				}
				count++;
			}
			return tempList.toArray();
		} else {
			return obj.getClass().getDeclaredFields();
		}
	}

}
