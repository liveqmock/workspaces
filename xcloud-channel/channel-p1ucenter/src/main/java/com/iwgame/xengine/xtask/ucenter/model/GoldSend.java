/****************************************************************
 *  系统名称  ： '消息任务系统-蜀门&醉逍遥'
 *  文件名    ： Notice.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.ucenter.model;


import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 类说明
 * 
 * @简述： 玩家水晶点卡充值活动相关对象
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-4-11 下午06:32:33
 */
public class GoldSend implements IwAnnotation{

	private static final long serialVersionUID = -4912242007827012912L;
	
	private final Logger logger = Logger.getLogger(GoldSend.class);
	
	@NotEmpty
	private Integer activeid; // 活动ID
	@NotEmpty
	private String activename; // 活动名称
	@NotEmpty
	private Integer accountid; // 账号ID
	@NotEmpty
	private String name; // 用户名
	@NotEmpty
	private String actor; // 角色名
	@NotEmpty
	private Integer type; // 发奖类型，1:等级;2:消耗点数;
	@NotEmpty
	private Integer gameid; // 游戏，蜀门=1，醉逍遥=2
	@NotEmpty
	private Integer zoneid; // 大区ID，与账号中心保持同步
	@NotEmpty
	private Integer value; // 值
	
	private String appname; // 请求来源

	public GoldSend() {

	}

	public GoldSend(String source) throws Exception {
		try {
			JSONObject jsonObject = JSONObject.fromObject(source);
			this.activeid = jsonObject.get("activeid") == null ? 1 : jsonObject.getInt("activeid");
			this.activename = jsonObject.getString("activename");
			this.accountid = Integer.valueOf(jsonObject.getString("accountid"));
			this.name = jsonObject.getString("name");
			this.actor = jsonObject.getString("actor");
			this.type = Integer.valueOf(jsonObject.getString("type"));
			this.gameid = Integer.valueOf(jsonObject.getString("gameid"));
			this.zoneid = Integer.valueOf(jsonObject.getString("zoneid"));
			this.value = Integer.valueOf(jsonObject.getString("value"));
			this.appname = String.valueOf(jsonObject.getString("appname"));
		}catch (Exception e) {
			logger.info("水晶活动参数异常,请求:"+source,e);
		}
	}

	public Integer getActiveid() {
		return activeid;
	}

	public void setActiveid(Integer activeid) {
		this.activeid = activeid;
	}

	public String getActivename() {
		return activename;
	}

	public void setActivename(String activename) {
		this.activename = activename;
	}

	public Integer getAccountid() {
		return accountid;
	}

	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGameid() {
		return gameid;
	}

	public void setGameid(Integer gameid) {
		this.gameid = gameid;
	}

	public Integer getZoneid() {
		return zoneid;
	}

	public void setZoneid(Integer zoneid) {
		this.zoneid = zoneid;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

}
