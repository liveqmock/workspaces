/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： AutoKillLog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.logs.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.iwgame.account.kill.modules.policy.shared.model.Policy;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-11 下午06:05:54
 */
public class AutoKillLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8405153652343428099L;
	
	private int id;
	private int optType;
	private String policyTitle;
	private String policyContent;
	private int accountid;
	
	private String accountName;
	private Date killTime;
	private String remark;
	private String objectId;
	private String object;
	
	private int killDays;
	private String operator;
	
	private Policy policy;

	/**
	 * @return the policy
	 */
	public Policy getPolicy() {
		return policy;
	}

	/**
	 * @param policy the policy to set
	 */
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the optType
	 */
	public int getOptType() {
		return optType;
	}

	/**
	 * @param optType the optType to set
	 */
	public void setOptType(int optType) {
		this.optType = optType;
	}

	/**
	 * @return the policyTitle
	 */
	public String getPolicyTitle() {
		return policyTitle;
	}

	/**
	 * @param policyTitle the policyTitle to set
	 */
	public void setPolicyTitle(String policyTitle) {
		this.policyTitle = policyTitle;
	}

	/**
	 * @return the policyContent
	 */
	public String getPolicyContent() {
		return policyContent;
	}

	/**
	 * @param policyContent the policyContent to set
	 */
	public void setPolicyContent(String policyContent) {
		this.policyContent = policyContent;
	}

	/**
	 * @return the accountid
	 */
	public int getAccountid() {
		return accountid;
	}

	/**
	 * @param accountid the accountid to set
	 */
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the killTime
	 */
	public Date getKillTime() {
		return killTime;
	}

	/**
	 * @param killTime the killTime to set
	 */
	public void setKillTime(Date killTime) {
		this.killTime = killTime;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the objectId
	 */
	public String getObjectId() {
		return objectId;
	}

	/**
	 * @param objectId the objectId to set
	 */
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	/**
	 * @return the object
	 */
	public String getObject() {
		return object;
	}

	/**
	 * @param object the object to set
	 */
	public void setObject(String object) {
		this.object = object;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the killDays
	 */
	public int getKillDays() {
		return killDays;
	}

	/**
	 * @param killDays the killDays to set
	 */
	public void setKillDays(int killDays) {
		this.killDays = killDays;
	}
	
}
