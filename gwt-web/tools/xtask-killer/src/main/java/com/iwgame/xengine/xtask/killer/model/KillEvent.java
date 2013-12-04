/**      
 * KillEvent.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.model;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * @ClassName: KillEvent
 * @Description: 封杀事件
 * @author 吴江晖
 * @date 2012-7-11 上午09:45:50
 * @Version 1.0
 * 
 */
public class KillEvent {

	private String zone;

	private String content;

	private String title;

	private String mac;

	private Long accountid;

	private String accountName;// 封杀的玩家帐号名称

	private Date killDate;// 封杀时间

	private String reason;

	private int days;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(final String accountName) {
		this.accountName = accountName;
	}

	public Date getKillDate() {
		return killDate;
	}

	public void setKillDate(final Date killDate) {
		this.killDate = killDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(final String mac) {
		this.mac = mac;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public int getDays() {
		return days;
	}

	public void setDays(final int days) {
		this.days = days;
	}

	public Long getAccountid() {
		return accountid;
	}

	public void setAccountid(final Long accountid) {
		this.accountid = accountid;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(final String zone) {
		this.zone = zone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("被封杀的帐号=").append(accountName).append(",");
		sb.append("封杀策略文案名是：").append(title).append(",");
		sb.append("封杀策略是：").append(content).append(",");
		sb.append("封杀理由是：").append(reason).append(",");
		sb.append("封杀天数是：").append(days).append(",");
		sb.append("封杀MAC是：").append(mac).append(",");
		sb.append("封杀MAC登录大区：").append(zone).append(",");
		DateTime s = new DateTime(killDate);
		sb.append("封杀时间=").append(s.toString("yyyy-MM-dd HH:mm:ss"));
		return sb.toString();
	}
}
