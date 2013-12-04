/****************************************************************
 *  系统名称  ：  '消息任务系统-公共服务-业务通道'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xtask.support.model;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * 
 * @类名:   SupportSms 
 * @描述:  	短信类
 *
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-09-25上午09:54:30
 * @版本:   1.0
 */
public class SupportSms implements Serializable{
	
	private static final long serialVersionUID = -3432228673003139236L;
	
	private String phone;
	
	private String message;
	
	public SupportSms(){
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
