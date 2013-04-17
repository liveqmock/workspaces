/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： PositionDateBase.java
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
import com.iwgame.xmvp.shared.Media;

/**
 * 类说明
 * 
 * @简述： 广告位
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-8 上午11:29:03
 */
@BizLog(value = "POS", createTemplate = "创建广告位", relId = "id")
public class PositionDateBase implements Serializable {

	private static final long serialVersionUID = -8652480658771285340L;

	private String id;
	private String name;
	private String mediaId;
	private String adId;
	@BizLogField("广告位图例")
	private String legend; // 图片的物理路径
	@BizLogField("售卖单位")
	private int units;
	@BizLogField("一般日价格")
	private Double generalPrice;
	@BizLogField("特殊日价格")
	private Double specialPrice;
	@BizLogField("广告位规格")
	private String format;
	@BizLogField("广告位说明")
	private String explain;
	@BizLogField("备注")
	private String remark;
	private Date addTime;
	private Date updateTime;
	private String mediaName;
	private String unitsName;
	private Media media;
	@BizLogField("地址")
	private String adds;// 链接
	private int width;
	private int height;
	private int capacity;
	private String contentType;

	private int type;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return 获取 adds
	 */
	public String getAdds() {
		return adds;
	}

	/**
	 * @param adds
	 *            设置 adds
	 */
	public void setAdds(final String adds) {
		this.adds = adds;
	}

	/**
	 * @return 获取 width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            设置 width
	 */
	public void setWidth(final int width) {
		this.width = width;
	}

	/**
	 * @return 获取 height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height
	 *            设置 height
	 */
	public void setHeight(final int height) {
		this.height = height;
	}

	/**
	 * @return 获取 capacity
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity
	 *            设置 capacity
	 */
	public void setCapacity(final int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return 获取 contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            设置 contentType
	 */
	public void setContentType(final String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return 获取 media
	 */
	public Media getMedia() {
		return media;
	}

	/**
	 * @param media
	 *            设置 media
	 */
	public void setMedia(final Media media) {
		this.media = media;
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
	 * @return 获取 adId
	 */
	public String getAdId() {
		return adId;
	}

	/**
	 * @param adId
	 *            设置 adId
	 */
	public void setAdId(final String adId) {
		this.adId = adId;
	}

	/**
	 * @return 获取 legend
	 */
	public String getLegend() {
		return legend;
	}

	/**
	 * @param legend
	 *            设置 legend
	 */
	public void setLegend(final String legend) {
		this.legend = legend;
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
	 * @return 获取 generalPrice
	 */
	public Double getGeneralPrice() {
		return generalPrice;
	}

	/**
	 * @param generalPrice
	 *            设置 generalPrice
	 */
	public void setGeneralPrice(final Double generalPrice) {
		this.generalPrice = generalPrice;
	}

	/**
	 * @return 获取 specialPrice
	 */
	public Double getSpecialPrice() {
		return specialPrice;
	}

	/**
	 * @param specialPrice
	 *            设置 specialPrice
	 */
	public void setSpecialPrice(final Double specialPrice) {
		this.specialPrice = specialPrice;
	}

	/**
	 * @return 获取 format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            设置 format
	 */
	public void setFormat(final String format) {
		this.format = format;
	}

	/**
	 * @return 获取 explain
	 */
	public String getExplain() {
		return explain;
	}

	/**
	 * @param explain
	 *            设置 explain
	 */
	public void setExplain(final String explain) {
		this.explain = explain;
	}

	/**
	 * @return 获取 remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            设置 remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
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
