/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： IwResultTrack.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 类说明
 * @简述：结果跟踪表
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-19 上午09:17:10
 */
public class IwResultTrack implements Serializable{
	private static final long serialVersionUID = 483220598374665832L;
	private int id; 	//自增 	 
	private String batchid; 	//批次号 	 
	private int trackType; 	//类型  1:封杀,2:冻结,3:解封,4:安全模式,5:解除安全模式
	private int submitNum; 	//提交数量 	 
	private int successNum; 	//完成数量  
	private int failedNum; 	//失败数量 
	private Date submitTime; 	//提交时间 
	private Date finishTime; 	//完成时间 
	private String operator; 	//操作人 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
	public int getTrackType() {
		return trackType;
	}
	public void setTrackType(int trackType) {
		this.trackType = trackType;
	}
	public int getSubmitNum() {
		return submitNum;
	}
	public void setSubmitNum(int submitNum) {
		this.submitNum = submitNum;
	}
	public int getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}
	public int getFailedNum() {
		return failedNum;
	}
	public void setFailedNum(int failedNum) {
		this.failedNum = failedNum;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
