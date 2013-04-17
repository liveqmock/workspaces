/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： TaskStartup.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.pconf;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.iwgame.xengine.xtask.core.spring.ApplicationContextHelper;
import com.iwgame.xengine.xtask.pconf.service.XtaskService;
import com.iwgame.xengine.xtask.pconf.util.Version;
import com.rabbitmq.client.ConnectionFactory;

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
			// 打印MQ连接信息
			printMQConnectionInfo();

			logger.info("[XTASK-PCONF]-消息任务系统启动...");
			logger.info("首次启动，准备更新Pconf文件。");
 
			makeFileOnFirstTime();
			
			logger.info("完成首次启动的配置文件生成!");
			logger.info("\n");
			
			while (true) {
				Thread.sleep(1);
			}
		} catch (Exception e) {
			logger.error("启动失败！\r" + e.getMessage());
		}
	}

	private static void printMQConnectionInfo() {
		ConnectionFactory connectionFactor = (ConnectionFactory) ApplicationContextHelper.getApplicationContext().getBean(
				"rabbitMQSource");
		logger.info(connectionFactor.getClientProperties().toString());
		logger.info("[Host]:" + connectionFactor.getHost() + " [Port]:" + connectionFactor.getPort() + " [VirtualHost]:"
				+ connectionFactor.getVirtualHost());
	}
	
	private static void makeFileOnFirstTime() throws IOException {
		XtaskService xtaskService = (XtaskService) ApplicationContextHelper.getApplicationContext().getBean(
				"xtaskService");
		xtaskService.makeFile();
	}
}
