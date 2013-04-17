/****************************************************************
 *  系统名称  ： '消息任务系统-公共服务-业务通道'
 *  文件名    ： SecurityMail.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.bean;

import net.sf.json.JSONObject;

/**
 * 
 * @描述:  	TODO(...) 
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-11-19下午04:49:48
 * @版本:   v1.0
 */
public class SecurityMail {

	private Integer templateId; 	// 模板ID

	private String emailAddress;// Email地址

	private String FNAME; 		// 

	private String LNAME; 		// 

	private String aparam; 		// 备用参数一  
	
	private String bparam; 		// 备用参数二
	
	private String cparam; 		// 备用参数三
	
	public SecurityMail() {

	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFNAME() {
		return FNAME;
	}

	public void setFNAME(String fNAME) {
		FNAME = fNAME;
	}

	public String getLNAME() {
		return LNAME;
	}

	public void setLNAME(String lNAME) {
		LNAME = lNAME;
	}

	public String getAparam() {
		return aparam;
	}

	public void setAparam(String aparam) {
		this.aparam = aparam;
	}

	public String getBparam() {
		return bparam;
	}

	public void setBparam(String bparam) {
		this.bparam = bparam;
	}

	public String getCparam() {
		return cparam;
	}

	public void setCparam(String cparam) {
		this.cparam = cparam;
	}
	
	
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
