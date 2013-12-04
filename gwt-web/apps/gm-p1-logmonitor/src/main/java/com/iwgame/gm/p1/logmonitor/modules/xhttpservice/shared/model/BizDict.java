/****************************************************************
 *  文件名     ： BizDict.java
 *  日期         :  2012-10-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.model;

import java.io.Serializable;

/** 
 * @ClassName:    BizDict 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-19上午10:53:05
 * @Version:      1.0 
 */
public class BizDict implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4693033504758621470L;

	private int id;
	
	private String bizname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBizname() {
		return bizname;
	}

	public void setBizname(String biz_name) {
		this.bizname = biz_name;
	}
	
}
