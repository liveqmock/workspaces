/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： PayableDataBase.java
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
 * @简述： 合同抬头
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午02:03:28
 */
public class PayableDataBase implements Serializable {

	private static final long serialVersionUID = -3299568334684999271L;

	private int id;
	private String title;
	private Date createTime;
	private Date updateTime;

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
	 * @return 获取 title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            设置 title
	 */
	public void setTitle(final String title) {
		this.title = title;
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

}
