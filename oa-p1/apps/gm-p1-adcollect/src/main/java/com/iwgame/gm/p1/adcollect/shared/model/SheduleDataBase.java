/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： SheduleDataBase.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.iwgame.gm.p1.common.server.log.BizLog;
import com.iwgame.gm.p1.common.server.log.BizLogField;

/**
 * 类说明
 * 
 * @简述： 广告排期
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-16 上午09:27:39
 */
@BizLog(value = "SHEDULE", createTemplate = "创建广告排期", relId = "id")
public class SheduleDataBase implements Serializable {

	private static final long serialVersionUID = 8017292267635176859L;

	private String id;
	@BizLogField("排期名称")
	private String name;
	private String mediaId;
	@BizLogField("素材")
	private String materialId;
	private String contractId;
	private String adName; // 里面存的是adid
	private String adId; // 里面存的是adid
	private Date startTime;
	private Date endTime;
	@BizLogField("广告组")
	private int adGroup;
	private double rebate;
	private int units;
	private double generalPrice;
	private double specialPrice;
	private double sumPrice;
	private String usedType;
	private double distribuRatio;
	private int types;
	@BizLogField("广告url")
	private String adUrl;
	@BizLogField("配送来源")
	private String source;
	private Date addTime;
	private Date updateTime;
	private String posId;

	private String startTimeString;
	private String endTimeString;
	private String mediaName;
	private String posName;
	private String materialName;
	private String groupName;
	private String contractItemno;
	private String unitsName;

	/**
	 * @return 获取 adId
	 */
	public String getAdId() {
		return adId;
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
	 * @param adId
	 *            设置 adId
	 */
	public void setAdId(final String adId) {
		this.adId = adId;
	}

	/**
	 * @return 获取 unitsName
	 */
	public String getUnitsName() {
		return unitsName;
	}

	/**
	 * @param unitsName
	 *            设置 unitsName
	 */
	public void setUnitsName(final String unitsName) {
		this.unitsName = unitsName;
	}

	/**
	 * @return 获取 contractItemno
	 */
	public String getContractItemno() {
		return contractItemno;
	}

	/**
	 * @param contractItemno
	 *            设置 contractItemno
	 */
	public void setContractItemno(final String contractItemno) {
		this.contractItemno = contractItemno;
	}

	/**
	 * @return 获取 groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName
	 *            设置 groupName
	 */
	public void setGroupName(final String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return 获取 materialName
	 */
	public String getMaterialName() {
		return materialName;
	}

	/**
	 * @param materialName
	 *            设置 materialName
	 */
	public void setMaterialName(final String materialName) {
		this.materialName = materialName;
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
	 * @return 获取 posName
	 */
	public String getPosName() {
		return posName;
	}

	/**
	 * @param posName
	 *            设置 posName
	 */
	public void setPosName(final String posName) {
		this.posName = posName;
	}

	/**
	 * @return 获取 startTimeString
	 */
	public String getStartTimeString() {
		return startTimeString;
	}

	/**
	 * @param startTimeString
	 *            设置 startTimeString
	 */
	public void setStartTimeString(final String startTimeString) {
		this.startTimeString = startTimeString;
	}

	/**
	 * @return 获取 endTimeString
	 */
	public String getEndTimeString() {
		return endTimeString;
	}

	/**
	 * @param endTimeString
	 *            设置 endTimeString
	 */
	public void setEndTimeString(final String endTimeString) {
		this.endTimeString = endTimeString;
	}

	/**
	 * @return 获取 posId
	 */
	public String getPosId() {
		return posId;
	}

	/**
	 * @param posId
	 *            设置 posId
	 */
	public void setPosId(final String posId) {
		this.posId = posId;
	}

	/**
	 * @return 获取 id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            设置 id
	 */
	public void setId(final String id) {
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
	 * @return 获取 mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId
	 *            设置 mediaId
	 */
	public void setMediaId(final String mediaId) {
		this.mediaId = mediaId;
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
	 * @return 获取 contractId
	 */
	public String getContractId() {
		return contractId;
	}

	/**
	 * @param contractId
	 *            设置 contractId
	 */
	public void setContractId(final String contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return 获取 adName
	 */
	public String getAdName() {
		return adName;
	}

	/**
	 * @param adName
	 *            设置 adName
	 */
	public void setAdName(final String adName) {
		this.adName = adName;
	}

	/**
	 * @return 获取 startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            设置 startTime
	 */
	public void setStartTime(final Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return 获取 endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            设置 endTime
	 */
	public void setEndTime(final Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return 获取 adGroup
	 */
	public int getAdGroup() {
		return adGroup;
	}

	/**
	 * @param adGroup
	 *            设置 adGroup
	 */
	public void setAdGroup(final int adGroup) {
		this.adGroup = adGroup;
	}

	/**
	 * @return 获取 rebate
	 */
	public double getRebate() {
		return rebate;
	}

	/**
	 * @param rebate
	 *            设置 rebate
	 */
	public void setRebate(final double rebate) {
		this.rebate = rebate;
	}

	/**
	 * @return 获取 units
	 */
	public int getUnits() {
		return units;
	}

	/**
	 * @param units
	 *            设置 units
	 */
	public void setUnits(final int units) {
		this.units = units;
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
	 * @return 获取 distribuRatio
	 */
	public double getDistribuRatio() {
		return distribuRatio;
	}

	/**
	 * @param distribuRatio
	 *            设置 distribuRatio
	 */
	public void setDistribuRatio(final double distribuRatio) {
		this.distribuRatio = distribuRatio;
	}

	/**
	 * @return 获取 types
	 */
	public int getTypes() {
		return types;
	}

	/**
	 * @param types
	 *            设置 types
	 */
	public void setTypes(final int types) {
		this.types = types;
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
	 * @return 获取 source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source
	 *            设置 source
	 */
	public void setSource(final String source) {
		this.source = source;
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
