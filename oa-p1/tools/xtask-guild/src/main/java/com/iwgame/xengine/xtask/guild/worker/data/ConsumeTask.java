/**      
 * RegistGuildTask.java Create on 2012-6-21     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.worker.data;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.guild.model.CachedJoiner;
import com.iwgame.xengine.xtask.guild.service.ContextService;
import com.iwgame.xengine.xtask.guild.service.DataService;
import com.iwgame.xengine.xtask.guild.util.Tools;

/**
 * @ClassName: ConsumeTask
 * @Description: 参赛玩家消费记录数据
 * @author 吴江晖
 * @date 2012-6-21 下午02:51:35
 * @Version 1.0
 * 
 */
public class ConsumeTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(ConsumeTask.class);

	private ContextService contextService;

	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	private DataService dataService;

	public void setDataService(final DataService dataService) {
		this.dataService = dataService;
	}

	private String encoding;

	public void setEncoding(final String encoding) {
		this.encoding = encoding;
	}

	private boolean transform;

	public void setTransform(final boolean transform) {
		this.transform = transform;
	}

	private String offset;

	public void setOffset(final String offset) {
		this.offset = offset;
	}

	public synchronized void handleMessage(final String text) {
		logger.info("######################    消费数据计算  开始  ######################");
		logger.info("从MQ收到消费数据");
		logger.info("数据为：" + text);

		try {
			JSONObject json = JSONObject.fromObject(text);
			logger.debug("解析后JSON串为：" + json);
			String productId = Tools.getProductId(json.getString("channelId"));
			logger.debug("游戏产品ID=" + productId);
			String eventMsg = json.getString("eventMsg");
			JSONObject msg = JSONObject.fromObject(eventMsg);
			logger.info("解析EventMsg：" + msg);

			String username = Tools.getUsername(msg.getString("user_name"), transform, encoding,
					Long.valueOf(offset, 16));

			CachedJoiner joiner = contextService.getCachedJoiner(username, false);
			if (joiner != null) {
				double money = Double.valueOf(msg.getString("gold")) / 10.0;
				dataService.addConsume(productId, username, money);
			} else {
				logger.info("抛弃数据！");
			}

		} catch (Exception e) {
			logger.error("消费数据计算错误。" + e.getMessage(), e);
		} finally {
			logger.info("######################    消费数据计算  结束  ######################");
		}

	}
}
