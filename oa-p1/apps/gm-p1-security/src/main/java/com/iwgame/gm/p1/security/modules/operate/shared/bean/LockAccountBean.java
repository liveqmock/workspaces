/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： LockAccountBean.java
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
 * @修改时间：2012-11-19 下午03:47:11
 */
public class LockAccountBean implements Serializable{
	private static final long serialVersionUID = -7966745865289901254L;
	private String pid;		//产品编号  p-p1 p-p1.5
	private String guid;		//大区标示  默认:all 全区   以后可能出现 dx1 和 dx2
	private int sealtime;	//封停时间,单位分钟  解封=0
	private String username;	//帐号名
	private int showtime;	//游戏客户端是否显示封停时间,0:不显示,1:显示,                     (解封时=0);
	private int online;		//封杀时是否执行踢人下线操作,0:不执行踢人,1:执行踢人操作            (解封时=0)
	private int mailnotice;	//是否邮件通知,0:不通知,1:通知                             (解封时=0)
	private int sqlogin;		//是否限制社区登入限制社区登录,0:不限制,1:限制
	private int optype;		//操作类型,1:封杀(kill),2:冻结(look),3:解封(activity)
	private int causetype;	//原因类型,1:外挂,2:盗号,3:帐号安全,4其他                 (解封时=4)
	private String causenote;	//封禁,冻结,解冻,原因，说明
	private String batchid;		//批次号
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public int getSealtime() {
		return sealtime;
	}
	public void setSealtime(int sealtime) {
		this.sealtime = sealtime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getShowtime() {
		return showtime;
	}
	public void setShowtime(int showtime) {
		this.showtime = showtime;
	}
	public int getOnline() {
		return online;
	}
	public void setOnline(int online) {
		this.online = online;
	}
	public int getMailnotice() {
		return mailnotice;
	}
	public void setMailnotice(int mailnotice) {
		this.mailnotice = mailnotice;
	}
	public int getSqlogin() {
		return sqlogin;
	}
	public void setSqlogin(int sqlogin) {
		this.sqlogin = sqlogin;
	}
	public int getOptype() {
		return optype;
	}
	public void setOptype(int optype) {
		this.optype = optype;
	}
	public int getCausetype() {
		return causetype;
	}
	public void setCausetype(int causetype) {
		this.causetype = causetype;
	}
	public String getCausenote() {
		return causenote;
	}
	public void setCausenote(String causenote) {
		this.causenote = causenote;
	}
	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
}
