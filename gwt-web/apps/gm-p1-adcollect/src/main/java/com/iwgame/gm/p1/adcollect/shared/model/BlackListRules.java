/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： BlackListRules.java
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
 * @简述： 黑名单规则表
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-9-25 下午03:33:23
 */
public class BlackListRules implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7813722255954919437L;

	private String id; // id
	private String project; // 项目
	private String count; // 数量
	private String status; // 状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
