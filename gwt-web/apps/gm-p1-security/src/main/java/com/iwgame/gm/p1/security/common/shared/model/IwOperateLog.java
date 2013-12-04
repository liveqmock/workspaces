/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： IwOperateLog.java
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
 * @简述： 操作日志表
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-19 上午09:16:37
 */
public class IwOperateLog  implements Serializable{
	private static final long serialVersionUID = -6983979194325723177L;
	private int id; 	//自增  	  	 
	private String relId;		//关联编号
	private String note;		//内容
	private Date insertTime; 	//插入时间
	private String insertUser; 	//插入人  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRelId() {
		return relId;
	}
	public void setRelId(String relId) {
		this.relId = relId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	public String getInsertUser() {
		return insertUser;
	}
	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}
}
