/****************************************************************
 *  文件名     ： RegisterModel.java
 *  日期         :  2013-2-26
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.mq.sender.simulator.runtime;

import java.io.Serializable;

/** 
 * 类说明
 *
 * @简述： 
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间：2013-2-26下午05:52:00
 */
public class RegisterModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4843319349792484703L;

	private String rtime;

	private String rip;

	private String pid;

	private String rusername;

	private String rsource;

	private String rstatus;

	private String rtype;

	private String rmsg;

	/**
	 * @return the rstatus
	 */
	public String getRstatus() {
		return rstatus;
	}

	/**
	 * @param rstatus
	 *            the rstatus to set
	 */
	public void setRstatus(String rstatus) {
		this.rstatus = rstatus;
	}

	/**
	 * @return the rtime
	 */
	public String getRtime() {
		return rtime;
	}

	/**
	 * @param rtime
	 *            the rtime to set
	 */
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}

	/**
	 * @return the rip
	 */
	public String getRip() {
		return rip;
	}

	/**
	 * @param rip
	 *            the rip to set
	 */
	public void setRip(String rip) {
		this.rip = rip;
	}

	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * @return the rusername
	 */
	public String getRusername() {
		return rusername;
	}

	/**
	 * @param rusername
	 *            the rusername to set
	 */
	public void setRusername(String rusername) {
		this.rusername = rusername;
	}

	/**
	 * @return the rsource
	 */
	public String getRsource() {
		return rsource;
	}

	/**
	 * @param rsource
	 *            the rsource to set
	 */
	public void setRsource(String rsource) {
		this.rsource = rsource;
	}

	/**
	 * @return the rtype
	 */
	public String getRtype() {
		return rtype;
	}

	/**
	 * @param rtype
	 *            the rtype to set
	 */
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	/**
	 * @return the rmsg
	 */
	public String getRmsg() {
		return rmsg;
	}

	/**
	 * @param rmsg
	 *            the rmsg to set
	 */
	public void setRmsg(String rmsg) {
		this.rmsg = rmsg;
	}
}
