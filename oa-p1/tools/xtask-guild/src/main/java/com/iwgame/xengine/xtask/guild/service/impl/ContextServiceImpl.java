/**      
 * ContextServiceImpl.java Create on 2012-6-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.service.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import com.iwgame.xengine.xtask.guild.context.Context;
import com.iwgame.xengine.xtask.guild.model.CachedJoiner;
import com.iwgame.xengine.xtask.guild.model.CachedRank;
import com.iwgame.xengine.xtask.guild.model.CachedServer;
import com.iwgame.xengine.xtask.guild.model.Race;
import com.iwgame.xengine.xtask.guild.service.BaseService;
import com.iwgame.xengine.xtask.guild.service.ContextService;

/**
 * @ClassName: ContextServiceImpl
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-28 下午05:15:41
 * @Version 1.0
 * 
 */
public class ContextServiceImpl extends BaseService implements ContextService {

	private static final Logger logger = Logger.getLogger(ContextServiceImpl.class);

	private String productId;

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	private StringRedisTemplate guildRedisTemplate;

	@Autowired
	public void setGuildRedisTemplate(final StringRedisTemplate guildRedisTemplate) {
		this.guildRedisTemplate = guildRedisTemplate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.guild.service.ContextService#loadCurrentRace()
	 */
	@Override
	public void loadCurrentRace() {
		Race race = selectOne(productId, "guild.xtask.queryRace");
		if (race != null) {
			Context.get().setRace(race);
			logger.info("缓存当前开启公会赛信息：" + race);
			Context.get().setHasOpenRace(true);
		} else {
			logger.info("当前没有开启的公会赛！");
			Context.get().setHasOpenRace(false);
		}
	}

	private static final String CACHE_USER_KEY = "rid:{0}:joiner:{1}";
	private static final String CACHE_RANK_KEY = "race.guild.rank";
	private final MessageFormat keyFormat;

	public ContextServiceImpl() {
		super();
		keyFormat = new MessageFormat(CACHE_USER_KEY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.guild.service.ContextService#initializeCache()
	 */
	@Override
	public void initializeCache() {

		int raceId = Context.get().getRace().getId();

		logger.info("从数据库中查询所有的当前开启公会赛的参赛公会");
		List<CachedRank> cacheRank = selectList(productId, "guild.xtask.queryCurrentRaceGuild");
		logger.info("查询到参赛公会" + cacheRank.size() + "名");
		guildRedisTemplate.delete(CACHE_RANK_KEY);
		ZSetOperations<String, String> zsetOpt = guildRedisTemplate.opsForZSet();
		for (CachedRank rank : cacheRank) {
			zsetOpt.add(CACHE_RANK_KEY, Integer.toString(rank.getGuildId()), Integer.valueOf(rank.getScore())
					.doubleValue());
		}
		logger.info("参赛公会缓存完成；");
		logger.info("============================================");
		logger.info("从数据库中查询所有的当前开启公会赛的参赛用户");
		List<CachedJoiner> cacheJoiner = selectList(productId, "guild.xtask.queryRaceJoiner");
		logger.info("查询到参赛用户" + cacheJoiner.size() + "名");
		Set<String> joinerKeys = guildRedisTemplate.keys("rid:*:joiner:*");
		if ((joinerKeys != null) && !joinerKeys.isEmpty()) {
			guildRedisTemplate.delete(guildRedisTemplate.keys("rid:*:joiner:*"));
		}
		for (CachedJoiner joiner : cacheJoiner) {
			HashOperations<String, String, String> hashOpt = guildRedisTemplate.opsForHash();
			String key = keyFormat.format(new String[] { Integer.toString(raceId), joiner.getName() });
			hashOpt.put(key, "joinerId", Integer.toString(joiner.getJoinerId()));
			hashOpt.put(key, "guildId", Integer.toString(joiner.getGuildId()));
			hashOpt.put(key, "level", Integer.toString(joiner.getLevel()));
			hashOpt.put(key, "score", Integer.toString(joiner.getScore()));
			hashOpt.put(key, "status", Integer.toString(joiner.getStatus()));
		}
		logger.info("参赛用户缓存完成；");
		logger.info("============================================");
		logger.info("从数据库中查询所有的当前开启公会赛的参赛服务器");
		List<CachedServer> cachedServer = selectList(productId, "guild.xtask.queryRaceServer");
		logger.info("查询到参赛服务器" + cacheJoiner.size() + "个");
		Set<String> serverKeys = guildRedisTemplate.keys("rid:*:server:*");
		if ((serverKeys != null) && !serverKeys.isEmpty()) {
			guildRedisTemplate.delete(guildRedisTemplate.keys("rid:*:server:*"));
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (CachedServer server : cachedServer) {
			HashOperations<String, String, String> hashOpt = guildRedisTemplate.opsForHash();
			String key = keyFormat.format(new String[] { Integer.toString(raceId), server.getZone().toLowerCase() });
			hashOpt.put(key, "serverId", Integer.toString(server.getServerId()));
			hashOpt.put(key, "opendate", dateFormat.format(server.getOpenDate()));
			hashOpt.put(key, "status", Integer.toString(server.getStatus()));
		}
		logger.info("参赛服务器缓存完成；");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.xengine.xtask.guild.service.ContextService#cleanCache()
	 */
	@Override
	public void cleanCache() {
		logger.info("清空缓存中排行榜");
		guildRedisTemplate.delete(CACHE_RANK_KEY);
		logger.info("清空缓存中参赛用户");
		Set<String> keys = guildRedisTemplate.keys("rid:*:joiner:*");
		if ((keys != null) && !keys.isEmpty()) {
			guildRedisTemplate.delete(guildRedisTemplate.keys("rid:*:joiner:*"));
		}
		logger.info("清空缓存中参赛服务器");
		Set<String> serverKeys = guildRedisTemplate.keys("rid:*:server:*");
		if ((serverKeys != null) && !serverKeys.isEmpty()) {
			guildRedisTemplate.delete(guildRedisTemplate.keys("rid:*:server:*"));
		}
		logger.info("清空缓存中公会赛");
		Context.get().setHasOpenRace(false);
		Context.get().setRace(null);
	}

	@Override
	public CachedJoiner getCachedJoiner(final String name, final boolean forbit) {
		if (Context.get().hasOpenRace()) {
			DateTime startDate = new DateTime(Context.get().getRace().getStartDate());
			DateTime endDate = new DateTime(Context.get().getRace().getEndDate());
			long now = new Date().getTime();
			if (!forbit && startDate.isAfter(now) && endDate.isBefore(now)) {
				logger.debug("当前开启的公会赛的，开始时间为" + startDate.toString("yyyy-MM-dd") + "，结束时间为"
						+ endDate.toString("yyyy-MM-dd") + "！");
				return null;
			} else {
				HashOperations<String, String, String> hashOpt = guildRedisTemplate.opsForHash();
				int raceId = Context.get().getRace().getId();
				String key = keyFormat.format(new String[] { Integer.toString(raceId), name });
				Map<String, String> map = hashOpt.entries(key);
				if ((map != null) && !map.isEmpty()) {
					CachedJoiner joiner = new CachedJoiner(raceId, name, map);
					logger.debug("获取缓存参赛者信息：" + joiner);
					return joiner;
				} else {
					logger.debug("当前开启的公会赛中没有该参赛用户【" + name + "】！");
					return null;
				}
			}
		} else {
			logger.debug("当前没有开启的公会赛！");
			return null;
		}
	}

	@Override
	public CachedServer getCachedServer(final String zone) {
		if (Context.get().hasOpenRace()) {
			DateTime startDate = new DateTime(Context.get().getRace().getStartDate());
			if (startDate.isAfter(new Date().getTime())) {
				logger.debug("当前开启的公会赛还没有开始，开始时间为" + startDate.toString("yyyy-MM-dd") + "！");
				return null;
			} else {
				HashOperations<String, String, String> hashOpt = guildRedisTemplate.opsForHash();
				int raceId = Context.get().getRace().getId();
				String key = keyFormat.format(new String[] { Integer.toString(raceId), zone.toLowerCase() });
				Map<String, String> map = hashOpt.entries(key);
				if ((map != null) && !map.isEmpty()) {
					CachedServer server = new CachedServer(raceId, zone.toLowerCase(), map);
					logger.debug("获取缓存参赛服务器信息：" + server);
					return server;
				} else {
					logger.debug("当前开启的公会赛中没有该参赛服务器【" + zone.toLowerCase() + "】！");
					return null;
				}
			}
		} else {
			logger.debug("当前没有开启的公会赛！");
			return null;
		}
	}

	@Override
	public void updateCachedJoiner(final CachedJoiner joiner) {
		if (Context.get().hasOpenRace()) {
			HashOperations<String, String, String> hashOpt = guildRedisTemplate.opsForHash();
			int raceId = Context.get().getRace().getId();
			String key = keyFormat.format(new String[] { Integer.toString(raceId), joiner.getName() });
			hashOpt.put(key, "joinerId", Integer.toString(joiner.getJoinerId()));
			hashOpt.put(key, "level", Integer.toString(joiner.getLevel()));
			hashOpt.put(key, "score", Integer.toString(joiner.getScore()));
			hashOpt.put(key, "status", Integer.toString(joiner.getStatus()));
			logger.debug("更新缓存参赛者" + joiner);
		}
	}

	@Override
	public void updateRank(final int guildId, final int score) {
		if (Context.get().hasOpenRace()) {
			ZSetOperations<String, String> zsetOpt = guildRedisTemplate.opsForZSet();
			zsetOpt.incrementScore(CACHE_RANK_KEY, Integer.toString(guildId), Integer.valueOf(score).doubleValue());
			logger.debug("更新缓存排行榜【GuildID=" + guildId + "】");
		}
	}

	@Override
	public void addRaceGuild(final int guildId) {
		if (Context.get().hasOpenRace()) {
			ZSetOperations<String, String> zsetOpt = guildRedisTemplate.opsForZSet();
			zsetOpt.add(CACHE_RANK_KEY, Integer.toString(guildId), 0);
		}
	}

}
