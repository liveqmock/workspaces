/****************************************************************
 *  文件名     ： GetFiledValue.java
 *  日期         :  2012-8-14
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xvalidators.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;

/**
 * 
 * @类名:   GetFiledValue 
 * @描述:  	通过反射得到某个字段的值处理类
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-8-14下午06:08:16
 * @版本:   1.0
 */
public final class GetFiledValue {
	
	private final static Logger LOGGER = Logger.getLogger(GetFiledValue.class);
	
	/**
	 * <p>
	 * 	通过反射得到某个字段的值
	 * </p>
	 * @param targetObj
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(IwAnnotation targetObj,String fieldName){
		Object value = null;
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getMethodName = "get" + firstLetter + fieldName.substring(1);
			Method getMethod = targetObj.getClass().getMethod(getMethodName);
			getMethod.setAccessible(true);
			value = getMethod.invoke(targetObj);
		} catch (SecurityException ex) {
			LOGGER.error("Get Field Value Fail! ex msg:",ex);
		} catch (NoSuchMethodException ex) {
			LOGGER.error("Get Field Value Fail! ex msg:",ex);
		} catch (IllegalArgumentException ex) {
			LOGGER.error("Get Field Value Fail! ex msg:",ex);
		} catch (IllegalAccessException ex) {
			LOGGER.error("Get Field Value Fail! ex msg:",ex);
		} catch (InvocationTargetException ex) {
			LOGGER.error("Get Field Value Fail! ex msg:",ex);
		} catch (Exception ex){
			LOGGER.error("Get Field Value Fail! oex msg:",ex);
		}
		return value;
	}
}
