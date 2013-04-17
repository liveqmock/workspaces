/****************************************************************
 *  系统名称  ：  '消息任务系统-公共服务-业务通道'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.sms.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;

/**
 * 
 * 类说明
 * 
 * @简述： 加载自定义配置文件
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间：2012-4-17 下午02:47:27
 */
public class ConfigProperties {

	private static Log logger = LogFactory.getLog(ConfigProperties.class);

	private static Properties properties = new Properties();

	private Resource[] locations;

	private String fileEncoding = "UTF-8";

	private boolean ignoreResourceNotFound;

	public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
		this.ignoreResourceNotFound = ignoreResourceNotFound;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}

	public Resource[] getLocations() {
		return locations;
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}

	protected void init() throws IOException {

		if (this.locations != null) {

			for (Resource location : this.locations) {

				InputStream is = null;

				try {
					is = location.getInputStream();

					if (this.fileEncoding != null) {
						if (properties != null) {
							Reader reader = new InputStreamReader(is, this.fileEncoding);
							properties.load(reader);
							logger.info("Loading properties file from " + location);
						}
					} else {
						properties.load(is);
					}

				} catch (IOException ex) {
					if (this.ignoreResourceNotFound) {
						if (logger.isWarnEnabled()) {
							logger.warn("Could not load properties from " + location + ": " + ex.getMessage());
						}
					} else {
						throw ex;
					}
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		}
	}

	public static String getString(String key, String defaultValue) {
		return StringUtils.defaultIfEmpty(properties.getProperty(key), defaultValue);
	}

	public static List<String> getStringArray(String key) {
		List<String> list = new ArrayList<String>();
		if ("null".equals(getString(key))) {
			return list;
		} else {
			String[] strs = getString(key).split(",");
			for (String string : strs) {
				list.add(string);
			}
			return list;
		}
	}

	public static List<Integer> getIntegerArray(String key) {
		List<Integer> list = new ArrayList<Integer>();
		if ("null".equals(getString(key))) {
			return list;
		} else {
			String[] strs = getString(key).split(",");
			for (String string : strs) {
				list.add(Integer.valueOf(string));
			}
			return list;
		}
	}

	public static String getString(String key) {
		return String.valueOf(properties.get(key));
	}

	public static boolean getBoolean(String key) {
		return Boolean.valueOf(properties.get(key).toString());
	}

	public static int getShort(String key) {
		return Short.parseShort(properties.get(key).toString());
	}

	public static int getInteger(String key) {
		return Integer.parseInt(properties.get(key).toString());
	}

	public static long getLong(String key) {
		return Long.parseLong(properties.get(key).toString());
	}

	public static float getFloat(String key) {
		return Float.parseFloat(properties.get(key).toString());
	}

	public static double getDouble(String key) {
		return Double.parseDouble(properties.get(key).toString());
	}

	public static String createBizKey(String... params) {
		StringBuilder builder = new StringBuilder();
		for (String param : params) {
			builder.append(param);
			builder.append("-");
		}
		return builder.toString();
	}

	/**
	 * 去空格,换行,TAB
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (null == str) {
			return "esolving error!";
		}
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
