/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： DangerIdCard.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.common.shared.model;

import java.io.Serializable;
import java.util.Date;

import com.iwgame.gm.p1.common.server.log.BizLog;
import com.iwgame.gm.p1.common.server.log.BizLogField;
import com.iwgame.gm.p1.security.common.shared.util.ConstantShared;

/**
 * 类说明
 * @简述： 高危身份证表
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-19 上午09:18:05
 */
@BizLog(value=ConstantShared.TABLE_DANGER_ID_CARD,createTemplate="添加高危身份证",relId="id")
public class DangerIdCard  implements Serializable{
	private static final long serialVersionUID = -3127144294378730221L;
	private int id; 	//自增 	 
	@BizLogField("身份证号")
	private String idCard;  	//身份证号
	@BizLogField("原因备注")
	private String causeNote; 	//原因备注
	private Date createTime; 	//创建时间 
	private Date modifyTime; //修改日期
	@BizLogField("操作人 ")
	private String opreator;//操作人
	private Integer status;//删除状态 0：有效 1：无效
	
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getOpreator() {
		return opreator;
	}
	public void setOpreator(String opreator) {
		this.opreator = opreator;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getCauseNote() {
		return causeNote;
	}
	public void setCauseNote(String causeNote) {
		this.causeNote = causeNote;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
