/****************************************************************
 *  文件名     ： XtaskLog.java
 *  日期         :  2012-9-13
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xtask.support.model;

import java.util.Date;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * @类名:   SupportLog 
 * @描述:  	日志存储对象
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-09-25上午09:54:16
 * @版本:   1.0
 */
public class SupportLog implements IwAnnotation{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4957294844397230625L;

	@NotEmpty
	private String productid; 				//产品ID
	
	@NotEmpty
	private Integer bizcode;   				//业务code   比如0:道具卡,1:踢人,2:水晶....
	
	@NotEmpty
	private String resource;				//请求数据
	
	@NotEmpty
	private Integer logtype = 1;  			//0:SUCCESS,1:ERROR
	
	@NotEmpty
	private Date datatime = new Date();		//当前时间
	
	private String lognote;     			//日志内容
	
	private String appname;					//应用名称
	
	private String val1;					//备用字段1
	
	private String val2;					//备用字段2
	
	private String val3;					//备用字段3
	
	private String val4;					//备用字段4

	private String val5; // 备用字段5

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public Integer getBizcode() {
		return bizcode;
	}

	public void setBizcode(Integer bizCode) {
		this.bizcode = bizCode;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Integer getLogtype() {
		return logtype;
	}

	public void setLogtype(Integer logtype) {
		this.logtype = logtype;
	}

	public String getLognote() {
		return lognote;
	}

	public void setLognote(String note) {
		this.lognote = note;
	}
	
	public Date getDatatime() {
		return datatime;
	}

	public void setDatatime(Date datatime) {
		this.datatime = datatime;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getVal1() {
		return val1;
	}

	public void setVal1(String value1) {
		this.val1 = value1;
	}

	public String getVal2() {
		return val2;
	}

	public void setVal2(String value2) {
		this.val2 = value2;
	}

	public String getVal3() {
		return val3;
	}

	public void setVal3(String val3) {
		this.val3 = val3;
	}

	public String getVal4() {
		return val4;
	}

	public void setVal4(String val4) {
		this.val4 = val4;
	}

	/**
	 * @return the val5
	 */
	public String getVal5() {
		return val5;
	}

	/**
	 * @param val5
	 *            the val5 to set
	 */
	public void setVal5(String val5) {
		this.val5 = val5;
	}

}
