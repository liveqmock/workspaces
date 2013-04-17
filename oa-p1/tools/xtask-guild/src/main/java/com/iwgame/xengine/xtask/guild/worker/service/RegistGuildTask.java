/**      
 * RegistGuildTask.java Create on 2012-6-21     

 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.worker.service;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.guild.model.RegistGuild;
import com.iwgame.xengine.xtask.guild.service.FrontService;

/**
 * @ClassName: RegistGuildTask
 * @Description: 注册公会
 * @author 吴江晖
 * @date 2012-6-21 下午02:51:35
 * @Version 1.0
 * 
 */
public class RegistGuildTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(RegistGuildTask.class);

	private FrontService frontService;

	public void setFrontService(final FrontService frontService) {
		this.frontService = frontService;
	}

	public void handleMessage(final String text) {
		logger.info("========================");
		logger.info("从MQ服务器中获取注册公会消息");
		logger.info("消息为：" + text);
		try {
			RegistGuild registGuild = (RegistGuild) JSONObject.toBean(JSONObject.fromObject(text), RegistGuild.class);
			frontService.registGuild(registGuild);
			logger.info("公会数据存入数据库");
		} catch (Exception e) {
			logger.error("公会注册异常\n" + e);
		}
		logger.info("========================");
	}
}
