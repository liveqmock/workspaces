package com.iwgame.xcloud.searcher.model;


import net.sf.json.JSONObject;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * @类名:   KickParamBean 
 * @描述:  	踢人,javaBean
 * 
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24上午11:00:22
 * @版本:   1.0
 */
public class KickParamBean extends ParamBean implements IwAnnotation{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -150974030598852134L;
	
	@NotEmpty
	private String guid;	 	 //大区ID,
	
	@NotEmpty
	private String username;	 //角色名称
	
	
	/**
	 * TODO: 无参构造函数
	 */
	public KickParamBean() {
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
	 * 帐号名称
	 * @return
	 */
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
