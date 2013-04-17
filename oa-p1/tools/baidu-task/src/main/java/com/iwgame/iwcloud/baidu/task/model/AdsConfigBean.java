package com.iwgame.iwcloud.baidu.task.model;

import java.io.Serializable;

/**
 * 类说明
 * 
 * @compend： AdsConfigBean
 * @author： jjwu
 * @version： 1.0
 * @email： wujunjie@iwgame.com
 * @time：2012-7-3 上午11:41:07
 */
public class AdsConfigBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1773010341036468079L;

	private String productid;
	
	private String username;
	
	private String password;
	
	private String token;
	
	private int vingve;
	
	public AdsConfigBean(){
		
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public int getVingve() {
		return vingve;
	}

	public void setVingve(int vingve) {
		this.vingve = vingve;
	}
	
}
