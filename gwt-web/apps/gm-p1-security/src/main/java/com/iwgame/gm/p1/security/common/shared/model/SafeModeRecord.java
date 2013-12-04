/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SafeModeRecord.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 类说明
 * @简述： 安全模式记录表
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-19 上午09:17:38
 */
public class SafeModeRecord implements Serializable{

	private static final long serialVersionUID = -5445210958750034486L;

	private int id; 			//自增 	 
	private String rolename;	//角色名称  
	private String username;   //账户名
	private String dbid; 			//角色ID
	private Date optime;		//操作时间 
	private String operator;	//操作人 
	private int modeType;		//模式类型 ,1:增加安全模式,2:解除安全模式
	private String causeNote;	//原因备注
	private String groupname;	//游戏组名称  wt9game2
	private String guid;		//游戏大区    dx1  wt2
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public Date getOptime() {
		return optime;
	}
	public void setOptime(Date optime) {
		this.optime = optime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public int getModeType() {
		return modeType;
	}
	public void setModeType(int modeType) {
		this.modeType = modeType;
	}
	public String getCauseNote() {
		return causeNote;
	}
	public void setCauseNote(String causeNote) {
		this.causeNote = causeNote;
	}
}
