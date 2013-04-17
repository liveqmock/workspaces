/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ccc.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;

import com.iwgame.gm.p1.common.server.log.BizLog;
import com.iwgame.gm.p1.common.server.log.BizLogField;

/**
 * 类说明
 * 
 * @简述： 广告模型
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-8-22 上午10:07:36
 */
@BizLog(value = "KEYS", createTemplate = "创建关键字", relId = "id")
public class AdvertisementInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7021827564941467138L;

	private String id = ""; // 广告ID
	private String keyword = ""; // 关键字文本
	@BizLogField("广告分类")
	private String type = ""; // 关键词类型
	@BizLogField("广告标识")
	private String mark = ""; // 关键词标识
	@BizLogField("地址")
	private String url = ""; // 关键词链接
	private String addtime = ""; // 添加时间
	private String media = ""; // 媒体id
	private String mediaName = ""; // 媒体名
	@BizLogField("状态")
	private String status = ""; // 状态 0 ：禁用 1：启用

	private boolean full = false; // 完整性标志

	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(final String mark) {
		this.mark = mark;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(final String addtime) {
		this.addtime = addtime;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(final String media) {
		this.media = media;
	}

	public boolean isFull() {
		return full;
	}

	public void setFull(final boolean full) {
		this.full = full;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(final String mediaName) {
		this.mediaName = mediaName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

}