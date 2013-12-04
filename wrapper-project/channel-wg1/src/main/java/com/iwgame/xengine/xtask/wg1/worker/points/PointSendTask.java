/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： PointSendTask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.worker.points;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.wg1.model.UserPoints;
import com.iwgame.xengine.xtask.wg1.service.XtaskService;

/**
 * 类说明
 * 
 * @简述： 积分处理监听程序
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午02:29:37
 */
public class PointSendTask extends AbstractMQWorker {
	private Logger logger = Logger.getLogger(PointSendTask.class);

	private XtaskService xtaskService;

	@Autowired
	public void setXtaskService(XtaskService xtaskService) {
		this.xtaskService = xtaskService;
	}

	/**
	 * 处理接受到的消息
	 * 
	 * @param text
	 * @throws Exception
	 */
	public void handleMessage(String text) {
		logger.info("收到积分加点请求: " + text);
		UserPoints userPoints = new UserPoints(text);
		xtaskService.givePoints(userPoints);
		logger.info("本次积分加点完成! text:" + text);

	}
}
