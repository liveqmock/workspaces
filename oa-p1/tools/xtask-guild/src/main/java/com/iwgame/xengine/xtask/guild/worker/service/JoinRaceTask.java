/**      
 * RegistGuildTask.java Create on 2012-6-21     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.worker.service;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;
import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.guild.service.FrontService;

/**
 * @ClassName: JoinRaceTask
 * @Description: 玩家参赛
 * @author 吴江晖
 * @date 2012-6-21 下午02:51:35
 * @Version 1.0
 * 
 */
public class JoinRaceTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(JoinRaceTask.class);

	private FrontService frontService;

	public void setFrontService(final FrontService frontService) {
		this.frontService = frontService;
	}

	public void handleMessage(final String text) {
		logger.info("######################    玩家注册  开始  ######################");
		logger.info("从MQ服务器中参加公会赛消息");
		logger.info("消息为：" + text);
		try {
			DynaBean bean = (DynaBean) JSONObject.toBean(JSONObject.fromObject(text));
			if (frontService.canJoinRace((String) bean.get("gameId"), (Integer) bean.get("guildId"))) {
				frontService.joinRace((String) bean.get("gameId"), (Integer) bean.get("guildId"),
						(String) bean.get("username"), (Long) bean.get("regTime"));
				logger.info("参赛者数据存入数据库");
			}
			// if (Context.get().hasOpenRace()) {
			// DynaBean bean = (DynaBean)
			// JSONObject.toBean(JSONObject.fromObject(text));
			// frontService.joinRace((String) bean.get("gameId"), (Integer)
			// bean.get("guildId"),
			// (String) bean.get("username"), (Long) bean.get("regTime"));
			// logger.info("参赛者数据存入数据库");
			// } else {
			// logger.info("当前没有开启的公会赛，用户被抛弃！");
			// }

		} catch (Exception e) {
			logger.error("公会赛参与异常\n" + e);
		} finally {
			logger.info("######################    玩家注册  结束  ######################");
		}
	}
}
