/**      
 * Rule.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.model;


/**
 * @ClassName: Rule
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-11 下午12:12:16
 * @Version 1.0
 * 
 */
public class Rule {

	private int paid;// 是否有充值记录。-1=无限制；0=无；大于0时，值为充值限额

	private int level;// 是否有等级过滤。-1=限制；大于0的值为等级限制值

	private String levelOpt;// "gt","lt"

	private int delay;// 是否延时。-1=帐号登录自动封停；其他大于0的值为延时值，在帐号登录后延时封停

	private int reasonId;// 封停理由ID

	private String reason;

	private int days;// 封停天数 （1，3，7，30，5000）

	private int accounts;// 允许自动封停账户数，0表示不限制

	private int dueDays = 7;// 有效期（默认7天）

	public int getPaid() {
		return paid;
	}

	public void setPaid(final int paid) {
		this.paid = paid;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public String getLevelOpt() {
		return levelOpt;
	}

	public void setLevelOpt(final String levelOpt) {
		this.levelOpt = levelOpt;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(final int reasonId) {
		this.reasonId = reasonId;
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

	public int getAccounts() {
		return accounts;
	}

	public void setAccounts(final int accounts) {
		this.accounts = accounts;
	}

	public int getDueDays() {
		return dueDays;
	}

	public void setDueDays(final int dueDays) {
		this.dueDays = dueDays;
	}

}
