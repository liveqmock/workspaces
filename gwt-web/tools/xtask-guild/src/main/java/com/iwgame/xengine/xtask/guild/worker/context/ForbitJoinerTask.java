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
import com.iwgame.xengine.xtask.guild.model.CachedJoiner;
import com.iwgame.xengine.xtask.guild.service.ContextService;

/**
 * @ClassName: ForbitJoinerTask
 * @Description: 禁用参赛者
 * @author 吴江晖
 * @date 2012-6-21 下午02:51:35
 * @Version 1.0
 * 
 */
public class ForbitJoinerTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(ForbitJoinerTask.class);

	private ContextService contextService;

	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	public void handleMessage(final String text) {
		logger.info("######################    禁用参赛者  开始  ######################");
		logger.info("从MQ服务器中获得禁用参赛者消息");
		logger.info("消息为：" + text);
		try {
			if (Context.get().hasOpenRace()) {
				DynaBean bean = (DynaBean) JSONObject.toBean(JSONObject.fromObject(text));
				String username = (String) bean.get("name");
				CachedJoiner joiner = contextService.getCachedJoiner(username, true);
				if (joiner != null) {
					joiner.setScore(0);
					joiner.setStatus(1);
					contextService.updateCachedJoiner(joiner);
				}
			}
		} catch (Exception e) {
			logger.error("禁用参赛者异常\n" + e);
		} finally {
			logger.info("######################    禁用参赛者  结束  ######################");
		}
	}
}
