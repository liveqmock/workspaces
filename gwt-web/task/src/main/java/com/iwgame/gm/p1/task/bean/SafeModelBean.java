/****************************************************************
 *  文件名     ： SafeModelBean.java
 *  日期         :  2012-11-27
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.bean;

import java.io.Serializable;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-27下午12:21:03
 * @版本: v1.0
 */
public class SafeModelBean implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 8393196928958946885L;

	private final Logger logger = Logger.getLogger(SafeModelBean.class);

	private String pid; // 产品编号 p-p1 p-p1.5
	private String dbid; // 角色ID
	private String rolename; // 角色名称
	private int flag; // 是否允许自助解封 0:不可以 1:可以
	private String batchid; // 批次号
	private String accountid; // 玩家帐号ID
	private String groupname; // 游戏组的名称
	private String guid; // 大区ID
	private String username; // 玩家帐号

	public SafeModelBean(String source) throws Exception {
		JSONObject json = JSONObject.fromObject(source);
		try {
			this.pid = json.getString("pid");
			this.dbid = json.getString("dbid");
			this.flag = json.getInt("flag");
			this.batchid = json.getString("batchid");
			this.accountid = json.getString("accountid");
			this.rolename = json.getString("rolename");
			this.groupname = json.getString("groupname");
			this.guid = json.getString("guid");
			this.username = json.getString("username");
		} catch (JSONException e) {
			throw e;
		} catch (Exception e) {
			logger.error("参数异常!,忽略此条消息,请求:" + source, e);
		}
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getDbid() {
		return dbid;
	}

	public void setDbid(String dbid) {
		this.dbid = dbid;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getBatchid() {
		return batchid;
	}

	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
