/****************************************************************
 *  系统名称  ： 'Flume采集系统MQ消息处理'
 *  文件名    ： TaskStartup.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.task;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.iwgame.iwcloud.flume.task.spring.ApplicationContextHelper;
import com.iwgame.iwcloud.flume.task.util.Version;

/**
 * 类说明
 * 
 * @简述： 启动类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-8 下午04:41:09
 */
public class TaskStartup {
	static Logger logger = Logger.getLogger(TaskStartup.class);

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
			logger.info("[蜀门&醉逍遥]业务日志监控系统启动...");

			while (true) {
				Thread.sleep(1);
			}
		} catch (Exception e) {
			logger.error("启动失败！\r" + e.getMessage());
		}
	}

}
