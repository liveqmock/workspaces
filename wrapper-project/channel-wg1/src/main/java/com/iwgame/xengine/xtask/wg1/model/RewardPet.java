/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： Pet.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.model;

import java.io.Serializable;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 类说明
 * @简述： 宠物赠送策略
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-5-28 下午05:12:28
 */
public class RewardPet implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8783595828300652275L;
	
	private String guid="";   //#guid: 大区编号
	private String operater= "";//#operater: 操作人员即发起这次发奖操作的人
	private String username = "";//#username：账号
	private String content= "";//#content：宝石内容json格式{"content":[{“id”:1},{“id”:2}]}
	private String source = "";//#source: 来源
	private String uptime= "";  //#uptime: 时间，主要用于跟踪数据
	private String desc;		//

		
//	private String desc;		//
	
	public RewardPet(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		this.guid = getJsonString(jsonObject,"guid");
		this.operater = getJsonString(jsonObject,"operater");
		this.username = getJsonString(jsonObject,"username");
		this.uptime = getJsonString(jsonObject,"uptime");
		this.content = getJsonString(jsonObject,"content");
		this.source = getJsonString(jsonObject,"source");
		this.desc= getJsonString(jsonObject,"desc");
	}
	
	
	
	public String getDesc() {
		return desc;
	}



	public void setDesc(String desc) {
		this.desc = desc;
	}



	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getOperater() {
		return operater;
	}
	public void setOperater(String operater) {
		this.operater = operater;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getContent() {
		return content;
	}
	public String getTranslatedContent() {
		try {
			StringBuilder stringBuilder = new StringBuilder();
			if (content != null) {
				JSONObject jsonObject = JSONObject.fromObject(content);
				JSONArray jsonArray = jsonObject.getJSONArray("content");
				
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jObject = jsonArray.getJSONObject(i);
					stringBuilder.append("宠物名：").append(getJsonString(jObject, "name")).append(",");
					stringBuilder.append("数量：").append(getJsonString(jObject, "num")).append(";");
				}
				String returnValueString = stringBuilder.toString();
				return returnValueString;
			} 
			return content;
		} catch (Exception e) {
			return "";
		}
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
/*
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}*/

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("guid:").append(getGuid()).append("&");
		stringBuilder.append("operater:").append(getOperater()).append("&");
		stringBuilder.append("username:").append(getUsername()).append("&");
		stringBuilder.append("uptime:").append(getUptime()).append("&");
		stringBuilder.append("source:").append(getSource()).append("&");
		stringBuilder.append("content:").append(getContent());
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
