package com.iwgame.xengine.xtask.game.model;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * 类说明
 * 
 * @简述： 禁言& 解除 Bean
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-5-3 下午06:16:34
 */
public class TalkBean implements IwAnnotation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 615355551760489998L;
	
	private final Logger logger = Logger.getLogger(TalkBean.class);
	
	@NotEmpty
	private String guid;	  	//大区ID
	@NotEmpty
	private String groupname; 	//组名称
	@NotEmpty
	private String rolename;  	//角色名称
	@NotEmpty
	private int validtime;	  	//禁言时间
	private String appname;		//请求来源应用名称
	private String source; 		// Json字符串

	public TalkBean(String source) {
		try {
			JSONObject jsonObject = JSONObject.fromObject(source);
			this.guid = jsonObject.getString("guid");
			this.groupname = jsonObject.getString("groupname");
			this.rolename = jsonObject.getString("username");
			this.validtime = jsonObject.getInt("validtime");
			this.appname = String.valueOf(jsonObject.get("appname"));
			this.source = source;
		}catch(Exception e){
			logger.error("[忽略]禁言&解除禁言失败,参数异常!",e);
		}
	}

	/**
	 * 大区ID
	 * @return
	 */
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	/**
	 * 大区组名称
	 * @return
	 */
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	/**
	 * 角色名称
	 * @return
	 */
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String username) {
		this.rolename = username;
	}

	/**
	 * 禁言时间
	 * @return
	 */
	public int getValidtime() {
		return validtime;
	}

	public void setValidtime(int validtime) {
		this.validtime = validtime;
	}

	/**
	 * 数据源
	 * @return
	 */
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}
}
