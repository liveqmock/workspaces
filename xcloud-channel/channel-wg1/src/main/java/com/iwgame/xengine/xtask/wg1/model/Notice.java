/****************************************************************
 *  系统名称  ： '消息任务系统-石器部落'
 *  文件名    ： Notice.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.wg1.model;

import net.sf.json.JSONObject;

/**
 * 类说明
 * 
 * @简述： 公告对象
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-11 下午06:32:33
 */
public class Notice {
	private String guid; // 大区ID，ALL表示wield全服
	private int type; // 类型,预留扩展字段
	private String content; // 公告内容
	private String operater; // 操作人员

	public Notice() {
	};

	public Notice(String jsonString) {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		this.guid = jsonObject.getString("guid");
		this.type = jsonObject.getInt("type");
		this.content = jsonObject.getString("content");
		this.operater = jsonObject.getString("operater");
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

}
