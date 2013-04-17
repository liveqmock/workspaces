/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： RewardPetGivingtask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.worker.reward;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.wg1.model.RewardPet;
import com.iwgame.xengine.xtask.wg1.service.XtaskService;

/**
 * 类说明
 * @简述： 赠送宠物监听程序
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-5-28 下午04:54:25
 */
public class RewardPetGivingTask extends AbstractMQWorker {
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
		logger.info("收到[宠物赠送]请求: " + text);
		try {
			RewardPet rewardPet = new RewardPet(text);
			xtaskService.giveRewardPet(rewardPet);
		} catch (Exception e) {
			// 因为连接原因出错,则强制消息重发,我们把它放进备用通道来处理该类业务
			xtaskService.send2RewardPetBackupMQ(text);
			logger.info("[backup]消息连接错误,放到[备用宠物赠送通道]处理,message:" + text);
		}
		logger.info("本次[宠物赠送]操作完成!\n");
	}
}

