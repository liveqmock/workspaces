/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： RewardGemstoneBackupGivingTask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.worker.reward;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.wg1.model.RewardGemstone;
import com.iwgame.xengine.xtask.wg1.service.XtaskService;

/**
 * 类说明
 * @简述： 宝石赠送监听
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-10 下午12:35:57
 */
public class RewardGemstoneBackupGivingTask extends AbstractMQWorker {
	private Logger logger = Logger.getLogger(RewardGivingTask.class);

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
			logger.info("收到[备用宝石赠送]请求: " + text);
			RewardGemstone rewardGemstone = new RewardGemstone(text);
			xtaskService.giveRewardGemstone(rewardGemstone);
			logger.info("本次[备用宝石赠送]操作完成!\n");
		} catch (Exception e) {
			logger.info("本次[备用宝石赠送]操作完成,但是出现连接错误,10秒继续重试!\n");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
			}
			throw e;
		}
		
	}
}
