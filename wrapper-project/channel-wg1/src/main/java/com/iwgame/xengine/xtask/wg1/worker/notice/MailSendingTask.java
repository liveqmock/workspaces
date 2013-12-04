/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： MailSendingTask.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.worker.notice;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.wg1.model.Mail;
import com.iwgame.xengine.xtask.wg1.service.XtaskService;
import com.iwgame.xengine.xtask.wg1.worker.operation.SystemSettingTask;

/**
 * 类说明
 * 
 * @简述： 邮件发送处理线程
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-11 下午07:25:45
 */
public class MailSendingTask extends AbstractMQWorker {
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
		logger.info("收到邮件发送请求: " + text);
		Mail mail = new Mail(text);
		xtaskService.sendMail(mail.getGuid(), mail.getUsername(), mail.getMailtitle(), mail.getMailcontent(), mail.getOperater(),
				mail.getScope());
	}
}
