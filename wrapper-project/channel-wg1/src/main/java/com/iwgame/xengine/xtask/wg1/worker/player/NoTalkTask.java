/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： NoTalkTask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.worker.player;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.wg1.service.XtaskService;

/**
 * 类说明
 * 
 * @简述： 禁言
 * @作者： 刘锦峰
 * @版本： 1.0
 * @邮箱： liujinfeng@iwgame.com
 * @修改时间：2012-2-14 下午02:37:23
 */
public class NoTalkTask extends AbstractMQWorker {
	private Logger logger = Logger.getLogger(NoTalkTask.class);

	private XtaskService xtaskService;

	@Autowired
	public void setXtaskService(XtaskService xtaskService) {
		this.xtaskService = xtaskService;
	}

	/**
	 * 处理接受到的消息
	 * 
	 * @param text
	 */
	public void handleMessage(String text) {
		logger.info("收到禁言请求: " + text);

		String memoString = "玩家禁言操作";
		JSONObject jsonObject = JSONObject.fromObject(text);
		xtaskService.TalkManager(jsonObject.getString("guid"),
				jsonObject.getString("username"), jsonObject.getInt("operate"),
				jsonObject.getInt("validtime"), jsonObject.getString("msg"),
				memoString);

		logger.info("本次禁言完成! text:" + text);
	}
}