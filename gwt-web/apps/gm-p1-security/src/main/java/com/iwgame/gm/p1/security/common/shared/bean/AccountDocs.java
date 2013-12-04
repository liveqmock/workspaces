/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： AccountInfo.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.bean;

import java.io.Serializable;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-27 下午12:12:51
 */
public class AccountDocs implements Serializable{
	private static final long serialVersionUID = 8579338070123863612L;
	/**
	 * 帐号ID
	 */
	private String accountId;
	/**
	 * 帐号状态
	 */
	private String accountStatus;
	/**
	 * 身份证号码
	 */
	private String idcard;
	/**
	 * 身份证状态，是否高危
	 */
	private String idcardStatus;
	/**
	 * 最高等级
	 */
	private Integer maxLevel;
	/**
	 * 4位密码(加密保存)
	 */
	private String password4;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 注册IP
	 */
	private String registerIp;
	/**
	 * 注册来源类型，如AD
	 */
	private String registerSourcetype;
	/**
	 * 注册来源id，如：对应的广告id
	 */
	private String registerSourceid;
	/**
	 * 注册来源url，如：http://xx
	 */
	private String registerSourceurl;
	/**
	 * 注册时间
	 */
	private String registerTime;
	/**
	 * 总充值额
	 */
	private Integer totalPaid;
	/**
	 * 注册邮箱
	 */
	private String userEmail;
	/**
	 * 新修改后的邮箱
	 */
	private String userEmailNew;
	/**
	 * 帐号名
	 */
	private String userName;
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getIdcardStatus() {
		return idcardStatus;
	}
	public void setIdcardStatus(String idcardStatus) {
		this.idcardStatus = idcardStatus;
	}
	public Integer getMaxLevel() {
		return maxLevel;
	}
	public void setMaxLevel(Integer maxLevel) {
		this.maxLevel = maxLevel;
	}
	public String getPassword4() {
		return password4;
	}
	public void setPassword4(String password4) {
		this.password4 = password4;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	public String getRegisterSourcetype() {
		return registerSourcetype;
	}
	public void setRegisterSourcetype(String registerSourcetype) {
		this.registerSourcetype = registerSourcetype;
	}
	public String getRegisterSourceid() {
		return registerSourceid;
	}
	public void setRegisterSourceid(String registerSourceid) {
		this.registerSourceid = registerSourceid;
	}
	public String getRegisterSourceurl() {
		return registerSourceurl;
	}
	public void setRegisterSourceurl(String registerSourceurl) {
		this.registerSourceurl = registerSourceurl;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public Integer getTotalPaid() {
		return totalPaid;
	}
	public void setTotalPaid(Integer totalPaid) {
		this.totalPaid = totalPaid;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserEmailNew() {
		return userEmailNew;
	}
	public void setUserEmailNew(String userEmailNew) {
		this.userEmailNew = userEmailNew;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
