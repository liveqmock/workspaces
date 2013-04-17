/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： RoleDocs.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.bean;

import java.io.Serializable;

/**
 * 类说明
 * @简述： 角色明细信息
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-22 上午09:29:23
 */
public class RoleDocs implements Serializable{

	private static final long serialVersionUID = 9088811959258452400L;
	private String accountid;
	private String bankmoney;
	private String createtime;
	private String dbid;
	private String deleted;
	private String deletetime;
	private String ip;
	private String ipptime;
	private String ipsafe;
	private String lastlevuptime;
	private String lastupdatetime;
	private String level;
	private String locked;
	private String lockedtime;
	private String money;
	private String name;
	private String onlinetime;
	private String party;
	private String svr;
	private String totalexp;
	private String totalgodexp;
	private String guid;
	
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getBankmoney() {
		return bankmoney;
	}
	public void setBankmoney(String bankmoney) {
		this.bankmoney = bankmoney;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getDeletetime() {
		return deletetime;
	}
	public void setDeletetime(String deletetime) {
		this.deletetime = deletetime;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getIpptime() {
		return ipptime;
	}
	public void setIpptime(String ipptime) {
		this.ipptime = ipptime;
	}
	public String getIpsafe() {
		return ipsafe;
	}
	public void setIpsafe(String ipsafe) {
		this.ipsafe = ipsafe;
	}
	public String getLastlevuptime() {
		return lastlevuptime;
	}
	public void setLastlevuptime(String lastlevuptime) {
		this.lastlevuptime = lastlevuptime;
	}
	public String getLastupdatetime() {
		return lastupdatetime;
	}
	public void setLastupdatetime(String lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}
	public String getLocked() {
		return locked;
	}
	public void setLocked(String locked) {
		this.locked = locked;
	}
	public String getLockedtime() {
		return lockedtime;
	}
	public void setLockedtime(String lockedtime) {
		this.lockedtime = lockedtime;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOnlinetime() {
		return onlinetime;
	}
	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String party) {
		this.party = party;
	}
	public String getSvr() {
		return svr;
	}
	public void setSvr(String svr) {
		this.svr = svr;
	}
	public String getTotalexp() {
		return totalexp;
	}
	public void setTotalexp(String totalexp) {
		this.totalexp = totalexp;
	}
	public String getTotalgodexp() {
		return totalgodexp;
	}
	public void setTotalgodexp(String totalgodexp) {
		this.totalgodexp = totalgodexp;
	}
	
}
