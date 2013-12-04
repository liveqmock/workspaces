/****************************************************************
 *  系统名称  ： '[guild-backend]'
 *  文件名    ： CheckStringIsEmpty.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.server.util;

/**
 * 类说明
 * 
 * @简述： 字符串处理
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-6-20 下午04:20:57
 */
public class StringIsEmpty {

	/**
	 * 判断字符串是否null 如果为null 返回 一个空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String isEmpty(final String str) {
		String result = " ";
		if ((null != str) && (str.trim().length() > 0)) {
			result = str;
		}
		return result;
	}
}
