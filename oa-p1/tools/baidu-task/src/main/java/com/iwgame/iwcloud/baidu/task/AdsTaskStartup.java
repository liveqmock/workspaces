/****************************************************************
 *  系统名称  ： 'Flume-task System'
 *  文件名    ： ApplicationContextHelper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.baidu.task;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.iwgame.iwcloud.baidu.task.util.ApplicationContextHelper;
import com.iwgame.iwcloud.baidu.task.util.Version;

/**
 * 类说明
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-3-16 上午09:51:17
 */
public class AdsTaskStartup {
	static Logger logger = Logger.getLogger(AdsTaskStartup.class);
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("参数不正确,共需要一个参数;");
			return;
		}
		try {
			// 初始化
			PropertyConfigurator.configure(args[0]); // 日志文件
			ApplicationContextHelper.configSpringContextService();// 初始Spring服务

			// 打印版本
			Version.print();
			logger.info("Baidu-Fetcher Task System Startup...");

			while (true) {
				Thread.sleep(1);
			}
		} catch (Exception e) {
			logger.error("启动失败！\r" + e.getMessage());
		}
	}
	
}
