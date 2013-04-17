/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ADDataBase.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;

/**
 * 类说明
 * 
 * @简述： 百度关键字日汇总
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-13 下午01:49:49
 */
public class ADDataBase implements Serializable {

	private static final long serialVersionUID = 6344921589245768070L;

	private String date; // 查询时间
	private String mediaId; // 媒体id
	private String adId; // 广告id
	private int click; // 点击
	private int ipClick; // 独立IP点击
	private int reg; // 注册
	private int ipReg; // 独立IP注册

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId
	 *            the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the adId
	 */
	public String getAdId() {
		return adId;
	}

	/**
	 * @param adId
	 *            the adId to set
	 */
	public void setAdId(String adId) {
		this.adId = adId;
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
	 * @return the ipClick
	 */
	public int getIpClick() {
		return ipClick;
	}

	/**
	 * @param ipClick
	 *            the ipClick to set
	 */
	public void setIpClick(int ipClick) {
		this.ipClick = ipClick;
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
	 * @return the ipReg
	 */
	public int getIpReg() {
		return ipReg;
	}

	/**
	 * @param ipReg
	 *            the ipReg to set
	 */
	public void setIpReg(int ipReg) {
		this.ipReg = ipReg;
	}

}
