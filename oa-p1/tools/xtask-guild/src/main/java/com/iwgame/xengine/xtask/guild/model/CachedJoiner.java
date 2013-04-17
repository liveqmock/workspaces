/**      
 * CachedJoiner.java Create on 2012-7-1     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @ClassName: CachedJoiner
 * @Description: 缓存到Redis中的公会赛参赛者，key=rid:[当前公会赛ID]:joiner:[参赛者帐号名]
 * @author 吴江晖
 * @date 2012-7-1 上午10:00:08
 * @Version 1.0
 * 
 */
public class CachedJoiner implements Serializable {

	private static final long serialVersionUID = 1829260580259106873L;

	private int raceId;

	private int joinerId;
	private int guildId;
	private String name;
	private int level;
	private int status;
	private int score;

	/**
	 * 
	 */
	public CachedJoiner() {
	}

	public CachedJoiner(final int raceId, final String name, final Map<String, String> map) {
		this.raceId = raceId;
		this.name = name;
		if (map.containsKey("joinerId")) {
			joinerId = Integer.valueOf(map.get("joinerId"));
		}
		if (map.containsKey("guildId")) {
			guildId = Integer.valueOf(map.get("guildId"));
		}
		if (map.containsKey("level")) {
			level = Integer.valueOf(map.get("level"));
		}
		if (map.containsKey("status")) {
			status = Integer.valueOf(map.get("status"));
		}
		if (map.containsKey("score")) {
			score = Integer.valueOf(map.get("score"));
		}
	}

	public int getJoinerId() {
		return joinerId;
	}

	public void setJoinerId(final int joinerId) {
		this.joinerId = joinerId;
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(final int guildId) {
		this.guildId = guildId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(final int raceId) {
		this.raceId = raceId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("当前公会赛【ID=").append(raceId).append("】的参数者信息【");
		sb.append("用户名=").append(name).append(";");
		sb.append("参赛者ID=").append(joinerId).append(";");
		sb.append("等级=").append(level).append(";");
		sb.append("积分=").append(score).append(";");
		sb.append("状态=").append(status).append("】");
		return sb.toString();
	}

}
