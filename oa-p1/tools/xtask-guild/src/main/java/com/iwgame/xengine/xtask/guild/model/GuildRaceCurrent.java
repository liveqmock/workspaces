/**      
 * CurrentRaceInfo.java Create on 2012-7-20     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.model;

/**
 * @ClassName: CurrentRaceInfo
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-20 下午03:27:43
 * @Version 1.0
 * 
 */
public class GuildRaceCurrent {

	private int raceId;

	private int guildId;

	private int joiners;

	private int score;

	private int sort;

	private double paid;

	private double consume;

	public int getRaceId() {
		return raceId;
	}

	public void setRaceId(final int raceId) {
		this.raceId = raceId;
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(final int guildId) {
		this.guildId = guildId;
	}

	public int getJoiners() {
		return joiners;
	}

	public void setJoiners(final int joiners) {
		this.joiners = joiners;
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(final int sort) {
		this.sort = sort;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(final double paid) {
		this.paid = paid;
	}

	public double getConsume() {
		return consume;
	}

	public void setConsume(final double consume) {
		this.consume = consume;
	}

}
