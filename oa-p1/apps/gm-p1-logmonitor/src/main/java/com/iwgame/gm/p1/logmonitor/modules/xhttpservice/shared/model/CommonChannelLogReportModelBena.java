/****************************************************************
 *  文件名     ： CommonChannelLogDetailModelBena.java
 *  日期         :  2012-10-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model;

import java.io.Serializable;

/** 
 * @ClassName:    CommonChannelLogReportModelBena 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-19下午02:28:13
 * @Version:      1.0 
 */
public class CommonChannelLogReportModelBena implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2416946284204248451L;

	private int id;
	
	private String dates;
	
	private String product_id;
	
	private int biz_code;
	
	private String biz_name;
	
	private String alias_name;
	
	private int success;
	
	private int failure;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDates() {
		return dates;
	}

	public void setDates(String dates) {
		this.dates = dates;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getBiz_code() {
		return biz_code;
	}

	public void setBiz_code(int biz_code) {
		this.biz_code = biz_code;
	}

	public String getBiz_name() {
		return biz_name;
	}

	public void setBiz_name(String biz_name) {
		this.biz_name = biz_name;
	}

	public String getAlias_name() {
		return alias_name;
	}

	public void setAlias_name(String alias_name) {
		this.alias_name = alias_name;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getFailure() {
		return failure;
	}

	public void setFailure(int failure) {
		this.failure = failure;
	}
}
