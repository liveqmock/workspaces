package com.iwgame.xengine.xtask.game.model;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.iwgame.xvalidators.annotation.IwAnnotation;
import com.iwgame.xvalidators.annotation.NotEmpty;

/**
 * 
 * 类说明
 * 
 * @简述： 帐号Bean
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-5-3 下午06:17:06
 */
public class AccountBean implements IwAnnotation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1711460836975608382L;

	private final Logger logger = Logger.getLogger(AccountBean.class);

	@NotEmpty
	private String guid; // 大区标示

	@NotEmpty
	private int sealtime; // 封停时间,单位分钟

	@NotEmpty
	private String username; // 帐号名

	private String note; // 封禁原因，说明

	private Integer showtime = 0; // 是否显示封停时间,0不显示,1显示

	private Integer online = 0; // 是否踢下线,0不执行踢人下线,1

	private String batchid; // 批次号

	private int optype; // 操作类型

	private String appname; // 来源应用名称

	private String source;

	public AccountBean() {

	}

	public AccountBean(String source) {

		try {
			JSONObject jsonObject = JSONObject.fromObject(source);
			this.guid = jsonObject.getString("guid");
			this.username = jsonObject.getString("username");
			this.sealtime = jsonObject.get("sealtime") == null ? 0 : jsonObject.getInt("sealtime");
			this.appname = jsonObject.get("appname") == null ? "unknown" : jsonObject.getString("appname");
			this.showtime = jsonObject.get("showtime") == null ? 0 : jsonObject.getInt("showtime");
			this.online = jsonObject.get("online") == null ? 0 : jsonObject.getInt("online");
			this.note = jsonObject.get("note") == null ? "" : jsonObject.getString("note");
			this.optype = jsonObject.get("optype") == null ? 0 : jsonObject.getInt("optype");
			this.batchid = jsonObject.get("batchid") == null ? "" : jsonObject.getString("batchid");
			this.source = source;
		} catch (Exception e) {
			logger.error("[忽略]帐号封杀,解封参数异常!", e);
		}
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public int getSealtime() {
		return sealtime;
	}

	public void setSealtime(int sealtime) {
		this.sealtime = sealtime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

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

	public Integer getShowtime() {
		return showtime;
	}

	public void setShowtime(Integer showtime) {
		this.showtime = showtime;
	}

	public Integer getOnline() {
		return online;
	}

	public void setOnline(Integer online) {
		this.online = online;
	}

	public String getBatchid() {
		return batchid;
	}

	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	public int getOptype() {
		return optype;
	}

	public void setOptype(int optype) {
		this.optype = optype;
	}

}
