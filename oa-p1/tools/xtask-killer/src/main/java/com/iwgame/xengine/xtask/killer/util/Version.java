/****************************************************************
 *  系统名称  ： '消息任务系统-CPS'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.killer.util;

import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: Version
 * @Description: 版本信息描述类
 * @author 吴江晖
 * @date 2012-4-27 下午02:34:03
 * @Version 1.0
 * 
 */
public class Version {

	private static Logger logger = Logger.getLogger(Version.class);

	private static String version = "1.0.0";

	private static String lastModifyTime = "2012.07.10 10:30";

	public static void print() {
		logger.info("===============================");
		logger.info("名称：MQ消息任务系统xtask-killer项目 ");
		logger.info("版本号: " + version);
		logger.info("最后修改时间: " + lastModifyTime);
		logger.info("===============================");
	}
}
