/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： HourDataBase.java
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
 * @简述： 百度小时跟踪基础信息
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-28 下午03:46:03
 */
public class HourDataBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9215212194781105648L;

	// 自增ID
	private int id;
	// 日期
	private Date date;
	// 关键字
	private String keyName;
	// 百度点击
	private int click;
	// 总注册
	private int reg;
	// 新帐号
	private int newReg;
	// 次新帐号
	private int subNewReg;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * @param keyName
	 *            the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * @return the click
	 */
	public int getClick() {
		return click;
	}

	/**
	 * @param click
	 *            the click to set
	 */
	public void setClick(int click) {
		this.click = click;
	}

	/**
	 * @return the reg
	 */
	public int getReg() {
		return reg;
	}

	/**
	 * @param reg
	 *            the reg to set
	 */
	public void setReg(int reg) {
		this.reg = reg;
	}

	/**
	 * @return the newReg
	 */
	public int getNewReg() {
		return newReg;
	}

	/**
	 * @param newReg
	 *            the newReg to set
	 */
	public void setNewReg(int newReg) {
		this.newReg = newReg;
	}

	/**
	 * @return the subNewReg
	 */
	public int getSubNewReg() {
		return subNewReg;
	}

	/**
	 * @param subNewReg
	 *            the subNewReg to set
	 */
	public void setSubNewReg(int subNewReg) {
		this.subNewReg = subNewReg;
	}

}
