/**      
 * User.java Create on 2012-5-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-11 下午04:02:21
 * @Version 1.0
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 8998847091804847089L;

	private long accountId;
	private String name;
	private int mediaId;
	private int linkId;
	private int consumeTimes;
	private double consume;
	private double bonus;
	private Date registeTime;
	private String ip;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(final long accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(final int mediaId) {
		this.mediaId = mediaId;
	}

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(final int linkId) {
		this.linkId = linkId;
	}

	public int getConsumeTimes() {
		return consumeTimes;
	}

	public void setConsumeTimes(final int consumeTimes) {
		this.consumeTimes = consumeTimes;
	}

	public double getConsume() {
		return consume;
	}

	public void setConsume(final double consume) {
		this.consume = consume;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(final double bonus) {
		this.bonus = bonus;
	}

	public Date getRegisteTime() {
		return registeTime;
	}

	public void setRegisteTime(final Date registeTime) {
		this.registeTime = registeTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

}
