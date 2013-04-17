/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： FrameDateBase.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.iwgame.gm.p1.common.server.log.BizLog;
import com.iwgame.gm.p1.common.server.log.BizLogField;

/**
 * @Description: 框架数据对象
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-13 下午5:26:11
 */
@BizLog(value = "FRAMEMGR", createTemplate = "创建框架", relId = "id")
public class FrameDataBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4993875098709231182L;

	private int id;
	private String mediaType;
	private String media;
	private String mediaName;
	private String name;
	/** 框架初始金额  */
	private Double amount;
	/**  框架折扣*/
	@BizLogField("框架折扣")
	private Double rebate;
	/** 框架保证金 */
	@BizLogField("框架保证金")
	private Double securityDeposit;
	/** 框架余额 */
	private Double balance;
	@BizLogField("开始时间")
	private Date startTime;
	@BizLogField("结束时间")
	private Date endTime;
	
	/** 框架初始金额  */
	public Double getAmount() {
		return amount;
	}
	/** 框架初始金额  */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**  框架折扣*/
	public Double getRebate() {
		return rebate;
	}
	/**  框架折扣*/
	public void setRebate(Double rebate) {
		this.rebate = rebate;
	}
	/** 框架保证金 */
	public Double getSecurityDeposit() {
		return securityDeposit;
	}
	/** 框架保证金 */
	public void setSecurityDeposit(Double securityDeposit) {
		this.securityDeposit = securityDeposit;
	}
	/** 框架余额 */
	public Double getBalance() {
		return balance;
	}
	/** 框架余额 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	
}

