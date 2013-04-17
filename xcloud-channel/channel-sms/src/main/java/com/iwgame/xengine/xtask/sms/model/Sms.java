/****************************************************************
 *  系统名称  ：  '消息任务系统-公共服务-业务通道'
 *  文件名    ： Version.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.sms.model;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * 类说明
 * 
 * @简述： 短信类
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改：2012-4-18 上午10:13:26
 */
public class Sms implements IwAnnotation {

	private static final long serialVersionUID = -3432228673003139236L;

	private final Logger logger = Logger.getLogger(Sms.class);

	@NotEmpty
	private String phone; // 手机号

	@NotEmpty
	private String message; // 短信内容

	private String queueNo; // 短信通道编号

	private String appname; // 来源应用名称

	public Sms() {

	}

	public Sms(String resource) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(resource);
			this.phone = jsonObject.getString("phone");
			this.message = jsonObject.getString("message");
			this.appname = String.valueOf(jsonObject.get("appname"));
			this.queueNo = String.valueOf(jsonObject.get("queueNo"));
		} catch (Exception e) {
			logger.error("短信请求参数异常,请求:" + resource, e);
		}
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

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	public String getQueueNo() {
		return queueNo;
	}

	public void setQueueNo(String queueNo) {
		this.queueNo = queueNo;
	}

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
