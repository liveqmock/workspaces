/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MaterialDateBase.java
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
 * @简述： 素材
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-25 上午10:02:55
 */
public class MaterialDateBase implements Serializable {

	private static final long serialVersionUID = 5433211714033513451L;

	private String id;
	private String name;
	private String type;// sql语句 根据Id得到名字
	private int typeAdd;
	private String url;
	private int width;
	private int height;
	private String contentType; // 素材文档类型
	private int capacity;
	private String note;
	private Date addTime;
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
	public void setMedia(Media media) {
		this.media = media;
	}

	/**
	 * @return 获取 typeAdd
	 */
	public int getTypeAdd() {
		return typeAdd;
	}

	/**
	 * @param typeAdd
	 *            设置 typeAdd
	 */
	public void setTypeAdd(int typeAdd) {
		this.typeAdd = typeAdd;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
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
	public void setName(String name) {
		this.name = name;
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
	 * @return 供查看素材用
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param 供查看素材用
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return 容量
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param 容量
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return 说明
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param 说明
	 */
	public void setNote(String explain) {
		note = explain;
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
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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
	public void setWidth(int width) {
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
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return 获取 素材文档类型
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            设置 素材文档类型
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
