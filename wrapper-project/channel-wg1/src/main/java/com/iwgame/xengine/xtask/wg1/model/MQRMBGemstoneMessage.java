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
public class MQRMBGemstoneMessage {
	private String guid;		//#guid: 大区编号
	private String operater;	//#operater: 操作人员即发起这次发奖操作的人
	private String username;	//#username：账号
	private String uptime;		//#uptime: 时间，主要用于跟踪数据
	private String content;		//#content：宝石内容json格式{"content":[{“id”:1},{“id”:2}]}
	private String source;		//#source: 来源
	private String desc;		//备注
	
	@SuppressWarnings("deprecation")
	public MQRMBGemstoneMessage(String username, String guid, String content,String source,String desc) {
		super();
		this.username = username;
		this.guid = guid;
		this.content = content;
		this.operater = "system";
		this.source = source;
		this.uptime = new Date().toLocaleString();
		this.desc = desc;
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
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
