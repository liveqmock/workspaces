/****************************************************************
 * 系统名称 ： '消息任务系统-公共服务-业务通道'
 * 文件名 ： Version.java
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * **************************************************************
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.channel.data.handler.tools;

import org.apache.log4j.Logger;

/**
 * @简述： 版本信息描述类
 * 
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间： 2013-01-18
 */
public class Version {

	private static Logger logger = Logger.getLogger(Version.class);

	private static String version = "1.0.0";

	private static String lastModifyTime = "2013.01.18 09:10";

	public static void print() {
		logger.info("===============================");
		logger.info("名称：数据处理程序");
		logger.info("版本号: " + version);
		logger.info("最后修改时间: " + lastModifyTime);
		logger.info("===============================");
	}
}
