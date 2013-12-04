/**      
 * ConsumeMessageData.java Create on 2012-4-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.model;

import java.util.Date;

/**
 * @ClassName: ConsumeMessageData
 * @Description: 消费数据
 * @author 吴江晖
 * @date 2012-4-28 上午11:41:26
 * @Version 1.0
 * 
 */
public class ConsumeMessageData {

	private CacheUser cacheUser;

	private int orderId;// idx;
	private long accountId;// accountid;
	private String name;// name
	private int linkId;
	private int mediaId;
	private int type = 0;
	private int gold;// gold
	private Date ctime;// utime;
	private int optype = 1;
	private String opparam1;
	private String opparam2;
	private String serveridx;
	private String actorid;
	private String actor;// actorname;
	private int level;// actorlev;
	private String ip;// ip
	private String area;// zone;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(final int orderId) {
		this.orderId = orderId;
	}

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

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(final int linkId) {
		this.linkId = linkId;
	}

	public int getMediaId() {
		return mediaId;
	}

	public void setMediaId(final int mediaId) {
		this.mediaId = mediaId;
	}

	public int getType() {
		return type;
	}

	public void setType(final int type) {
		this.type = type;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(final int gold) {
		this.gold = gold;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(final Date ctime) {
		this.ctime = ctime;
	}

	public int getOptype() {
		return optype;
	}

	public void setOptype(final int optype) {
		this.optype = optype;
	}

	public String getOpparam1() {
		return opparam1;
	}

	public void setOpparam1(final String opparam1) {
		this.opparam1 = opparam1;
	}

	public String getOpparam2() {
		return opparam2;
	}

	public void setOpparam2(final String opparam2) {
		this.opparam2 = opparam2;
	}

	public String getServeridx() {
		return serveridx;
	}

	public void setServeridx(final String serveridx) {
		this.serveridx = serveridx;
	}

	public String getActorid() {
		return actorid;
	}

	public void setActorid(final String actorid) {
		this.actorid = actorid;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(final String actor) {
		this.actor = actor;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public String getArea() {
		return area;
	}

	public void setArea(final String area) {
		this.area = area;
	}

	public CacheUser getCacheUser() {
		return cacheUser;
	}

	public void setCacheUser(final CacheUser cacheUser) {
		this.cacheUser = cacheUser;
	}

}
