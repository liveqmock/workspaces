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
import java.util.Date;

/** 
 * @ClassName:    CommonChannelLogDetailModelBena 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-19下午02:28:13
 * @Version:      1.0 
 */
public class CommonChannelLogDetailModelBena implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2416946284204248451L;

	private int id;
	
	private String productid;
	
	private String appname;
	
	private String bizcode;
	
	private Date logintime;
	
	private String resource;
	
	private int logtype;
	
	private String lognote;
	
	private String var1;
	
	private String var2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getBizcode() {
		return bizcode;
	}

	public void setBizcode(String bizcode) {
		this.bizcode = bizcode;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public int getLogtype() {
		return logtype;
	}

	public void setLogtype(int logtype) {
		this.logtype = logtype;
	}

	public String getLognote() {
		return lognote;
	}

	public void setLognote(String lognote) {
		this.lognote = lognote;
	}

	public String getVar1() {
		return var1;
	}

	public void setVar1(String var1) {
		this.var1 = var1;
	}

	public String getVar2() {
		return var2;
	}

	public void setVar2(String var2) {
		this.var2 = var2;
	}
}
