/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： UserPoints.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.model;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.wg1.service.impl.XtaskServiceImpl;

import net.sf.json.JSONObject;

/**
 * 类说明
 * 
 * @简述：用户积分对象
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午06:00:11
 */
public class UserPoints implements Serializable {
	private Logger logger = Logger.getLogger(XtaskServiceImpl.class);
	private static final long serialVersionUID = -8795826507898642106L;

	private String pointtype= "";
	private String username= "";
	private String amount= "";
	private String source= "";
	private String operation= "";
	private String location= "";
	private String orderNo= "";
	private String desc ="火把";

	public UserPoints() {

	}

	public UserPoints(String jsonString) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			this.pointtype = jsonObject.getString("pointtype");
			this.username = jsonObject.getString("username");
			this.amount = jsonObject.getString("amount");
			this.source = jsonObject.getString("source");
			this.operation = jsonObject.getString("operation");
			this.location = jsonObject.getString("location");
			this.orderNo = jsonObject.getString("orderNo");
			if (jsonObject.has("desc")) {
				this.desc = jsonObject.getString("desc");
			}
		} catch (Exception e) {
			logger.error("[points]:" + e);
		}
	}

	
	public String getPointtype() {
		return pointtype;
	}

	public void setPointtype(String pointtype) {
		this.pointtype = pointtype;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
      
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
    
	public String getTranslatedContent() {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			if (pointtype != null && amount !=null) {
				stringBuilder.append("积分名：").append(pointtype).append(",");
				stringBuilder.append("数量：").append(amount).append(";");
				String returnValueString = stringBuilder.toString();
				return returnValueString;
			}
			return "";
		} catch (Exception e) {
			return "";
		}
	}
	
	
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("pointtype:").append(getPointtype()).append("&");
		stringBuilder.append("username:").append(getUsername()).append("&");
		stringBuilder.append("amount:").append(getAmount()).append("&");
		stringBuilder.append("source:").append(getSource()).append("&");
		stringBuilder.append("operation:").append(getOperation()).append("&");
		stringBuilder.append("location:").append(getLocation()).append("&");
		stringBuilder.append("orderNo:").append(getOrderNo());
		return stringBuilder.toString();
	}
	
}
