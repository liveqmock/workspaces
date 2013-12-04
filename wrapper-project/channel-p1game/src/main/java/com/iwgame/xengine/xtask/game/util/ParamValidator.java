/****************************************************************
 *  系统名称  ：  '消息任务系统-公共服务-业务通道'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.game.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * 类说明
 * 
 * @简述： 参数验证器
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-4-22 下午03:18:48
 */
public class ParamValidator {

	private final static Logger logger = Logger.getLogger(ParamValidator.class);

	/**
	 * 相关对象验证
	 * 
	 * @param obj
	 *            验证对象
	 * @param key
	 *            验证的字段集合key
	 * @return
	 */
	public static boolean validateParams(Object obj, String key) {

		boolean flag = true;

		Class<?> clazz = obj.getClass();

		List<String> fieldNames = ConfigProperties.getStringArray(key);

		try {

			if (fieldNames != null && fieldNames.size() > 0) {

				for (int i = 0; i < fieldNames.size(); i++) {
					// 得到字段名字
					String fieldName = fieldNames.get(i);
					// 字段第一个字母大写
					String firstLetter = fieldName.substring(0, 1).toUpperCase();
					// 获得和属性对应的getXXX()方法的名字
					String getMethodName = "get" + firstLetter + fieldName.substring(1);
					// 获得和属性对应的getXXX()方法
					Method getMethod = clazz.getMethod(getMethodName, new Class[] {});
					// 调用get方法
					Object reslut = getMethod.invoke(obj, new Object[] {});
					// 判断返回值是否为空或NULL
					if (reslut == null || StringUtils.isBlank(reslut.toString()) || "null".equals(reslut.toString())) {
						logger.error("请求资源验证出错,参数不合法!,必要字段[ " + fieldName + " ]为空或为NULL,忽略此条消息!");
						flag = false;
						break;
					}
				}
			} else {
				flag = false;
				logger.error("验证出错，没有读取到该对象的验证配置信息！");
			}
		} catch (SecurityException e) {
			logger.error("验证出错，异常信息：" + e);
			return false;
		} catch (NoSuchMethodException e) {
			logger.error("验证出错，异常信息：" + e);
			return false;
		} catch (IllegalArgumentException e) {
			logger.error("验证出错，异常信息：" + e);
			return false;
		} catch (IllegalAccessException e) {
			logger.error("验证出错，异常信息：" + e);
			return false;
		} catch (InvocationTargetException e) {
			logger.error("验证出错，异常信息：" + e);
			return false;
		} catch (StringIndexOutOfBoundsException e) {
			logger.error("验证出错，异常信息：" + e);
			return false;
		} catch (Exception e) {
			logger.error("验证出错，其他异常信息：" + e);
			return false;
		}
		return flag;
	}

	/**
	 * 将普通字符串转换成Hex编码字符串
	 * 
	 * @param dataCoding
	 *            反编码格式，15表示GBK编码，16表示UTF-8编码,Other:ISO8859-1编码
	 * @param hexStr
	 *            Hex编码字符串
	 * @return 普通字符串
	 */
	public static String encodeHexStr(int dataCoding, String message) {
		String hexStr = "";
		if (message != null) {
			try {
				if (dataCoding == 15) {
					hexStr = new String(Hex.encodeHex(message.getBytes("GBK"), false));
				} else if (dataCoding == 16) {
					hexStr = new String(Hex.encodeHex(message.getBytes("UTF-8"), false));
				} else {
					hexStr = new String(Hex.encodeHex(message.getBytes("ISO8859-1"),false));
				}
			} catch (Exception e) {
				logger.error("字符串转换成Hex编码异常：" + e.toString());
			}
		}
		return hexStr;
	}

	/**
	 * 将Hex编码字符串转换成普通字符串
	 * 
	 * @param dataCoding
	 *            反编码格式，15表示GBK编码，其他默认ISO8859-1编码
	 * @param hexStr
	 *            Hex编码字符串
	 * @return 普通字符串
	 */
	public static String decodeHexStr(int dataCoding, String hexStr) {
		String realStr = null;
		try {
			if (hexStr != null) {
				if (dataCoding == 15) {
					realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "GBK");
				} else if (dataCoding == 16) {
					realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "UTF-8");
				} else {
					realStr = new String(Hex.decodeHex(hexStr.toCharArray()), "ISO8859-1");
				}
			}
		} catch (Exception e) {
			logger.error("Hex编码转换字符串异常：" + e.toString());
		}

		return realStr;
	}
	
	/**
	 * <p>插入latin1字符集的Mysql数据库 字符需转换</p>
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String tranferEncode(String params){
		
		byte[] b;
		
		String result = "";
		
		if(params!=null){
			try {
				//字符转换成UTF-8
				result = new String(params.getBytes(),"UTF-8");
				b = result.getBytes("GBK");
				result = new String(b, "iso-8859-1");
			} catch (UnsupportedEncodingException e) {
				logger.error("字符 [ "+params+" ] 转码 \"iso-8859-1\" 错误!异常信息:",e);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(tranferEncode("STEASA342E1C550"));
	}
}
