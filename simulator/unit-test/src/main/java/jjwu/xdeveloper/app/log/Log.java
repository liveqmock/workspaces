/****************************************************************
 * 文件名 : Log.java
 * 日期 : 2013-5-15
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.app.log;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-5-15 下午4:12:38
 * @版本: v1.0.0
 */
public class Log implements Serializable {

	private static final long serialVersionUID = 2032433111989885140L;

	private String username;
	private Date opTime;
	private String opIp;
	private String option;
	private String auName;
	private String appName;
	private String opStatus;
	private String description;

	public Log() {
	}

	public Log(final String username, final Date opTime, final String opIp, final String option, final String auName,
			final String appName, final String opStatus, final String description) {
		this.username = username;
		this.opTime = opTime;
		this.opIp = opIp;
		this.option = option;
		this.auName = auName;
		this.appName = appName;
		this.opStatus = opStatus;
		this.description = description;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public Date getOpTime() {
		return this.opTime;
	}

	public void setOpTime(final Date opTime) {
		this.opTime = opTime;
	}

	public String getOpIp() {
		return this.opIp;
	}

	public void setOpIp(final String opIp) {
		this.opIp = opIp;
	}

	public String getOption() {
		return this.option;
	}

	public void setOption(final String option) {
		this.option = option;
	}

	public String getAuName() {
		return this.auName;
	}

	public void setAuName(final String auName) {
		this.auName = auName;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setAppName(final String appName) {
		this.appName = appName;
	}

	public String getOpStatus() {
		return this.opStatus;
	}

	public void setOpStatus(final String opStatus) {
		this.opStatus = opStatus;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}



}
