/**      
 * RegistGuildTask.java Create on 2012-6-21     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.worker.data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.iwgame.xengine.xtask.core.worker.AbstractMQWorker;
import com.iwgame.xengine.xtask.guild.context.Context;
import com.iwgame.xengine.xtask.guild.model.CachedJoiner;
import com.iwgame.xengine.xtask.guild.model.CachedServer;
import com.iwgame.xengine.xtask.guild.service.ContextService;
import com.iwgame.xengine.xtask.guild.service.DataService;
import com.iwgame.xengine.xtask.guild.util.Tools;

/**
 * @ClassName: ActorTask
 * @Description: 参赛玩家角色数据
 * @author 吴江晖
 * @date 2012-6-21 下午02:51:35
 * @Version 1.0
 * 
 */
public class ActorTask extends AbstractMQWorker {

	private static final Logger logger = Logger.getLogger(ActorTask.class);

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
		logger.info("######################    角色数据计算  开始  ######################");
		logger.info("从MQ收到角色数据");
		logger.info("数据为：" + text);

		try {
			JSONObject json = JSONObject.fromObject(text);
			logger.debug("解析后JSON串为：" + json);
			String productId = Tools.getProductId(json.getString("channelId"));
			logger.debug("游戏产品ID=" + productId);
			String eventMsg = json.getString("eventMsg");
			JSONObject msg = JSONObject.fromObject(eventMsg);
			logger.info("解析Msg：" + msg);
			JSONObject params = json.getJSONObject("dynamicParameter");
			logger.info("解析dynamicParameter：" + params);

			String username = Tools.getUsername(msg.getString("user_name"), transform, encoding,
					Long.valueOf(offset, 16));
			String zone = params.getString("$zone");

			CachedJoiner joiner = contextService.getCachedJoiner(username, false);
			CachedServer server = contextService.getCachedServer(zone);
			if ((joiner != null) && (server != null)) {

				DateTime serverOpenDate = new DateTime(server.getOpenDate());
				if (serverOpenDate.isAfter(new Date().getTime())) {
					logger.info("服务器" + zone + "还未开启");
					logger.info("抛弃数据！");
					return;
				}
				if (server.getStatus() == 0) {
					logger.info("服务器" + zone + "不参与");
					logger.info("抛弃数据！");
					return;
				}

				int level = Integer.valueOf(msg.getString("level"));// 大本营等级
				int incrScore = 0;
				if ((joiner.getStatus() == 9) && (level > joiner.getLevel())) {
					logger.info("等级变动，计算积分");
					logger.debug("积分计算公式=" + Context.get().getRace().getFormula());
					Map<String, Object> p = new HashMap<String, Object>();
					p.put("等级", level);
					int score = Tools.calculateJoinerScore(Context.get().getRace().getFormula(), p);
					logger.debug("原等级=" + joiner.getLevel() + ";新等级=" + level);
					logger.debug("原积分=" + joiner.getScore() + ";新积分=" + score);
					incrScore = score - joiner.getScore();
					joiner.setLevel(level);
					joiner.setScore(score);
					contextService.updateCachedJoiner(joiner);
				} else {
					logger.info("不计算积分！状态为：" + joiner.getStatus());
					logger.info("原等级=" + joiner.getLevel() + ";新等级=" + level);
				}

				dataService.updateActor(productId, username, zone, "", joiner.getLevel(), joiner.getScore(), incrScore);
			} else {
				logger.info("抛弃数据！");
			}

		} catch (Exception e) {
			logger.error("角色数据计算错误。" + e.getMessage(), e);
		} finally {
			logger.info("######################    角色数据计算  结束  ######################");
		}

	}
}
