/****************************************************************
 *  文件名     ： AppSource.java
 *  日期         :  2012-10-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model;

import java.io.Serializable;

/** 
 * @ClassName:    AppSource 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-19上午10:51:35
 * @Version:      1.0 
 */
public class AppSource implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6937103145441734215L;

	private String appName;
	
	private String aliasName;

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
}
