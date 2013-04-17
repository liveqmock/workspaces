/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： DbidBean.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.server.bean;

import java.io.Serializable;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-22 下午04:36:26
 */
public class DbidBean implements Serializable{
	private static final long serialVersionUID = 2681731396685544453L;
	private long dbid;
	private String causeNote;
	private int optype;
	public long getDbid() {
		return dbid;
	}
	public void setDbid(long dbid) {
		this.dbid = dbid;
	}
	public String getCauseNote() {
		return causeNote;
	}
	public void setCauseNote(String causeNote) {
		this.causeNote = causeNote;
	}
	public int getOptype() {
		return optype;
	}
	public void setOptype(int optype) {
		this.optype = optype;
	}
	
}
