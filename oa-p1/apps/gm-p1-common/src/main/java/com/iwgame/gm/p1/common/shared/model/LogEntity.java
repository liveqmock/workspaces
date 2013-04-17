/**      
 * LogEntity.java Create on 2012-9-27     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.common.shared.model;

import com.iwgame.xportal.common.server.SecurityUserHolder;

/**
 * @ClassName: LogEntity
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-27 下午02:15:49
 * @Version 1.0
 * 
 */
public class LogEntity {
	public LogEntity() {
	}

	private String types;
	private Object relId; // 用于数据关联的id 此处为广告ID
	private String note; // 内容
	private String insertTime; // 操作时间
	private String insertUser = SecurityUserHolder.getCurrentUser().getUsername();// 操作人

	public String getTypes() {
		return types;
	}

	public void setTypes(final String types) {
		this.types = types;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(final String insertUser) {
		this.insertUser = insertUser;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(final String insertTime) {
		this.insertTime = insertTime;
	}

	public Object getRelId() {
		return relId;
	}

	public void setRelId(final Object relId) {
		this.relId = relId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(final String note) {
		this.note = note;
	}

}
