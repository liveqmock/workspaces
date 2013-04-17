/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： MQRMBMessage.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.model;

import java.util.Date;

import net.sf.json.JSONObject;

/**
 * 类说明
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-4-25 下午07:30:26
 */
public class MQRMBResourcesMessage {
	private String scope = "2";
	private String batchid = String.valueOf(System.currentTimeMillis());
	private String username = "";
	private String guid = "";
	private String sourcetype = "0";
	private String resouces = "";
	private String operater = "";
	private String source = "";
	private String uptime = "";
	private String desc = "";
	
	/**
	 * @param scope 	发放范围 0：所有用户 1：所有在线用户 2：自定义用户列表
	 * @param batchid	 批次号
	 * @param username	玩家帐号,支持多个用户，用;隔开，如格式：username1;username2
	 * @param guid		大区编号
	 * @param sourcetype礼品类型 0：默认赠送礼品 1：vip礼品，礼品内容中金币计入vip计算。（非常重要，不要传错了）	
	 * @param resouces  资源信息,json格式(参看资源定义)
	 * @param operater	操作人员即发起这次发奖操作的人
	 * @param source	来源，比如OA，平台导入
	 * @param uptime    时间，主要用于跟踪数据
	 */
	@SuppressWarnings("deprecation")
	public MQRMBResourcesMessage(String username, String guid, String resouces,String source,String desc) {
		super();
		this.username = username;
		this.guid = guid;
		this.resouces = resouces;
		this.operater = "system";
		this.source = source;
		this.uptime = new Date().toLocaleString();
		this.desc = desc;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getSourcetype() {
		return sourcetype;
	}
	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}
	public String getResouces() {
		return resouces;
	}
	public void setResouces(String resouces) {
		this.resouces = resouces;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String toJsonString() {
		JSONObject jsonObject = JSONObject.fromObject(this);
		return jsonObject.toString();
	}
	
}
