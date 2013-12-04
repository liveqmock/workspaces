/****************************************************************
 * 文件名 ： DataModel.java
 * 日期 : 2013-1-17
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.channel.data.handler.model;

import net.sf.json.JSONObject;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * @类名: DataModel
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮件: wujunjie@iwgame.com
 * @日期: 2013-1-17下午5:01:23
 * @版本: 1.0
 */
public class DataModel implements IwAnnotation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private long dbid;

	@NotEmpty
	private String name;

	@NotEmpty
	private String serv;

	@NotEmpty
	private int lastupdatetime;

	/**
	 * @return the dbid
	 */
	public long getDbid() {
		return dbid;
	}

	/**
	 * @param dbid
	 *            the dbid to set
	 */
	public void setDbid(long dbid) {
		this.dbid = dbid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the serv
	 */
	public String getServ() {
		return serv;
	}

	/**
	 * @param serv
	 *            the serv to set
	 */
	public void setServ(String serv) {
		this.serv = serv;
	}

	/**
	 * @return the lastupdatetime
	 */
	public int getLastupdatetime() {
		return lastupdatetime;
	}

	/**
	 * @param lastupdatetime
	 *            the lastupdatetime to set
	 */
	public void setLastupdatetime(int lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
