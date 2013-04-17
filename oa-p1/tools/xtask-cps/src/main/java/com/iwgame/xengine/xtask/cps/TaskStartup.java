/****************************************************************
 *  系统名称  ： '消息任务系统-CPS'
 *  文件名    ： TaskStartup.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.cps;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.iwgame.xengine.xtask.core.spring.ApplicationContextHelper;
import com.iwgame.xengine.xtask.cps.context.Context;
import com.iwgame.xengine.xtask.cps.service.ContextService;
import com.iwgame.xengine.xtask.cps.util.Version;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 
 * @ClassName: TaskStartup
 * @Description: 任务启动类
 * @author 吴江晖
 * @date 2012-4-27 下午02:32:51
 * @Version 1.0
 * 
 */
public class TaskStartup {
	static Logger logger = Logger.getLogger(TaskStartup.class);

	public static void main(final String[] args) {
		if (args.length != 1) {
			System.out.println("参数不正确,共需要一个参数=>log4j.propertise配置文件的路径;");
			return;
		}
		try {
			// 初始化
			PropertyConfigurator.configure(args[0]); // 日志文件
			ApplicationContextHelper.configSpringContextService();// 初始Spring服务

			ContextService contextService = (ContextService) ApplicationContextHelper
					.getApplicationContext().getBean("cpsContextService");

			logger.info("[XTASK-CPS]-缓存系统全局变量");
			contextService.initializeGlobalConfig();

			logger.info("[XTASK-CPS]-缓存用户列表");
			contextService.initializeCache();

			logger.info("[XTASK-CPS]-缓存黑名单查找表");
			contextService.initializeCRLs(Context.get().getCRL());

			logger.info("[XTASK-CPS]-缓存媒体分成方案查找表");
			contextService.initializeProfitSharingPolicyTable();

			logger.info("[XTASK-CPS]-缓存停用媒体查找表");
			contextService.initializeDisableMedias();

			logger.info("[XTASK-CPS]-缓存停用链接查找表");
			contextService.initializeDisableLinks();

			// 打印版本
			Version.print();
			// 打印MQ连接信息
			printMQConnectionInfo();

			logger.info("[XTASK-CPS]-消息任务系统启动...");

			while (true) {
				Thread.sleep(1);
			}
		} catch (Exception e) {
			logger.error("启动失败！\r" + e.getMessage());
		}
	}

	private static void printMQConnectionInfo() {
		ConnectionFactory connectionFactor = (ConnectionFactory) ApplicationContextHelper
				.getApplicationContext().getBean("cpsRabbitMQSource");
		logger.info(connectionFactor.getClientProperties().toString());
		logger.info("[Host]:" + connectionFactor.getHost() + " [Port]:"
				+ connectionFactor.getPort() + " [VirtualHost]:"
				+ connectionFactor.getVirtualHost());
	}
}
