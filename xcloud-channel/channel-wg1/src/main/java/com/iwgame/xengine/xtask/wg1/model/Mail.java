/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： Mail.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.model;

import net.sf.json.JSONObject;

/**
 * 类说明
 * 
 * @简述： 邮件对象
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-11 下午06:32:33
 */
public class Mail {
	private String token; // 访问令牌
	private int id; // 序号
	private String guid; // 大区ID
	private int scope; // 范围 0：所有用户 1：所有在线用户 2：自定义用户
	private String username; // 用户名
	private String mailtitle; // 邮件标题
	private String mailcontent; // 邮件内容
	private String operater; // 操作人
	private int status; // 发送状态
	private String updatetime; // 更新时间

	public Mail() {
	};

	public Mail(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		this.username = jsonObject.getString("users");
		this.scope = jsonObject.getInt("scope");
		this.guid = jsonObject.getString("guid");
		this.mailtitle = jsonObject.getString("title");
		this.mailcontent = jsonObject.getString("content");
		this.operater = jsonObject.getString("operater");
		this.updatetime = jsonObject.getString("uptime");
	}

	public Mail(String guid, String username, String mailtitle, String mailcontent, String operater) {
		super();
		this.guid = guid;
		this.username = username;
		this.mailtitle = mailtitle;
		this.mailcontent = mailcontent;
		this.operater = operater;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMailtitle() {
		return mailtitle;
	}

	public void setMailtitle(String mailtitle) {
		this.mailtitle = mailtitle;
	}

	public String getMailcontent() {
		return mailcontent;
	}

	public void setMailcontent(String mailcontent) {
		this.mailcontent = mailcontent;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	
	public String toJsonString() {
		JSONObject jsonObject = JSONObject.fromObject(this);
		return jsonObject.toString();
	}
}
