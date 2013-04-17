/****************************************************************
 *  系统名称  ：  '消息任务系统-Email服务-业务通道'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.email.tools.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @简述： 邮件发送工具类
 * 
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间： 2012-12-28 下午01:55:04
 */
public class EmailUtils {

	private final static Logger logger = Logger.getLogger(EmailUtils.class);

	/**
	 * 
	 * @param reg
	 *            正则表达式
	 * @param str
	 *            验证的字符串
	 * @return
	 */
	public static boolean regularExpCommon(String reg, String str) {
		if (reg != null) {
			Pattern pattern = Pattern.compile(reg);
			Matcher isNum = pattern.matcher(str.trim());
			if (!isNum.matches()) {
				logger.error("Email不合法,正则验证失败!不合法参数: [" + str + " ],忽略此条消息....");
				return false;
			}
			return true;
		} else {
			logger.error("正则验证失败，没有找到配置文件中正则表达式！ 请求验证资源:" + str);
			return false;
		}
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
