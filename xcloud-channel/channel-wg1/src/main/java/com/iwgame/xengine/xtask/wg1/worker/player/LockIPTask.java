/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： LockIPTask.java
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
 * @简述： 封禁IP
 * @作者： 刘锦峰
 * @版本： 1.0
 * @邮箱： liujinfeng@iwgame.com
 * @修改时间：2012-2-14 下午04:23:33
 */
public class LockIPTask extends AbstractMQWorker {
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
		logger.info("收到封禁IP请求: " + text);

		String memoString = "封禁IP操作";
		JSONObject jsonObject = JSONObject.fromObject(text);
		xtaskService.LockIPManager(jsonObject.getString("guid"), jsonObject.getString("ip")
				, jsonObject.getInt("operate"),jsonObject.getInt("validtime"), memoString);

		logger.info("本次封停IP完成! text:" + text);
	}
}