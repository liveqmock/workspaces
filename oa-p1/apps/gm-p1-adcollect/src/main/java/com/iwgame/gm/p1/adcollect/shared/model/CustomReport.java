/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： CustomReport.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.shared.model;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 * 类说明
 * @简述： 定制报表
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-11-7 下午05:15:22
 */
public class CustomReport implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4948366072741621569L;
	
	private String id;    //自增id
	private String name;   //报表名称     媒体类型_媒体_报表类型_汇总类型
	private String remark;  //功能备注
	private Date submitTime; //提交时间
	private Date finishTime; //完成时间
	private String operatior; //提交人
	private String param; //参数
	private String status; //状态     0：待处理   1：处理中  2：已完成
	private String filepath; //excel路径
	private String costtime; // 耗时
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getOperatior() {
		return operatior;
	}

	public void setOperatior(String operatior) {
		this.operatior = operatior;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getCosttime() {
		return costtime;
	}

	public void setCosttime(String costtime) {
		this.costtime = costtime;
	}

	public String toJsonContent() {
		JSONObject jsonObject = JSONObject.fromObject(this);
		return jsonObject.toString();
	}
	
}
