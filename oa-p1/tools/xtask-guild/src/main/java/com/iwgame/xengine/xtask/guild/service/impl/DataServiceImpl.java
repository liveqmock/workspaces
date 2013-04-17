/**      
 * DataServiceImpl.java Create on 2012-6-26     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.iwgame.xengine.xtask.guild.context.Context;
import com.iwgame.xengine.xtask.guild.model.CachedJoiner;
import com.iwgame.xengine.xtask.guild.service.BaseService;
import com.iwgame.xengine.xtask.guild.service.ContextService;
import com.iwgame.xengine.xtask.guild.service.DataService;

/**
 * @ClassName: DataServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-26 下午02:57:56
 * @Version 1.0
 * 
 */
public class DataServiceImpl extends BaseService implements DataService {

	private static final Logger logger = Logger.getLogger(DataServiceImpl.class);

	private ContextService contextService;

	@Autowired
	public void setContextService(final ContextService contextServiceImpl) {
		contextService = contextServiceImpl;
	}

	@Override
	public int addConsume(final String productId, final String username, final double money) {
		logger.info("累加" + username + "的消费数据：" + money);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("money", money);
		int r = update(productId, "guild.xtask.addConsume", params);
		r = updateTotalConsume(productId, username, money);
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.guild.service.DataService#addPaid(java.lang.
	 * String, java.lang.String, double)
	 */
	@Override
	public int addPaid(final String productId, final String username, final double money) {
		logger.info("累加" + username + "的充值数据：" + money);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("money", money);
		int r = update(productId, "guild.xtask.addPaid", params);
		r = updateTotalPaid(productId, username, money);
		return r;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.guild.service.DataService#updateLogin(java.lang
	 * .String, java.lang.String, java.lang.String, java.util.Date, long)
	 */
	@Override
	public int updateLogin(final String productId, final String username, final Date lastLogin, final long onlineTime) {
		logger.info("更新" + username + "的登录数据：【最后登录时间：" + lastLogin + "】【在线时长增加：" + onlineTime + "秒】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("lastLogin", lastLogin);
		params.put("onlineTime", onlineTime);
		return update(productId, "guild.xtask.updateLogin", params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.guild.service.DataService#updateActor(java.lang
	 * .String, java.lang.String, java.lang.String, java.lang.String, int)
	 */
	@Override
	public int updateActor(final String productId, final String username, final String zone, final String group,
			final int level, final int score, final int incrScore) {
		logger.info("更新" + username + "的角色数据：【大区：" + zone + "】【组：" + group + "】【等级：" + level + "】【积分：" + score + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", username);
		params.put("zone", zone);
		params.put("group", group);
		params.put("level", level);
		params.put("score", score);
		int r = update(productId, "guild.xtask.updateActor", params);

		r = updateTotalScore(productId, username, incrScore);

		return r;
	}

	private synchronized int updateTotalScore(final String productId, final String username, final int score) {
		CachedJoiner joiner = contextService.getCachedJoiner(username, true);
		logger.info("更新总积分：【RaceId：" + Context.get().getRace().getId() + "】【GuildId：" + joiner.getGuildId() + "】【新增积分："
				+ score + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("guildId", joiner.getGuildId());
		params.put("score", score);
		int r = update(productId, "guild.xtask.updateGuildScore", params);
		if (r > 0) {
			contextService.updateRank(joiner.getGuildId(), score);
		}
		return r;
	}

	private synchronized int updateTotalPaid(final String productId, final String username, final double paid) {
		CachedJoiner joiner = contextService.getCachedJoiner(username, true);
		logger.info("更新总充值：【RaceId：" + Context.get().getRace().getId() + "】【GuildId：" + joiner.getGuildId() + "】【新增充值："
				+ paid + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("guildId", joiner.getGuildId());
		params.put("paid", paid);

		return update(productId, "guild.xtask.updateGuildPaid", params);
	}

	private synchronized int updateTotalConsume(final String productId, final String username, final double consume) {
		CachedJoiner joiner = contextService.getCachedJoiner(username, true);
		logger.info("更新总消费：【RaceId：" + Context.get().getRace().getId() + "】【GuildId：" + joiner.getGuildId() + "】【新增消费："
				+ consume + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("guildId", joiner.getGuildId());
		params.put("consume", consume);

		return update(productId, "guild.xtask.updateGuildConsume", params);
	}

}
