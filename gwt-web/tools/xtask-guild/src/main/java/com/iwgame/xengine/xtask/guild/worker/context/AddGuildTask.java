/**      
 * RegistGuildTask.java Create on 2012-6-21     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.worker.context;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.guild.context.Context;
import com.iwgame.xengine.xtask.guild.service.ContextService;

/**
 * @ClassName: AddGuildTask
 * @Description: 添加参赛公会
 * @author 吴江晖
 * @date 2012-6-21 下午02:51:35
 * @Version 1.0
 * 
 */
public class AddGuildTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(AddGuildTask.class);

	private ContextService contextService;

	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	public void handleMessage(final String text) {
		logger.info("######################    归档公会赛  开始  ######################");
		logger.info("从MQ服务器中获得添加参赛公会消息");
		logger.info("消息为：" + text);
		try {
			if (Context.get().hasOpenRace()) {
				DynaBean bean = (DynaBean) JSONObject.toBean(JSONObject.fromObject(text));
				Integer guildId = (Integer) bean.get("guildId");
				contextService.addRaceGuild(guildId);
			}
		} catch (Exception e) {
			logger.error("添加参赛公会异常\n" + e);
		} finally {
			logger.info("######################    归档公会赛 结束  ######################");
		}
	}
}
