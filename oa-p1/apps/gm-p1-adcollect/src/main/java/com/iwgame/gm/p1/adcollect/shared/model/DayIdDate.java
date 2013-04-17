/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DayIdDate.java
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
 * @简述：百度日报明细跟踪表
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-29 下午04:55:49
 */
public class DayIdDate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1297042958072485127L;

	private int id;
	private Date date;
	private String type;
	private String keyName;
	private int adId;
	private int reg;
	private int regLogin;
	private int newReg;
	private double newRegRatio;
	private int subNewReg;
	private double subNewRegRatio;
	private int oldReg;
	private double oldRegRatio;
	private int newLogin;
	private int subNewLogin;
	private int oldLogin;

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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the adId
	 */
	public int getAdId() {
		return adId;
	}

	/**
	 * @param adId
	 *            the adId to set
	 */
	public void setAdId(int adId) {
		this.adId = adId;
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
	 * @return the regLogin
	 */
	public int getRegLogin() {
		return regLogin;
	}

	/**
	 * @param regLogin
	 *            the regLogin to set
	 */
	public void setRegLogin(int regLogin) {
		this.regLogin = regLogin;
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
	 * @return the newRegRatio
	 */
	public double getNewRegRatio() {
		return newRegRatio;
	}

	/**
	 * @param newRegRatio
	 *            the newRegRatio to set
	 */
	public void setNewRegRatio(double newRegRatio) {
		this.newRegRatio = newRegRatio;
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

	/**
	 * @return the subNewRegRatio
	 */
	public double getSubNewRegRatio() {
		return subNewRegRatio;
	}

	/**
	 * @param subNewRegRatio
	 *            the subNewRegRatio to set
	 */
	public void setSubNewRegRatio(double subNewRegRatio) {
		this.subNewRegRatio = subNewRegRatio;
	}

	/**
	 * @return the oldReg
	 */
	public int getOldReg() {
		return oldReg;
	}

	/**
	 * @param oldReg
	 *            the oldReg to set
	 */
	public void setOldReg(int oldReg) {
		this.oldReg = oldReg;
	}

	/**
	 * @return the oldRegRatio
	 */
	public double getOldRegRatio() {
		return oldRegRatio;
	}

	/**
	 * @param oldRegRatio
	 *            the oldRegRatio to set
	 */
	public void setOldRegRatio(double oldRegRatio) {
		this.oldRegRatio = oldRegRatio;
	}

	/**
	 * @return the newLogin
	 */
	public int getNewLogin() {
		return newLogin;
	}

	/**
	 * @param newLogin
	 *            the newLogin to set
	 */
	public void setNewLogin(int newLogin) {
		this.newLogin = newLogin;
	}

	/**
	 * @return the subNewLogin
	 */
	public int getSubNewLogin() {
		return subNewLogin;
	}

	/**
	 * @param subNewLogin
	 *            the subNewLogin to set
	 */
	public void setSubNewLogin(int subNewLogin) {
		this.subNewLogin = subNewLogin;
	}

	/**
	 * @return the oldLogin
	 */
	public int getOldLogin() {
		return oldLogin;
	}

	/**
	 * @param oldLogin
	 *            the oldLogin to set
	 */
	public void setOldLogin(int oldLogin) {
		this.oldLogin = oldLogin;
	}

}
