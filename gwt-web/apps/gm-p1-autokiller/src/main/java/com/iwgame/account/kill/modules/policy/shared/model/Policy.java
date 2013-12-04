/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： Policy.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.shared.model;

import java.io.Serializable;

/**
 * 类说明
 * 
 * @简述： 策略对象
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-10 上午11:37:20
 */
public class Policy implements Serializable {

	private static final long serialVersionUID = 4876872471180530718L;

	/*
	 * 
	{
	    "paid": NUMBER 是否有充值记录。-1=无限制；0=无；大于0时，值为充值限额
	    "level": NUMBER 是否有等级过滤。-1=限制；大于0的值为等级限制值
	    "level_opt": STRING "gt","lt"
	    "delay": NUMBER 是否延时。-1=帐号登录自动封停；其他大于0的值为延时值，在帐号登录后延时封停
	    "reason": NUMBER 封停理由ID
	    "days": NUMBER 封停天数 （1，3，7，30，5000）
	    "accounts": NUMBER 允许自动封停账户数，0表示不限制
	    "due_days": NUMBER 有效期（默认7天）
	}
	 * 
	 * */
	private int paid;
	private int level;
	private String levelOpt;
	private int delay;
	private int reason;
	private int days;
	private int accounts;
	private int dueDays;
	/**
	 * @return the paid
	 */
	public int getPaid() {
		return paid;
	}
	/**
	 * @param paid the paid to set
	 */
	public void setPaid(int paid) {
		this.paid = paid;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the levelOpt
	 */
	public String getLevelOpt() {
		return levelOpt;
	}
	/**
	 * @param levelOpt the levelOpt to set
	 */
	public void setLevelOpt(String levelOpt) {
		this.levelOpt = levelOpt;
	}
	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}
	/**
	 * @param delay the delay to set
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}
	/**
	 * @return the reason
	 */
	public int getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(int reason) {
		this.reason = reason;
	}
	/**
	 * @return the days
	 */
	public int getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}
	/**
	 * @return the accounts
	 */
	public int getAccounts() {
		return accounts;
	}
	/**
	 * @param accounts the accounts to set
	 */
	public void setAccounts(int accounts) {
		this.accounts = accounts;
	}
	/**
	 * @return the dueDays
	 */
	public int getDueDays() {
		return dueDays;
	}
	/**
	 * @param dueDays the dueDays to set
	 */
	public void setDueDays(int dueDays) {
		this.dueDays = dueDays;
	}

}
