/****************************************************************
 *  系统名称  ： '广告系统-[xportal-task]'
 *  文件名    ： MediaIp.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.bean;

import java.io.Serializable;

/**
 * 类说明
 * @简述： 媒体网吧IP
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-11-8 下午02:52:56
 */
public class MediaIp implements Serializable{
	private static final long serialVersionUID = -81023461456792527L;
    
	private String id;     //ID
	private String ip;    //IP
	private String area;   //对于区域
	private String addTime;    //添加时间
	private String updateTime;  //最后修改时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIp() {
		if (ip != null)
			return ip.trim();
		else
			return "";
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
}
