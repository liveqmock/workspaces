package com.iwgame.cps.security;

import java.io.Serializable;
import java.util.Date;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-6-29 下午2:44:10
 * @版本:   	v1.0.0
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5721822991155494358L;

	/**
	 * ID
	 * */
	private String id;

	/**
	 * 显示名称
	 * */
	private String name;

	/**
	 * 登录名称
	 * */
	private String loginname;

	/**
	 * 部门ID
	 * */
	private String deptId;

	/**
	 * 部门名称
	 * */
	private String deptName;

	/**
	 * SessionId md5
	 * */
	private String sessionId;

	private String serverIP;

	/**
	 * 主机名
	 * */
	private String hostname;

	/**
	 * 主机IP
	 * */
	private String hostip;

	/**
	 * 主机MAC地址
	 * */
	private String hostmac;

	/**
	 * CA Key
	 * */
	private String cakey;

	/**
	 * CA Key过期
	 * */
	private String expiredtime;

	/**
	 * 登录时间
	 * */
	private Date intime = new Date();

	private String onlinetime;

	private String cacheKey;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginname() {
		return loginname;
	}

	public String getServerIP() {
		return serverIP;
	}

	public void setServerIP(String serverIP) {
		this.serverIP = serverIP;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getExpiredtime() {
		return expiredtime;
	}

	public void setExpiredtime(String expiredtime) {
		this.expiredtime = expiredtime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(String onlinetime) {
		this.onlinetime = onlinetime;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getHostip() {
		return hostip;
	}

	public void setHostip(String hostip) {
		this.hostip = hostip;
	}

	public String getHostmac() {
		return hostmac;
	}

	public void setHostmac(String hostmac) {
		this.hostmac = hostmac;
	}

	public String getCakey() {
		return cakey;
	}

	public void setCakey(String cakey) {
		this.cakey = cakey;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public String getCacheKey() {
		if (this.sessionId != null && this.id != null) {
			this.cacheKey = this.id + ":" + this.sessionId;
		} else if (this.sessionId != null && this.id == null) {
			this.cacheKey = this.sessionId;
		} else if (this.sessionId == null && this.id != null) {
			this.cacheKey = this.id;
		}
		return cacheKey;
	}

}
