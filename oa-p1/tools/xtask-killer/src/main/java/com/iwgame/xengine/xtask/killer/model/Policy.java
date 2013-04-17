/**      
 * Policy.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.model;

import java.util.Date;

import net.sf.json.JSONObject;

import org.joda.time.DateTime;

/**
 * @ClassName: Policy
 * @Description: 封杀策略
 * @author 吴江晖
 * @date 2012-7-11 上午09:39:05
 * @Version 1.0
 * 
 */
public class Policy {

	private int id;// 策略ID

	private String title;// 策略文案名

	private String mac;// MAC地址

	private Date startDate;// 开始时间

	private Date endDate;// 结束时间

	private String content;

	private Rule rule;// 策略规则

	// private int counter;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(final Rule rule) {
		this.rule = rule;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("策略ID：").append(id).append(",");
		sb.append("策略文案名：").append(title).append(",");
		sb.append("MAC地址：").append(mac).append(",");
		DateTime s = new DateTime(startDate);
		sb.append("策略执行开始时间：").append(s.toString("yyyy-MM-dd HH:mm")).append(",");
		DateTime e = new DateTime(endDate);
		sb.append("策略执行结束时间：").append(e.toString("yyyy-MM-dd HH:mm")).append(",");
		sb.append("策略规则：").append(JSONObject.fromObject(rule));
		return sb.toString();
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	// public void counter() {
	// counter++;
	// }
	//
	// public int getCounter() {
	// return counter;
	// }
	//
	// public void cleanCounter() {
	// counter = 0;
	// }
}
