/****************************************************************
 *  系统名称  ： '[cps-background-spread]'
 *  文件名    ： DropDownListData.java
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
 * @简述： 下拉框
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-5-15 下午06:23:41
 */
public class DropDownListData implements Serializable {
	private static final long serialVersionUID = -7862316638333859813L;
	private String id;
	private String name;
	private String generate;

	/**
	 * @return 获取 generate
	 */
	public String getGenerate() {
		return generate;
	}

	/**
	 * @param generate
	 *            设置 generate
	 */
	public void setGenerate(String generate) {
		this.generate = generate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
