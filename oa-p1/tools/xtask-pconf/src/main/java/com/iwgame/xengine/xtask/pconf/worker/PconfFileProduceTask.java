/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： RmbCZNoticeTask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.pconf.worker;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.pconf.service.XtaskService;

/**
 * 类说明
 * @简述： Pconf文件生成监听
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-4-25 下午06:48:11
 */
public class PconfFileProduceTask {
	private Logger logger = Logger.getLogger(PconfFileProduceTask.class);

	private XtaskService xtaskService;

	@Autowired
	public void setXtaskService(XtaskService xtaskService) {
		this.xtaskService = xtaskService;
	}

	/**
	 * 处理接受到的消息
	 * 
	 * @param text
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws Exception
	 */
	public void handleMessage(String text) throws IOException, InterruptedException {
		logger.info("收到[配置变更]请求: " + text);
		logger.info("2秒后获取最新配置");
		Thread.sleep(2000);
		xtaskService.makeFile();
		logger.info("本次[配置变更]，配置信息生成完成! text:" + text);
		logger.info("\n");
	}
}
