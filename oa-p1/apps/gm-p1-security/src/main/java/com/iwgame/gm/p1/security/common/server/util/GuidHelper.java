/** * GuidHelper.java Create on 2012-5-2 * * Copyright (c) 2012 by GreenShore Network * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.) */
package com.iwgame.gm.p1.security.common.server.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.math.RandomUtils;

/**
 * @简述： 获取全局唯一标识
 * @作者： 张 扬
 * @版本： 1.0
 * @邮箱： zhang_yang@iwgame.com
 * @修改时间 2012-5-2 上午09:49:56
 */
public class GuidHelper {

	private static int RANDOM_MAX = 999;

	private static SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	/**
	 * 用0补全随机生成的不足7位数的数字
	 */
	private static DecimalFormat df = new DecimalFormat("0000000");

	/**
	 * @简述: 获取全局唯一标识
	 * @return String
	 */
	public static String getGUID() {
		return UUID.randomUUID().toString();
	}

	/**
	 * @简述: 获取批次编号
	 * @return String
	 */
	public static String getBatchId() {
		return sf.format(new Date())
				+ df.format(RandomUtils.nextInt(RANDOM_MAX));
	}
}
