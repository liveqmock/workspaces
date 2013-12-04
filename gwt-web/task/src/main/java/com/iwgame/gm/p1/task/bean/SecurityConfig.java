/****************************************************************
 *  文件名     ： SecurityConfig.java
 *  日期         :  2012-11-19
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.bean;

import java.io.Serializable;

/** 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-19下午03:01:16
 * @版本:   v1.0 
 */
public class SecurityConfig implements Serializable {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -2443067612267392863L;
	
	private String mailswitch = "off";

	private String xhttpservice_url;
	
	private String xhttpservice_key;
	
	private String websgs_url_sm;
	
	private String websgs_url_zxy;
	
	private String websgs_key_sm;
	
	private String websgs_key_zxy;
	
	private String xhttpservice_url_solr;
	

	public String getMailswitch() {
		return mailswitch;
	}

	public void setMailswitch(String mailswitch) {
		this.mailswitch = mailswitch;
	}

	public String getXhttpservice_url() {
		return xhttpservice_url;
	}

	public void setXhttpservice_url(String xhttpservice_url) {
		this.xhttpservice_url = xhttpservice_url;
	}
	
	public String getWebsgs_key_sm() {
		return websgs_key_sm;
	}

	public void setWebsgs_key_sm(String websgs_key) {
		this.websgs_key_sm = websgs_key;
	}

	public String getWebsgs_url_sm() {
		return websgs_url_sm;
	}

	public void setWebsgs_url_sm(String websgs_url) {
		this.websgs_url_sm = websgs_url;
	}

	public String getXhttpservice_key() {
		return xhttpservice_key;
	}

	public void setXhttpservice_key(String keyCode) {
		this.xhttpservice_key = keyCode;
	}

	public String getXhttpservice_url_solr() {
		return xhttpservice_url_solr;
	}

	public void setXhttpservice_url_solr(String xhttpservice_url_solr) {
		this.xhttpservice_url_solr = xhttpservice_url_solr;
	}

	public String getWebsgs_url_zxy() {
		return websgs_url_zxy;
	}

	public void setWebsgs_url_zxy(String websgs_url_zxy) {
		this.websgs_url_zxy = websgs_url_zxy;
	}

	public String getWebsgs_key_zxy() {
		return websgs_key_zxy;
	}

	public void setWebsgs_key_zxy(String websgs_key_zxy) {
		this.websgs_key_zxy = websgs_key_zxy;
	}
}
