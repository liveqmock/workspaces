/**      
 * LoginMessageData.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.model;

/**
 * @ClassName: LoginMessageData
 * @Description: <pre>
 *  {
 *  	"zone":"大区ID",#如dx1
 * 	"mac":"MAC地址",
 *  	"ip":"IP地址",
 *   	"name":"账号名称",
 *  	"addtime":"登录时间"
 *  }
 * </pre>
 * @author 吴江晖
 * @date 2012-7-11 下午02:55:54
 * @Version 1.0
 * 
 */
public class LoginMessageData {

	private Policy policy;

	private String zone;
	private String mac;
	private String ip;
	private String name;
	private String addtime;

	private double totalPaid;
	private int maxLevel;

	public String getZone() {
		return zone;
	}

	public void setZone(final String zone) {
		this.zone = zone;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(final String mac) {
		this.mac = mac;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(final String addtime) {
		this.addtime = addtime;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(final Policy policy) {
		this.policy = policy;
	}

	public double getTotalPaid() {
		return totalPaid;
	}

	public void setTotalPaid(final double totalPaid) {
		this.totalPaid = totalPaid;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(final int maxLevel) {
		this.maxLevel = maxLevel;
	}

}
