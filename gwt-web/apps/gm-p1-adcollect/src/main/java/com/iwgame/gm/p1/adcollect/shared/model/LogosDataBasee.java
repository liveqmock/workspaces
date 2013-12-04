/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LogosDataBasee.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.iwgame.xmvp.shared.Media;

/**
 * 类说明
 * 
 * @简述： LOGS
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午03:24:20
 */
public class LogosDataBasee implements Serializable {

	private static final long serialVersionUID = 5399134843356174777L;

	private int id;
	private String name;
	private Date createTime;
	private Date updateTime;
	private String path;
	private int width;
	private int height;
	private int capacity;
	private String contentType;
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
	 * @return 获取 id
	 */
	public int getId() {
		return id;
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
	 * @return 获取 createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            设置 createTime
	 */
	public void setCreateTime(final Date createTime) {
		this.createTime = createTime;
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

	/**
	 * @return 获取 path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            设置 path
	 */
	public void setPath(final String path) {
		this.path = path;
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

}
