/****************************************************************
 *  文件名     ： LatinConverter.java
 *  日期         :  2012-7-27
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.util;

import java.io.UnsupportedEncodingException;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.log4j.Logger;

/**
 * @ClassName: LatinStringHandler
 * @Description: TODO(拉丁转换器)
 * @author: 吴君杰
 * @email: wujunjie@iwgame.com
 * @date: 2012-7-27下午12:25:58
 * @Version: 1.0
 */
public class LatinConverter implements JsonValueProcessor{
	
	private final static Logger logger = Logger.getLogger(LatinConverter.class);

	/**
	 * 中文转换拉丁
	 * @param params
	 * @return
	 */
	public static String tranferEncode_latin1(String params) {

		byte[] b;

		String result = "";

		if (params != null) {
			try {
				// 字符转换成UTF-8
				result = new String(params.getBytes(), "UTF-8");
				b = result.getBytes("GBK");
				result = new String(b, "iso-8859-1");
			} catch (UnsupportedEncodingException e) {
				logger.error("字符 [ " + params + " ] 转码 \"iso-8859-1\" 错误!异常信息:", e);
			}
		}
		return result;
	}
	
	/**
	 * 拉丁转换中文
	 * @param params
	 * @return
	 */
	public static String tranferEncode_zh(String params) {
		
		byte[] b;
		
		String result = "";
		if (params != null) {
			try {
				result = new String(params.getBytes(), "UTF-8");
				b = result.getBytes("iso-8859-1");
				result = new String(b, "GBK");
			} catch (UnsupportedEncodingException e) {
				logger.error("字符 [ " + params + " ] 转码 \"iso-8859-1\" 错误!异常信息:", e);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processArrayValue(Object value, JsonConfig config) {
		return process("",value);
	}

	/* (non-Javadoc)
	 * @see net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, net.sf.json.JsonConfig)
	 */
	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		return process(key,value);
	}
	
	
	/**
	 * 处理latin
	 * @param key
	 * @param value
	 * @return
	 */
	private Object process(String key,Object value) {
		if("name".equals(key)){
			return tranferEncode_zh(value.toString());
		}
		return value;
    }
}
