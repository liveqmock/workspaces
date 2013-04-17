/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： RmbCZNoticeTask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.worker.rmbcz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.wg1.service.XtaskService;

/**
 * 类说明
 * @简述： RMB充值监听
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-4-25 下午06:48:11
 */
public class RmbCZNoticeTask {
	private Logger logger = Logger.getLogger(RmbCZNoticeTask.class);

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
		logger.info("收到[RMB充值]请求: " + text);
		xtaskService.sendRmbCZMQMessage(text);
		logger.info("本次[RMB充值奖励]完成! text:" + text);

	}
}
