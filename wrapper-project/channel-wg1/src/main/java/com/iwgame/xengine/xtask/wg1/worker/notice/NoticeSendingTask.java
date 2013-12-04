/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： NoticeSendingTask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.worker.notice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.wg1.model.Notice;
import com.iwgame.xengine.xtask.wg1.service.XtaskService;
import com.iwgame.xengine.xtask.wg1.worker.operation.SystemSettingTask;

/**
 * 类说明
 * 
 * @简述： 公告发送处理监听
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-11 下午07:40:39
 */
public class NoticeSendingTask extends AbstractMQWorker {
	private Logger logger = Logger.getLogger(SystemSettingTask.class);

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
		logger.info("收到公告发送请求: " + text);
		Notice notice = new Notice(text);
		xtaskService.sendNotice(notice.getGuid(), notice.getType(), notice.getContent(), notice.getOperater());
	}
}
