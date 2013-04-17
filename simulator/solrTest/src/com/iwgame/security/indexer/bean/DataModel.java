/****************************************************************
 *  文件名     ： DataModle.java
 *  日期         :  2013-1-23
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.security.indexer.bean;

import org.apache.solr.client.solrj.beans.Field;

/** 
 * @类名:    DataModle 
 * @描述:    TODO(...) 

 * @作者:    吴君杰
 * @邮件:    wujunjie@iwgame.com
 * @日期:    2013-1-23上午9:22:31
 * @版本:    1.0 
 */
public class DataModel {

	@Field
	private String id;

	@Field
	private String name;

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

}
