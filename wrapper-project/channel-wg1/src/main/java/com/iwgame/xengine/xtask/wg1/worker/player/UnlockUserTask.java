/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： UnlockUserTask.java
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
 * @简述： 解除解禁玩家角色监听
 * @作者： 刘锦峰
 * @版本： 1.0
 * @邮箱： liujinfeng@iwgame.com
 * @修改时间：2012-2-15 上午09:27:20
 */
public class UnlockUserTask extends AbstractMQWorker {
	private Logger logger = Logger.getLogger(UnlockUserTask.class);

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
		logger.info("收到解禁玩家角色请求: " + text);

		String memoString = "解禁玩家角色操作";
		JSONObject jsonObject = JSONObject.fromObject(text);
		xtaskService.LockUserManager(jsonObject.getString("guid"), jsonObject.getString("username")
				, jsonObject.getInt("operate"),jsonObject.getInt("validtime"), memoString);

		logger.info("本次解禁玩家角色完成! text:" + text);
	}
}
