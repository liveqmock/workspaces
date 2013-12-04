/****************************************************************
 *  文件名     ： SafeModelBean.java
 *  日期         :  2012-11-27
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.game.model;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/** 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-27下午03:05:49
 * @版本:   v1.0 
 */
public class SafeModelBean implements IwAnnotation{
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -4009269582973341849L;

	private final Logger logger = Logger.getLogger("game");
	
	@NotEmpty
	private String guid;
	
	private String dbid; 			// 角色ID
	
	private String rolename;
	
	private String batchid; 		// 批次号
	
	private String groupname;
	
	private String username;		//玩家帐号名称
	
	public SafeModelBean(String source){
		try {
			JSONObject json = JSONObject.fromObject(source);
			this.guid = json.getString("guid");
			this.dbid = json.get("dbid") == null ? "" : json.getString("dbid");
			this.rolename = json.getString("rolename");
			this.groupname = json.getString("groupname");
			this.batchid = json.getString("batchid");
			this.username = json.getString("username");
		} catch (Exception e) {
			logger.error("安全模式参数异常!忽略此条消息...",e);
		}
	}
	
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
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

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
