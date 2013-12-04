/****************************************************************
 *  文件名     ： SmsParamBean.java
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
 * @类名:   SmsParamBean 
 * @描述:  	TODO(...) 
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24上午10:59:23
 * @版本:   1.0
 */
public class SmsParamBean extends ParamBean implements IwAnnotation{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5441701867715416915L;

	@NotEmpty
	private String phone;      

	@NotEmpty
	private String message;    
	
	@NotEmpty
	private Integer queueNo;    	

	/**
	 * 
	 * @说明: 手机号,必填不能为空
	 * @return: String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 
	 * @说明: 短信内容,必填不能为空
	 * @return: String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 
	 * @说明: 短信通道编号
	 * @return: Integer
	 */
	public Integer getQueueNo() {
		return queueNo;
	}

	/**
	 * @param queueNo the queueNo to set
	 */
	public void setQueueNo(Integer queueNo) {
		this.queueNo = queueNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
