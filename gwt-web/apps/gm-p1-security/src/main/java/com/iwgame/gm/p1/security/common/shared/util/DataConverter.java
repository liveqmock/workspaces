/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： DataConverter.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.util;

/**
 * @简述： 数据转换
 * @作者： 张 扬
 * @版本： 1.0
 * @邮箱： zhang_yang@iwgame.com
 * @修改时间：2012-9-26 下午02:50:56
 */
public class DataConverter {

	public static String obj2StrEmpty(Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return "";
	}

	public static String obj2StrNull(Object obj) {
		if (obj != null) {
			return obj.toString();
		}
		return null;
	}

	public static long double2Long(Double doub) {
		String longStr = RegexGwtHelper
				.extractBeforePoint(String.valueOf(doub));
		if (longStr.length() == 0) {
			return 0L;
		}
		return Long.parseLong(longStr);
	}
	
	public static int double2Int(Double doub) {
		String intStr = RegexGwtHelper
				.extractBeforePoint(String.valueOf(doub));
		if (intStr.length() == 0) {
			return 0;
		}
		return Integer.parseInt(intStr);
	}
}
