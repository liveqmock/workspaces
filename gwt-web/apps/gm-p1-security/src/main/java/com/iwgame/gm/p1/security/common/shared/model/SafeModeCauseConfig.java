/**      
* SecuritySafeModeCause.java Create on 2012-11-16     
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
 * @简述: 安全模式备注
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期 2012-11-16 下午03:25:31 
 */
@BizLog(value=ConstantShared.TABLE_SAFE_MODE_CAUSE_CONFIG,createTemplate="添加安全模式备注",relId="id")
public class SafeModeCauseConfig implements Serializable{
	
	private static final long serialVersionUID = 6944645250460228381L;
	
	private Integer id;
	@BizLogField("原因备注")
	private String causeNote;
	
	private String creator;
	
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCauseNote() {
		return causeNote;
	}

	public void setCauseNote(String causeNote) {
		this.causeNote = causeNote;
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
