/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LandingDateBase.java
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
 * @简述： 到达页
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-24 上午09:36:01
 */
@BizLog(value = "LANDING", createTemplate = "创建到达页", relId = "id")
public class LandingDateBase implements Serializable {

	private static final long serialVersionUID = 6662816118610130923L;

	private int id;
	@BizLogField("名称")
	private String name;
	@BizLogField("链接")
	private String link;
	@BizLogField("素材ID")
	private String materialId;
	private String materialUrl;
	@BizLogField("状态")
	private int status;
	@BizLogField("说明")
	private String note;
	private Date addTime;
	private Date updateTime;

	private String mname;
	private int width;
	private int height;
	private String contentType; // 素材文档类型
	private int capacity;

	/**
	 * @return 获取 mname
	 */
	public String getMname() {
		return mname;
	}

	/**
	 * @param mname
	 *            设置 mname
	 */
	public void setMname(final String mname) {
		this.mname = mname;
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

	private Media media;

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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link
	 *            the link to set
	 */
	public void setLink(final String link) {
		this.link = link;
	}

	/**
	 * @return the 素材路径
	 */
	public String getMaterialUrl() {
		return materialUrl;
	}

	/**
	 * @param materialUrl
	 *            the 素材路径
	 */
	public void setMaterialUrl(final String url) {
		materialUrl = url;
	}

	/**
	 * @return the 状态
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the 状态
	 */
	public void setStatus(final int stats) {
		status = stats;
	}

	/**
	 * @return the 说明
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the 说明
	 */
	public void setNote(final String ps) {
		note = ps;
	}

	/**
	 * @return the addTime
	 */
	public Date getAddTime() {
		return addTime;
	}

	/**
	 * @param addTime
	 *            the addTime to set
	 */
	public void setAddTime(final Date addTime) {
		this.addTime = addTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            the updateTime to set
	 */
	public void setUpdateTime(final Date updateTime) {
		this.updateTime = updateTime;
	}

}
