/**      
* KilledCause.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.common.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.iwgame.gm.p1.common.server.log.BizLog;
import com.iwgame.gm.p1.common.server.log.BizLogField;
import com.iwgame.gm.p1.security.common.shared.util.ConstantShared;

/** 
 * @简述: 封杀原因
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-15 下午02:11:55 
 */
@BizLog(value=ConstantShared.TABLE_KILLED_CAUSE_CONFIG,createTemplate="添加封杀原因",relId="id")
public class KilledCauseConfig implements Serializable{
	private static final long serialVersionUID = -7260545797682750537L;
	
	private Integer id;
	/**
	 * 方案编号 
	 */
	@BizLogField(value="方案编号")
	private String causeNumber;
	/**
	 * 封杀原因类型
	 * 1:外挂 2:盗号 3:帐号安全 4:其他
	 */
	@BizLogField("封杀原因类型")
	private Integer causeType;
	/**
	 * 封杀原因内容
	 */
	@BizLogField("封杀原因内容")
	private String causeNote;
	/**
	 * 封杀类型
	 */
	@BizLogField("封杀类型")
	private Integer killedType;
	
	private String creator;
	
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCauseNumber() {
		return causeNumber;
	}

	public void setCauseNumber(String causeNumber) {
		this.causeNumber = causeNumber;
	}

	public Integer getCauseType() {
		return causeType;
	}

	public void setCauseType(Integer causeType) {
		this.causeType = causeType;
	}

	public String getCauseNote() {
		return causeNote;
	}

	public void setCauseNote(String causeNote) {
		this.causeNote = causeNote;
	}

	public Integer getKilledType() {
		return killedType;
	}

	public void setKilledType(Integer killedType) {
		this.killedType = killedType;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
