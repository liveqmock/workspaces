package com.iwgame.xcloud.searcher.model;


import net.sf.json.JSONObject;

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
 * @修改：2012-5-3 下午06:16:34
 */
public class TalkParamBean extends ParamBean implements IwAnnotation {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8746224602627126943L;
	
	@NotEmpty
	private String guid;	  //大区ID
	@NotEmpty
	private String groupname; //组名称
	@NotEmpty
	private String username;  //角色名称
	@NotEmpty
	private int validtime;	  //禁言时间

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
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
