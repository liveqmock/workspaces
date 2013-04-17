/****************************************************************
 *  文件名     ： MailParamBean.java
 *  日期         :  2012-9-7
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.model;

import net.sf.json.JSONObject;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * @类名: MailParamBean
 * @描述: 邮件发送,javaBean
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-24上午11:00:17
 * @版本: 1.0
 */
public class MailParamBean extends ParamBean implements IwAnnotation{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5327677183204716548L;

	@NotEmpty
	private String templateId;  

	@NotEmpty
	private String emailAddress; 

	private String FNAME; 

	private String LNAME; 

	private String aparam; 

	private String bparam; 

	private String cparam; 

	/**
	 * 
	 * @说明: 模板ID
	 * @return: String
	 */
	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	
	/**
	 * 
	 * @说明: Email地址
	 * @return: String
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	/**
	 * 
	 * @说明: 动态参数一
	 * @return: String
	 */
	public String getFNAME() {
		return FNAME;
	}

	public void setFNAME(String fNAME) {
		FNAME = fNAME;
	}

	
	/**
	 * 
	 * @说明: 动态参数二
	 * @return: String
	 */
	public String getLNAME() {
		return LNAME;
	}

	public void setLNAME(String lNAME) {
		LNAME = lNAME;
	}

	
	/**
	 * 
	 * @说明: 动态参数三
	 * @return: String
	 */
	public String getAparam() {
		return aparam;
	}

	public void setAparam(String aparam) {
		this.aparam = aparam;
	}

	
	/**
	 * 
	 * @说明: 动态参数四
	 * @return: String
	 */
	public String getBparam() {
		return bparam;
	}

	public void setBparam(String bparam) {
		this.bparam = bparam;
	}

	
	/**
	 * 
	 * @说明: 动态参数五
	 * @return: String
	 */
	public String getCparam() {
		return cparam;
	}

	public void setCparam(String cparam) {
		this.cparam = cparam;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
