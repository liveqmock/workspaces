/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： KilledRecord.java
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
 * @简述：封杀记录表
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-19 上午09:15:56
 */
public class KilledRecord implements Serializable{
	
	private static final long serialVersionUID = 2454822537737292700L;

	private int id; 	//自增
	private String zone; 	//大区
	private String username; 	//帐号
	private int type;	//帐号的状态 	1.封杀,2.冻结,3.正常
	private int days; 	//封停天数 
	private String batchid; 	//批次ID
	private Date optime; 	//操作时间 
	private String operator; 	//操作人
	private String handleStatus; 	//处理状态 0:处理完成 1:处理中 2:失败
	private String causeNote;	//封杀原因  ,与封杀原因配置表的id进行关联
	private int killedType;		//封杀类型  	1:封杀,2:冻结,3:解封,4:解冻
	private int causeType;		//1:外挂 2:盗号 3:帐号安全 4:其他
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
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
	public String getHandleStatus() {
		return handleStatus;
	}
	public void setHandleStatus(String handleStatus) {
		this.handleStatus = handleStatus;
	}
	public String getCauseNote() {
		return causeNote;
	}
	public void setCauseNote(String causeNote) {
		this.causeNote = causeNote;
	}
	public int getKilledType() {
		return killedType;
	}
	public void setKilledType(int killedType) {
		this.killedType = killedType;
	}
	public int getCauseType() {
		return causeType;
	}
	public void setCauseType(int causeType) {
		this.causeType = causeType;
	}
	
}
