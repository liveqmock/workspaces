/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： RewardGemstoneGivingTask.java
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
 * @简述： 宝石赠送监听-备用
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-10 下午12:33:09
 */
public class RewardGemstoneGivingTask extends AbstractMQWorker {
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
	public void handleMessage(String text) {
		logger.info("收到[宝石赠送]请求: " + text);
		try {
			RewardGemstone rewardGemstone = new RewardGemstone(text);
			xtaskService.giveRewardGemstone(rewardGemstone);
		} catch (Exception e) {
			// 因为连接原因出错,则强制消息重发,我们把它放进备用通道来处理该类业务
			xtaskService.send2RewardGemstoneBackupMQ(text);
			logger.info("[backup]消息连接错误,放到[备用宝石赠送通道]处理,message:" + text);
		}
		logger.info("本次[宝石赠送]操作完成!\n");
	}
}
