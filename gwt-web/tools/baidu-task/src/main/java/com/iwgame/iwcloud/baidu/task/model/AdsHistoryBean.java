package com.iwgame.iwcloud.baidu.task.model;

import java.io.Serializable;

/**
 * 
 * 类说明
 * 
 * @compend： AdsHistoryBean
 * @author： jjwu
 * @version： 1.0
 * @email： wujunjie@iwgame.com
 * @time：2012-7-2 下午02:04:51
 */
public class AdsHistoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4622493355287711121L;

	private String productid;

	private String username;

	private String begintime;

	private String endtime;

	private int savecount;

	private int fetchecount;

	private int status;

	private String info;

	// //////////////////////get or set////////////////////////

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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getSavecount() {
		return savecount;
	}

	public void setSavecount(int savecount) {
		this.savecount = savecount;
	}

	public int getFetchecount() {
		return fetchecount;
	}

	public void setFetchecount(int fetchecount) {
		this.fetchecount = fetchecount;
	}

}
