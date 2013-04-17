/****************************************************************
 *  文件名     ： SecurityAccount.java
 *  日期         :  2012-11-16
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.bean;

import java.io.Serializable;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-11-16上午11:44:37
 * @版本: v1.0
 */
public class SecurityAccount implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6597615147181961417L;

	private final Logger logger = Logger.getLogger(SecurityAccount.class);

	private String pid;

	private String guid;

	private int sealtime;

	private String username;

	private int showtime;

	private int online;

	private int mailnotice;

	private int sqlogin;

	private int optype;

	private int causetype;

	private String causenote;

	private String batchid;

	private String appname;

	/**
	 * TODO: 无参构造函数
	 */
	public SecurityAccount() {
		super();
	}

	/**
	 * TODO: 无参构造函数
	 * 
	 * @throws Exception
	 */
	public SecurityAccount(String source) throws Exception {
		try {
			JSONObject jsonObject = JSONObject.fromObject(source);
			this.pid = jsonObject.getString("pid");
			this.guid = jsonObject.getString("guid");
			this.sealtime = jsonObject.getInt("sealtime");
			this.username = jsonObject.getString("username");
			this.showtime = jsonObject.getInt("showtime");
			this.online = jsonObject.getInt("online");
			this.mailnotice = jsonObject.getInt("mailnotice");
			this.sqlogin = jsonObject.getInt("sqlogin");
			this.optype = jsonObject.getInt("optype");
			this.causetype = jsonObject.getInt("causetype");
			this.causenote = jsonObject.getString("causenote");
			this.batchid = jsonObject.getString("batchid");
		} catch (JSONException e) {
			throw e;
		} catch (Exception e) {
			logger.error("忽略,参数异常!请求:" + source, e);
		}
	}

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

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
