/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： BlackListmanage.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 类说明
 * 
 * @简述： 黑名单功能管理
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-9-26 下午01:47:54
 */
public class BlackListManage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2273480917756160276L;

	private String manageId; // id
	private String manageType; // 1为邮箱，2为身份证
	private String manageText; // 信息 sha1加密
	private Date manageInsertTime; // 插入时间
	private boolean isFull = false; // 信息是否完整

	public String getManageId() {
		return manageId;
	}

	public void setManageId(String manageId) {
		this.manageId = manageId;
	}

	public String getManageType() {
		return manageType;
	}

	public void setManageType(String manageType) {
		this.manageType = manageType;
	}

	public String getManageText() {
		return manageText;
	}

	public void setManageText(String manageText) {
		this.manageText = manageText;
	}

	public Date getManageInsertTime() {
		return manageInsertTime;
	}

	public void setManageInsertTime(Date manageInsertTime) {
		this.manageInsertTime = manageInsertTime;
	}

	public boolean isFull() {
		return isFull;
	}

	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}

}
