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
public class LoginModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4843319349792484703L;

	private String zone;

	private String name;
	
	private String mac;

	private String ip;

	private String addtime;

	/**
	 * @return the zone
	 */
	public String getZone() {
		return zone;
	}

	/**
	 * @param zone
	 *            the zone to set
	 */
	public void setZone(String zone) {
		this.zone = zone;
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
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * @param mac
	 *            the mac to set
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the addtime
	 */
	public String getAddtime() {
		return addtime;
	}

	/**
	 * @param addtime
	 *            the addtime to set
	 */
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
}
