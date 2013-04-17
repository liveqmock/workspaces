/**      
 * RegistGuildTask.java Create on 2012-6-21     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.worker.context;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.guild.service.ContextService;

/**
 * @ClassName: CloseRaceTask
 * @Description: 归档公会赛
 * @author 吴江晖
 * @date 2012-6-21 下午02:51:35
 * @Version 1.0
 * 
 */
public class CloseRaceTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(CloseRaceTask.class);

	private ContextService contextService;

	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	public void handleMessage(final String text) {
		logger.info("######################    归档公会赛  开始  ######################");
		logger.info("从MQ服务器中获得归档公会赛消息");
		logger.info("消息为：" + text);
		try {
			contextService.cleanCache();
		} catch (Exception e) {
			logger.error("归档公会赛异常\n" + e);
		} finally {
			logger.info("######################    归档公会赛 结束  ######################");
		}
	}
}
