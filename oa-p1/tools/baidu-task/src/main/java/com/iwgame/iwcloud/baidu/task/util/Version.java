/****************************************************************
 *  系统名称  ： 'Flume-task System'
 *  文件名    ： ApplicationContextHelper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.baidu.task.util;

import org.apache.log4j.Logger;

/**
 * 类说明
 * 
 * @简述： 版本信息
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-3-16 上午09:51:59
 */
public class Version {
	private static Logger logger = Logger.getLogger(Version.class);
	private static String version = "1.0.2";
	private static String lastModifyTime = "2012.06.30 23:10";

	public static void print() {
		logger.info("===============================");
		logger.info("名称：Baidu Task System");
		logger.info("版本号: " + version);
		logger.info("最后修改时间: " + lastModifyTime);
		logger.info("===============================");
	}
}
