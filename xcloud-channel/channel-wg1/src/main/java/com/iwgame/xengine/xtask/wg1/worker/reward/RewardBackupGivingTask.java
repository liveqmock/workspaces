/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： RewardBackupGivingTask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.worker.reward;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.wg1.model.UserResouce;
import com.iwgame.xengine.xtask.wg1.service.XtaskService;

/**
 * 类说明
 * 
 * @简述： 备用发奖处理监听程序
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午02:29:37
 */
public class RewardBackupGivingTask extends AbstractMQWorker {
	private Logger logger = Logger.getLogger(RewardBackupGivingTask.class);

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
	public void handleMessage(String text) throws Exception {
		try {
			logger.info("收到[发奖-备用资源发放]请求: " + text);
			UserResouce userResouce = new UserResouce(text);
			xtaskService.giveGift(userResouce);
			logger.info("本次[发奖-备用资源发放]操作完成!\n");
		} catch (Exception e) {
			logger.info("本次[发奖-备用资源发放]操作完成,但是出现连接错误,10秒继续重试!\n");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
			}
			throw e;
		}
		
	}
}
