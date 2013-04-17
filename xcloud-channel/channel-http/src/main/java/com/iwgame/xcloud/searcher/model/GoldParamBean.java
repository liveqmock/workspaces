/****************************************************************
 *  文件名     ： GoldParamBean.java
 *  日期         :  2012-9-21
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xcloud.searcher.model;

import net.sf.json.JSONObject;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * @类名:   GoldParamBean 
 * @描述:  	水晶点卡,javaBean
 * 
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24上午11:00:35
 * @版本:   1.0
 */
public class GoldParamBean extends ParamBean implements IwAnnotation {
	
	private static final long serialVersionUID = -4137608436078153185L;

	@NotEmpty
	private Integer activeid; 
	@NotEmpty
	private String activename; 
	@NotEmpty
	private Integer accountid;
	@NotEmpty
	private String name;  
	@NotEmpty
	private String actor;  
	@NotEmpty
	private Integer type; 
	@NotEmpty
	private Integer gameid;  
	@NotEmpty
	private Integer zoneid;  
	@NotEmpty
	private Integer value;  
	
	
	
	/**
	 * TODO: 无参构造函数
	 */
	public GoldParamBean() {
		
	}
	
	/**
	 * 
	 * @说明: 活动ID
	 * @return: Integer
	 */
	public Integer getActiveid() {
		return activeid;
	}
	public void setActiveid(Integer activeId) {
		this.activeid = activeId;
	}
	
	/**
	 * 
	 * @说明: 活动名称
	 * @return: String
	 */
	public String getActivename() {
		return activename;
	}
	public void setActivename(String activename) {
		this.activename = activename;
	}
	
	/**
	 * 
	 * @说明: 账号ID
	 * @return: Integer
	 */
	public Integer getAccountid() {
		return accountid;
	}
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	
	/**
	 * 
	 * @说明: 用户名
	 * @return: String
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @说明: 角色名
	 * @return: String
	 */
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	
	/**
	 * 
	 * @说明:  发奖类型，1:等级;2:消耗点数;
	 * @return: void
	 */
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 
	 * @说明: 游戏，蜀门=1，醉逍遥=2
	 * @return: void
	 */
	public Integer getGameid() {
		return gameid;
	}
	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}
	
	/**
	 * 
	 * @说明: 大区ID，与账号中心保持同步
	 * @return: void
	 */
	public Integer getZoneid() {
		return zoneid;
	}
	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}
	
	/**
	 * 
	 * @说明: 值
	 * @return: void
	 */
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
