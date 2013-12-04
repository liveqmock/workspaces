/**      
 * FrontServiceImpl.java Create on 2012-6-26     
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
import com.iwgame.xengine.xtask.guild.model.RegistGuild;
import com.iwgame.xengine.xtask.guild.service.BaseService;
import com.iwgame.xengine.xtask.guild.service.ContextService;
import com.iwgame.xengine.xtask.guild.service.FrontService;

/**
 * @ClassName: FrontServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-26 上午09:30:34
 * @Version 1.0
 * 
 */
public class FrontServiceImpl extends BaseService implements FrontService {

	private static final Logger logger = Logger.getLogger(FrontServiceImpl.class);

	private ContextService contextService;

	@Autowired
	public void setContextService(final ContextService contextService) {
		this.contextService = contextService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.guild.service.FrontService#registGuild(com.iwgame
	 * .xengine.xtask.guild.model.RegistGuild)
	 */
	@Override
	public void registGuild(final RegistGuild registGuild) {
		insert(registGuild.getGameId(), "guild.xtask.registGuild", registGuild);
		logger.debug("向表guild_ext中插入公会基本信息，ID=" + registGuild.getGuildId());
	}

	@Override
	public boolean canJoinRace(final String gameId, final int guildId) {
		if (Context.get().hasOpenRace()) {
			int raceId = Context.get().getRace().getId();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("guildId", guildId);
			params.put("raceId", raceId);
			Map<String, Object> result = selectOne(gameId, "guild.xtask.canJoinRace", params);
			if (result == null) {
				logger.info("该公会还没有通过审核，用户被抛弃！");
				return false;
			} else {
				return true;
			}
		} else {
			logger.info("当前没有开启的公会赛，用户被抛弃！");
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.xengine.xtask.guild.service.FrontService#joinRace(int,
	 * java.lang.String)
	 */
	@Override
	public void joinRace(final String gameId, final int guildId, final String name, final long regTime) {

		if (Context.get().hasOpenRace()) {
			HashMap<String, Object> params = new HashMap<String, Object>();
			params.put("joinerId", "");
			params.put("guildId", guildId);
			params.put("name", name);
			params.put("regTime", new Date(regTime));
			insert(gameId, "guild.xtask.joinRace", params);
			logger.debug("向表guild_race_joiner中插入公会赛参赛者基本信息【ID=" + params.get("joinerId") + "】【name=" + name + "】");

			CachedJoiner joiner = new CachedJoiner();
			joiner.setRaceId(Context.get().getRace().getId());
			joiner.setName(name);
			joiner.setStatus(9);
			joiner.setGuildId(guildId);
			joiner.setLevel(0);
			joiner.setScore(0);
			joiner.setJoinerId((Integer) params.get("joinerId"));
			contextService.updateCachedJoiner(joiner);

			updateJoiners(gameId, joiner);
		}

	}

	private synchronized int updateJoiners(final String productId, final CachedJoiner joiner) {
		logger.info("更新参赛人数：【RaceId：" + Context.get().getRace().getId() + "】【GuildId：" + joiner.getGuildId() + "】");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("guildId", joiner.getGuildId());

		return update(productId, "guild.xtask.updateGuildJoiner", params);
	}
}
