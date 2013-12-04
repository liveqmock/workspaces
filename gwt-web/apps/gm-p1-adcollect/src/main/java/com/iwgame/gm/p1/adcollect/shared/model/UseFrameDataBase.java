/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： UseFrameDataBase.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-17 下午6:41:09
 */
public class UseFrameDataBase  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8780491243224181185L;
	
	private int id;
	/**  媒体ID  */
	private String mediaId;
	/**  媒体名称  */
	private String mediaName;
	/**  框架ID  */
	private int frameId;
	/**  框架名称  */
	private String frameName;
	
	/**  更新时间  */
	private Date updateTime;
	/**  变更情况  */
	private String changes;
	/**  变更金额  */
	private Double changeAmount;
	/**  本次变更余额  */
	private Double balance;
	/**  合同ID  */
	private int contractId;
	
	/**  合同编号  */
	private String contractItmo;
	/**  使用详情  */
	private String usedDetails;
	/**  效果追踪  */
	private String effectTracking;
	
	private int result;  // 执行过程 返回的值
	
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public int getFrameId() {
		return frameId;
	}
	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}
	public String getFrameName() {
		return frameName;
	}
	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getChanges() {
		return changes;
	}
	public void setChanges(String changes) {
		this.changes = changes;
	}
	public Double getChangeAmount() {
		return changeAmount;
	}
	public void setChangeAmount(Double changeAmount) {
		this.changeAmount = changeAmount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public int getContractId() {
		return contractId;
	}
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	public String getContractItmo() {
		return contractItmo;
	}
	public void setContractItmo(String contractItmo) {
		this.contractItmo = contractItmo;
	}
	public String getUsedDetails() {
		return usedDetails;
	}
	public void setUsedDetails(String usedDetails) {
		this.usedDetails = usedDetails;
	}
	public String getEffectTracking() {
		return effectTracking;
	}
	public void setEffectTracking(String effectTracking) {
		this.effectTracking = effectTracking;
	}
	

}

