/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： DropDownListValue.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.shared.model;

import java.io.Serializable;

/**
 * 类说明
 * 
 * @简述： 下拉框数据源
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-9 下午06:16:43
 */
public class DropDownListData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -833076103923637002L;

	private int id;
	private String reason;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * @param reason the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
