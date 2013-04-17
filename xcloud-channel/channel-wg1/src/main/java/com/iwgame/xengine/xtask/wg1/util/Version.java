/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.util;

import org.apache.log4j.Logger;

/**
 * 类说明
 * @简述： 版本信息描述类 
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-8 下午04:47:06
 */
public class Version {
	private static Logger logger = Logger.getLogger(Version.class);
	private static String version = "1.0.2";
	private static String lastModifyTime = "2012.05.10 13:10";
	private static String updateInfo = "新增了首次充值赠送,宝石,装备赠送,优化了错误情况下的备用通道处理,提高容错处理。";
	
	public static void print() {
		logger.info("===============================");
		logger.info("名称：MQ消息任务系统-石器部落");
		logger.info("版本号: " + version);
		logger.info("更新关键点: " + updateInfo);
		logger.info("最后修改时间: " + lastModifyTime);
		logger.info("===============================");
	}
}
