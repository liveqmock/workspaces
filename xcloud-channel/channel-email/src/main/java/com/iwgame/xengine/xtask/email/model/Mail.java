/****************************************************************
 *  系统名称  ： '消息任务系统-公共服务-业务通道'
 *  文件名    ： Mail.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.email.model;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 类说明
 * 
 * @简述： 邮件类
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改：2012-12-28 下午04:41:09
 */
public class Mail implements IwAnnotation {

	private static final long serialVersionUID = 483281409399011139L;

	private final Logger logger = Logger.getLogger(Mail.class);

	@NotEmpty
	private Integer templateId; // 模板ID

	@NotEmpty
	private String emailAddress;// Email地址

	private Integer channelid; // 内部通道ID

	private String FNAME; //

	private String LNAME; //

	private String aparam; // 备用参数一

	private String bparam; // 备用参数二

	private String cparam; // 备用参数三

	private String appname; // 来源应用名称

	private String provider; // 邮件提供商名称

	private String failed; // 失败

	public Mail() {

	}

	public Mail(String resource) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(resource);
			this.templateId = jsonObject.getInt("templateId");
			this.emailAddress = jsonObject.getString("emailAddress");
			this.FNAME = jsonObject.get("FNAME") == null ? "" : jsonObject.getString("FNAME");
			this.LNAME = jsonObject.get("LNAME") == null ? "" : jsonObject.getString("LNAME");
			this.aparam = jsonObject.get("aparam") == null ? "" : jsonObject.getString("aparam");
			this.bparam = jsonObject.get("bparam") == null ? "" : jsonObject.getString("bparam");
			this.cparam = jsonObject.get("cparam") == null ? "" : jsonObject.getString("cparam");
			this.appname = jsonObject.get("appname") == null ? "unknown" : jsonObject.getString("appname");
			this.provider = jsonObject.get("provider") == null ? "" : jsonObject.getString("provider");
			this.failed = jsonObject.get("failed") == null ? "" : jsonObject.getString("failed");
			this.channelid = jsonObject.get("templateId") == null ? -1 : jsonObject.getInt("templateId");
		} catch (Exception e) {
			logger.error("邮件请求参数异常,请求:" + resource, e);
		}
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

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * @param provider
	 *            the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}

	/**
	 * @return the failed
	 */
	public String getFailed() {
		return failed;
	}

	/**
	 * @param failed
	 *            the failed to set
	 */
	public void setFailed(String failed) {
		this.failed = failed;
	}

	/**
	 * @return the channelid
	 */
	public Integer getChannelid() {
		return channelid;
	}

	/**
	 * @param channelid
	 *            the channelid to set
	 */
	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
