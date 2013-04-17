package com.iwgame.xcloud.searcher.model;


import net.sf.json.JSONObject;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * @类名:   SecurityCardParamBean 
 * @描述:  	TODO(...) 
 * @作者:   吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2012-10-24上午10:59:49
 * @版本:   1.0
 */
public class SecurityCardParamBean extends ParamBean implements IwAnnotation{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7428515039352956847L;
	@NotEmpty
	private String guid;
	@NotEmpty
	private int type;
	@NotEmpty
	private String name;
	@NotEmpty
	private String str3;
	@NotEmpty
	private String operate;
	
	/**
	 * 
	 * @说明: 大区ID
	 * @return: String
	 */
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	
	/**
	 * 
	 * @说明: 
	 * @return: int
	 */
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * 
	 * @说明: 帐号
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
	 * @说明: TODO
	 * @return: String
	 */
	public String getStr3() {
		return str3;
	}
	public void setStr3(String str3) {
		this.str3 = str3;
	}
	
	/**
	 * 
	 * @说明: 解绑or绑定类型
	 * @return: String
	 */
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
