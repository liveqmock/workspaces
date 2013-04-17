/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DayTxtDate.java
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
 * @简述： 百度日报明细跟踪表
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-27 下午04:00:47
 */
public class DayTxtDate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5553587405517549051L;

	private int id;
	private Date date;
	private String type;
	private String keyName;
	private Double used;
	private int show;
	private int click;
	private double clickPrice;
	private double rank;
	private int reg;
	private int regLogin;
	private double clickRatio;
	private double cpm;
	private double cpa;
	private double loginPrice;
	private int newReg;
	private double newRegRatio;
	private int subNewReg;
	private double subNewRegRatio;
	private int oldReg;
	private double oldRegRatio;
	private int newLogin;
	private int subNewLogin;
	private int oldLogin;
	private double regRatio;
	private String adIdSub;

	/**
	 * @return the regRatio
	 */
	public double getRegRatio() {
		return regRatio;
	}

	/**
	 * @param regRatio
	 *            the regRatio to set
	 */
	public void setRegRatio(double regRatio) {
		this.regRatio = regRatio;
	}

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
	 * @return the used
	 */
	public Double getUsed() {
		return used;
	}

	/**
	 * @param used
	 *            the used to set
	 */
	public void setUsed(Double used) {
		this.used = used;
	}

	/**
	 * @return the show
	 */
	public int getShow() {
		return show;
	}

	/**
	 * @param show
	 *            the show to set
	 */
	public void setShow(int show) {
		this.show = show;
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
	 * @return the clickPrice
	 */
	public double getClickPrice() {
		return clickPrice;
	}

	/**
	 * @param clickPrice
	 *            the clickPrice to set
	 */
	public void setClickPrice(double clickPrice) {
		this.clickPrice = clickPrice;
	}

	/**
	 * @return the rank
	 */
	public double getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(double rank) {
		this.rank = rank;
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
	 * @return the clickRatio
	 */
	public double getClickRatio() {
		return clickRatio;
	}

	/**
	 * @param clickRatio
	 *            the clickRatio to set
	 */
	public void setClickRatio(double clickRatio) {
		this.clickRatio = clickRatio;
	}

	/**
	 * @return the cpm
	 */
	public double getCpm() {
		return cpm;
	}

	/**
	 * @param cpm
	 *            the cpm to set
	 */
	public void setCpm(double cpm) {
		this.cpm = cpm;
	}

	/**
	 * @return the cpa
	 */
	public double getCpa() {
		return cpa;
	}

	/**
	 * @param cpa
	 *            the cpa to set
	 */
	public void setCpa(double cpa) {
		this.cpa = cpa;
	}

	/**
	 * @return the loginPrice
	 */
	public double getLoginPrice() {
		return loginPrice;
	}

	/**
	 * @param loginPrice
	 *            the loginPrice to set
	 */
	public void setLoginPrice(double loginPrice) {
		this.loginPrice = loginPrice;
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

	/**
	 * @return the adIdSub
	 */
	public String getAdIdSub() {
		return adIdSub;
	}

	/**
	 * @param adIdSub
	 *            the adIdSub to set
	 */
	public void setAdIdSub(String adIdSub) {
		this.adIdSub = adIdSub;
	}

}
