/****************************************************************
 *  文件名     ： FlumeEtcConfig.java
 *  日期         :  2012-8-21
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.iwcloud.flume.admin.shared.model;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

/** 
 * @ClassName:    FlumeEtcConfig 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-8-21下午04:21:47
 * @Version:      1.0 
 */
public class FlumeEtcConfig implements IsSerializable,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8036679484136374036L;

	private String t_channel_id;
	
	private String t_channel_name;
	
	private String t_exec;
	
	private String t_status;
	
	private String t_zone;
	
	private String t_group;
	
	/**
	 * @return the t_channel_id
	 */
	public String getT_channel_id() {
		return t_channel_id;
	}

	/**
	 * @param t_channel_id the t_channel_id to set
	 */
	public void setT_channel_id(String t_channel_id) {
		this.t_channel_id = t_channel_id;
	}

	/**
	 * @return the t_channel_name
	 */
	public String getT_channel_name() {
		return t_channel_name;
	}

	/**
	 * @param t_channel_name the t_channel_name to set
	 */
	public void setT_channel_name(String t_channel_name) {
		this.t_channel_name = t_channel_name;
	}

	/**
	 * @return the t_exec
	 */
	public String getT_exec() {
		return t_exec;
	}

	/**
	 * @param t_exec the t_exec to set
	 */
	public void setT_exec(String t_exec) {
		this.t_exec = t_exec;
	}

	/**
	 * @return the t_status
	 */
	public String getT_status() {
		return t_status;
	}

	/**
	 * @param t_status the t_status to set
	 */
	public void setT_status(String t_status) {
		this.t_status = t_status;
	}

	/**
	 * @return the t_zone
	 */
	public String getT_zone() {
		return t_zone;
	}

	/**
	 * @param t_zone the t_zone to set
	 */
	public void setT_zone(String t_zone) {
		this.t_zone = t_zone;
	}

	/**
	 * @return the t_group
	 */
	public String getT_group() {
		return t_group;
	}

	/**
	 * @param t_group the t_group to set
	 */
	public void setT_group(String t_group) {
		this.t_group = t_group;
	}

	
	
}
