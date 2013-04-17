/**      
 * BaiduAccount.java Create on 2012-10-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.modules.baidu.account.shared.model;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @ClassName: BaiduAccount
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-10-11 下午04:13:26
 * @Version 1.0
 * 
 */
public class BaiduAccount implements IsSerializable {

	private int id;

	private String productId;

	private String username;

	private String password;

	private String token;

	private int vingve;

	private Date modifyTime;

	private String modifier;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(final String productId) {
		this.productId = productId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public int getVingve() {
		return vingve;
	}

	public void setVingve(final int vingve) {
		this.vingve = vingve;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(final Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(final String modifier) {
		this.modifier = modifier;
	}

}
