/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SafeModeBean.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.shared.bean;

import java.io.Serializable;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午11:02:17
 */
public class SafeModeBean implements Serializable{
	private static final long serialVersionUID = -2702676027311924001L;
	private String pid;			//产品编号  p-p1 p-p1.5
	private String dbid;			//角色ID
	private int flag;			// 是否允许自助解封  0:不可以 1:可以
	private String causenote;	//原因备注
	private String batchid;		//批次号
	private String accountid;	//帐号id;
	private String groupname;	//游戏组名称  wt9game2
	private String rolename;	//角色名称
	private String guid;		//游戏大区    dx1  wt2
	private String username;
	
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
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
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
	public String getCausenote() {
		return causenote;
	}
	public void setCausenote(String causenote) {
		this.causenote = causenote;
	}
	
}
