/****************************************************************
 *  文件名     ： ItemCardParamBean.java
 *  日期         :  2012-9-7
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
 * @类名: ItemCardParamBean
 * @描述: 道具卡激活,javaBean
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-24上午11:00:30
 * @版本: 1.0
 */
public class ItemCardParamBean extends ParamBean implements IwAnnotation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9045385399963765942L;

	@NotEmpty
	private String guid;  
	@NotEmpty
	private String username;  
	@NotEmpty
	private String cardnum;  
	@NotEmpty
	private String cardpwd;  

	private String validtime;  

	private Integer itype;  
	
	
	/**
	 * TODO: 无参构造函数
	 */
	public ItemCardParamBean() {
		
	}
	
	
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
	 * @说明: 账号名
	 * @return: String
	 */
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	/**
	 * 
	 * @说明: 卡号
	 * @return: String
	 */
	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	
	/**
	 * 
	 * @说明: 卡密码（MD5编码，全部小写）
	 * @return: String
	 */
	public String getCardpwd() {
		return cardpwd;
	}

	public void setCardpwd(String cardpwd) {
		this.cardpwd = cardpwd;
	}

	/**
	 * 
	 * @说明: 道具卡有效期，空字符串表示永不过期 过期"str3":"expired=过期时间戳"
	 * @return: String
	 */
	public String getValidtime() {
		return validtime;
	}

	public void setValidtime(String validtime) {
		this.validtime = validtime;
	}

	/**
	 * 
	 * @说明: 类型：0 普通卡号，1 运营商赠送卡号
	 * @return: Integer
	 */
	public Integer getItype() {
		return itype;
	}

	public void setItype(Integer itype) {
		this.itype = itype;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}
}
