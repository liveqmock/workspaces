/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： RewardEquipmentBackupGivingTask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.worker.reward;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.wg1.model.RewardEquipment;
import com.iwgame.xengine.xtask.wg1.service.XtaskService;

/**
 * 类说明
 * @简述： 装备赠送监听-备用
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-5-10 下午12:30:16
 */
public class RewardEquipmentBackupGivingTask extends AbstractMQWorker {
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
			logger.info("收到[备用装备赠送]请求: " + text);
			RewardEquipment rewardEquipment = new RewardEquipment(text);
			xtaskService.giveRewardEquipment(rewardEquipment);
			logger.info("本次[备用装备赠送]操作完成!\n");
		} catch (Exception e) {
			logger.info("本次[备用装备赠送]操作完成,但是出现连接错误,10秒继续重试!\n");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ex) {
			}
			throw e;
		}
		
	}
}
