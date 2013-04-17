package com.iwgame.xcloud.searcher.model;

import net.sf.json.JSONObject;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * @类名: AccountParamBean
 * @描述: 玩家帐号封杀,解封,帐号Bean
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2012-10-24上午11:00:46
 * @版本: 1.0
 */
public class AccountParamBean extends ParamBean implements IwAnnotation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5961841981055917715L;

	@NotEmpty
	private String guid;

	@NotEmpty
	private Integer sealtime;

	@NotEmpty
	private String username;

	private int showtime;
	
	private int online;
	
	private int optype;
	
	private String note;
	
	private String batchid;

	/**
	 * TODO: 无参构造函数
	 */
	public AccountParamBean() {
		
	}

	
	/**
	 * @说明: 大区标示
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
	 * @说明: 封停时间,单位分钟
	 * @return: Integer
	 */
	public Integer getSealtime() {
		return sealtime;
	}

	public void setSealtime(Integer sealtime) {
		this.sealtime = sealtime;
	}

	
	
	/**
	 * 
	 * @说明: 玩家帐号
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
	 * @说明: 封禁原因，说明
	 * @return: String
	 */
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	/**
	 * 
	 * @说明: 游戏客户端是否显示封停时间
	 * @return: int
	 */
	public int getShowtime() {
		return showtime;
	}


	public void setShowtime(int showtime) {
		this.showtime = showtime;
	}
	
	
	/**
	 * 
	 * @说明: 是否执行踢下线操作
	 * @return: int
	 */
	public int getOnline() {
		return online;
	}


	public void setOnline(int online) {
		this.online = online;
	}
	
	
	/**
	 * 
	 * @说明: 批次编号
	 * @return: long
	 */
	public String getBatchid() {
		return batchid;
	}


	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
	
	


	/**
	 * 
	 * @说明: 1封杀 2冻结
	 * @return: int
	 */
	public int getOptype() {
		return optype;
	}


	public void setOptype(int optype) {
		this.optype = optype;
	}


	@Override
	public String toString() {
		return JSONObject.fromObject(this).toString();
	}

}
