/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： AdvidHour.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

/**
 * 类说明
 * 
 * @简述： 按照广告百度小时跟踪
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-28 下午03:56:37
 */
public class AdvidHour extends HourDataBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4766765396274741125L;

	// 广告ID
	private String adId;

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

}
