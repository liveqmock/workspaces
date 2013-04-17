/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： UserResouce.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.model;

import java.io.Serializable;

import net.sf.json.JSONObject;

/**
 * 类说明
 * 
 * @简述： 发奖对象
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-10 下午06:00:11
 */
public class UserResouce implements Serializable {

	private static final long serialVersionUID = -8795826507898642106L;
	private int id; // | 编号 | int | 自增的id |
	private String batchid; // | 批次编号 | varchar | 用来作为批次导入的数据标志 |
	private String scope; // | 范围
	private String username; // | 帐号名称 | varchar | |
	private String guid; // | 大区编号 | varchar \\ | |
	private String resourceType; // | 礼品类型 | int | 礼品类型
									// 0：默认赠送礼品1：vip礼品，礼品内容中金币计入vip计算。
	private String resouces; // | 资源属性 | varchar&nbsp; \\ | lmoney money renown
								// glory food wood \\
	private String operater; // | 操作人 | varchar | 记录发起这次奖励的业务操作人名称 |
	private String source; // | 来源 | varchar | 比如：oa,平台导入等。。 |
	private String updatetime; // | 插入时间 | varchar \\ | |
	private String status; // 状态 默认为"待发送"
	private String desc; //	备注

	public UserResouce() {

	}

	public UserResouce(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		this.batchid = getJsonString(jsonObject,"batchid");
		this.username = getJsonString(jsonObject,"username");
		this.scope = getJsonString(jsonObject,"scope");
		this.guid = getJsonString(jsonObject,"guid");
		this.resourceType = getJsonString(jsonObject,"sourcetype");
		this.resouces = getJsonString(jsonObject,"resouces");
		this.operater = getJsonString(jsonObject,"operater");
		this.source = getJsonString(jsonObject,"source");
		this.updatetime = getJsonString(jsonObject,"uptime");
		this.desc = getJsonString(jsonObject,"desc");
	}

	/**
	 * @param id
	 * @param batchid
	 * @param username
	 * @param guid
	 * @param resource_type
	 * @param resouces
	 * @param operater
	 * @param source
	 */
	public UserResouce(int id, String batchid, String username, String guid, String resourceType, String resouces,
			String operater, String source, String updatetime) {
		super();
		this.id = id;
		this.batchid = batchid;
		this.username = username;
		this.guid = guid;
		this.resourceType = resourceType;
		this.resouces = resouces;
		this.operater = operater;
		this.source = source;
		this.updatetime = updatetime;
	}

	/**
	 * @param id
	 * @param batchid
	 * @param username
	 * @param guid
	 * @param resource_type
	 * @param resouces
	 * @param operater
	 * @param source
	 */
	public UserResouce(String batchid, String username, String guid, String resourceType, String resouces, String operater,
			String source) {
		super();
		this.batchid = batchid;
		this.username = username;
		this.guid = guid;
		this.resourceType = resourceType;
		this.resouces = resouces;
		this.operater = operater;
		this.source = source;
	}

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

	public String getUsername() {
		if (scope.equalsIgnoreCase("0")) {
			return "所有用户";
		} else if (scope.equalsIgnoreCase("1")) {
			return "所有在线用户";
		} else {
			return username;
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResource_type(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResouces() {
		return resouces;
	}

	public void setResouces(String resouces) {
		this.resouces = resouces;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTranlatedResouces() {
		StringBuilder stringBuilder = new StringBuilder();
		if (resouces != null) {
			JSONObject jsonObject = JSONObject.fromObject(resouces);
			//石牌
			if (!getJsonString(jsonObject, "battleFlagNum").equalsIgnoreCase("0") && !getJsonString(jsonObject, "battleFlagNum").equalsIgnoreCase("")) {
				stringBuilder.append(getJsonString(jsonObject, "battleFlagNum")).append("石牌").append(";");
			}
			//金币
			if (!getJsonString(jsonObject, "lmoney").equalsIgnoreCase("0") && !getJsonString(jsonObject, "lmoney").equalsIgnoreCase("")) {
				stringBuilder.append(getJsonString(jsonObject, "lmoney")).append("金币").append(";");
			}
			//银币
			if (!getJsonString(jsonObject, "money").equalsIgnoreCase("0") && !getJsonString(jsonObject, "money").equalsIgnoreCase("")) {
				stringBuilder.append(getJsonString(jsonObject, "money")).append("银币").append(";");
			}
			//食物
			if (!getJsonString(jsonObject, "food").equalsIgnoreCase("0") && !getJsonString(jsonObject, "food").equalsIgnoreCase("")) {
				stringBuilder.append(getJsonString(jsonObject, "food")).append("食物").append(";");
			}
			//木材
			if (!getJsonString(jsonObject, "wood").equalsIgnoreCase("0") && !getJsonString(jsonObject, "wood").equalsIgnoreCase("")) {
				stringBuilder.append(getJsonString(jsonObject, "wood")).append("木材").append(";");
			}
			//声望
			if (!getJsonString(jsonObject, "renown").equalsIgnoreCase("0") && !getJsonString(jsonObject, "renown").equalsIgnoreCase("")) {
				stringBuilder.append(getJsonString(jsonObject, "renown")).append("声望").append(";");
			}
			//荣誉
			if (!getJsonString(jsonObject, "glory").equalsIgnoreCase("0") && !getJsonString(jsonObject, "glory").equalsIgnoreCase("")) {
				stringBuilder.append(getJsonString(jsonObject, "glory")).append("荣誉").append(";");
			}
			
			String returnValueString = stringBuilder.toString();
			return returnValueString;
		} 
		return resouces;
	}
	
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("batchid:").append(getBatchid()).append("&");
		stringBuilder.append("guid:").append(getGuid()).append("&");
		stringBuilder.append("username:").append(getUsername()).append("&");
		stringBuilder.append("resouces:").append(getResouces());
		return stringBuilder.toString();
	}
	
	private String getJsonString(JSONObject jsonObject,String key) {
		try {
			return jsonObject.getString(key);
		} catch (Exception e) {
			return "";
		}
	}
	
}
