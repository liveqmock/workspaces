/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： LoginPassModifyRecord.java
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
 * @简述：账号登陆密码修改记录表
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-19 上午09:18:36
 */
public class LoginPassModifyRecord implements Serializable{
	
	private static final long serialVersionUID = 8526338505319697245L;

	private int id; 	//自增 
	private String userId; //账户ID
	private String username; 	//帐号名称 
	private String modifyPlace; 	//改密渠道,客户后台,手机....
	private String modifyip; 	//改密ip 
	private Date modifyTime; 	//改密时间 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getModifyPlace() {
		return modifyPlace;
	}
	public void setModifyPlace(String modifyPlace) {
		this.modifyPlace = modifyPlace;
	}
	public String getModifyip() {
		return modifyip;
	}
	public void setModifyip(String modifyip) {
		this.modifyip = modifyip;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
