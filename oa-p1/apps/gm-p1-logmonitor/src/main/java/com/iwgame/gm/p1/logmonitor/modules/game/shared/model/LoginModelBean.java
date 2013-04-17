/**      
 * LoginModelBean.java Create on 2012-8-31     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.game.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: LoginModelBean
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-31 下午02:43:34
 * @Version 1.0
 * 
 */
public class LoginModelBean implements Serializable {

	private static final long serialVersionUID = -971532916803556363L;

	private int id;

	private String zoneId; // 大区ID

	private String username; // 用户名

	private int errorTimes; // 错误次数

	private Date loginTime; // 登录时间

	private String loginIp; // 登录IP

	private String loginMac; // 登录MAC

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getZoneId() {
		return zoneId;
	}

	public void setZoneId(final String zoneId) {
		this.zoneId = zoneId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public int getErrorTimes() {
		return errorTimes;
	}

	public void setErrorTimes(final int errorTimes) {
		this.errorTimes = errorTimes;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(final Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(final String loginIp) {
		this.loginIp = loginIp;
	}

	public String getLoginMac() {
		return loginMac;
	}

	public void setLoginMac(final String loginMac) {
		this.loginMac = loginMac;
	}

}
