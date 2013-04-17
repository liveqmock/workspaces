/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： KickUserTask.java
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
 * @简述： 踢人队列监听
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-13 下午05:19:17
 */
public class KickUserTask extends AbstractMQWorker {
	private Logger logger = Logger.getLogger(LockAccountTask.class);

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
		logger.info("收到踢人请求: " + text);
	
		JSONObject jsonObject = JSONObject.fromObject(text);
		xtaskService.kickUser(jsonObject.getString("guid"), jsonObject.getInt("type"), jsonObject.getString("username"));
		
		logger.info("本次踢人完成! text:" + text);
	}
}

