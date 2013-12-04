/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： AdGroupDataBase.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.iwgame.gm.p1.common.server.log.BizLog;

/**
 * 类说明
 * 
 * @简述： 广告组
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-17 上午11:39:38
 */
@BizLog(value = "GROUP", createTemplate = "创建广告组", relId = "id")
public class AdGroupDataBase implements Serializable {

	private static final long serialVersionUID = -3780583172160675776L;

	private int id;
	private String name;
	private double budgetPrice;
	private double sumPrice;
	private String mediaid;
	private Date addTime;
	private Date updateTime;

	private String sheduleId;
	private String sheduleName;
	private String materialId;
	private String usedType;
	private double generalPrice;
	private double specialPrice;

	private String adUrl;
	private String mediaName;

	/**
	 * @return 获取 id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return 获取 budgetPrice
	 */
	public double getBudgetPrice() {
		return budgetPrice;
	}

	/**
	 * @param budgetPrice
	 *            设置 budgetPrice
	 */
	public void setBudgetPrice(final double budgetPrice) {
		this.budgetPrice = budgetPrice;
	}

	/**
	 * @return 获取 sumPrice
	 */
	public double getSumPrice() {
		return sumPrice;
	}

	/**
	 * @param sumPrice
	 *            设置 sumPrice
	 */
	public void setSumPrice(final double sumPrice) {
		this.sumPrice = sumPrice;
	}

	/**
	 * @return 获取 generalPrice
	 */
	public double getGeneralPrice() {
		return generalPrice;
	}

	/**
	 * @param generalPrice
	 *            设置 generalPrice
	 */
	public void setGeneralPrice(final double generalPrice) {
		this.generalPrice = generalPrice;
	}

	/**
	 * @return 获取 specialPrice
	 */
	public double getSpecialPrice() {
		return specialPrice;
	}

	/**
	 * @param specialPrice
	 *            设置 specialPrice
	 */
	public void setSpecialPrice(final double specialPrice) {
		this.specialPrice = specialPrice;
	}

	/**
	 * @return 获取 sheduleId
	 */
	public String getSheduleId() {
		return sheduleId;
	}

	/**
	 * @param sheduleId
	 *            设置 sheduleId
	 */
	public void setSheduleId(final String sheduleId) {
		this.sheduleId = sheduleId;
	}

	/**
	 * @return 获取 sheduleName
	 */
	public String getSheduleName() {
		return sheduleName;
	}

	/**
	 * @param sheduleName
	 *            设置 sheduleName
	 */
	public void setSheduleName(final String sheduleName) {
		this.sheduleName = sheduleName;
	}

	/**
	 * @return 获取 materialId
	 */
	public String getMaterialId() {
		return materialId;
	}

	/**
	 * @param materialId
	 *            设置 materialId
	 */
	public void setMaterialId(final String materialId) {
		this.materialId = materialId;
	}

	/**
	 * @return 获取 usedType
	 */
	public String getUsedType() {
		return usedType;
	}

	/**
	 * @param usedType
	 *            设置 usedType
	 */
	public void setUsedType(final String usedType) {
		this.usedType = usedType;
	}

	/**
	 * @return 获取 adUrl
	 */
	public String getAdUrl() {
		return adUrl;
	}

	/**
	 * @param adUrl
	 *            设置 adUrl
	 */
	public void setAdUrl(final String adUrl) {
		this.adUrl = adUrl;
	}

	/**
	 * @return 获取 mediaName
	 */
	public String getMediaName() {
		return mediaName;
	}

	/**
	 * @param mediaName
	 *            设置 mediaName
	 */
	public void setMediaName(final String mediaName) {
		this.mediaName = mediaName;
	}

	/**
	 * @param id
	 *            设置 id
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return 获取 name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            设置 name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return 获取 mediaid
	 */
	public String getMediaid() {
		return mediaid;
	}

	/**
	 * @param mediaid
	 *            设置 mediaid
	 */
	public void setMediaid(final String mediaid) {
		this.mediaid = mediaid;
	}

	/**
	 * @return 获取 addTime
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime
	 *            设置 addTime
	 */
	public void setAddTime(final Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * @return 获取 updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            设置 updateTime
	 */
	public void setUpdateTime(final Date updateTime) {
		this.updateTime = updateTime;
	}

}
