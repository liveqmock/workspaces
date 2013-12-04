package com.iwgame.xcloud.searcher.model;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * @ClassName:    SearchResult 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-7-27上午11:05:18
 * @Version:      1.0
 */
public class ActWinner implements Serializable {
	
	private static final long serialVersionUID = 5428982577659073396L;
	
	private int idx;
	private int actid;
	private int accountid;
	private String user_name;
	private String role_name;
	private int type;
	private int gameid;
	private int zone;
	private int value;
	private int flag;
	private Date addtime;
	
	/**
	 * @param actid
	 * @param accountid
	 * @param user_name
	 * @param role_name
	 * @param type
	 * @param gameid
	 * @param zone
	 * @param value
	 * @param flag
	 * @param addtime
	 */
	public ActWinner(int idx,int actid, int accountid, String user_name, String role_name, int type, int gameid, int zone, int value, int flag, Date addtime) {
		this.idx = idx;
		this.actid = actid;
		this.accountid = accountid;
		this.user_name = user_name;
		this.role_name = role_name;
		this.type = type;
		this.gameid = gameid;
		this.zone = zone;
		this.value = value;
		this.flag = flag;
		this.addtime = addtime;
	}
	/**
	 * 
	 */
	public ActWinner() {
		
	}
	
	/**
	 * @return the idx
	 */
	public int getIdx() {
		return idx;
	}
	/**
	 * @param idx the idx to set
	 */
	public void setIdx(int idx) {
		this.idx = idx;
	}
	/**
	 * @return the actid
	 */
	public int getActid() {
		return actid;
	}
	/**
	 * @param actid the actid to set
	 */
	public void setActid(int actid) {
		this.actid = actid;
	}
	/**
	 * @return the accountid
	 */
	public int getAccountid() {
		return accountid;
	}
	/**
	 * @param accountid the accountid to set
	 */
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the role_name
	 */
	public String getRole_name() {
		return role_name;
	}
	/**
	 * @param role_name the role_name to set
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the gameid
	 */
	public int getGameid() {
		return gameid;
	}
	/**
	 * @param gameid the gameid to set
	 */
	public void setGameid(int gameid) {
		this.gameid = gameid;
	}
	/**
	 * @return the zone
	 */
	public int getZone() {
		return zone;
	}
	/**
	 * @param zone the zone to set
	 */
	public void setZone(int zone) {
		this.zone = zone;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * @return the flag
	 */
	public int getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}
	/**
	 * @return the addtime
	 */
	public Date getAddtime() {
		return addtime;
	}
	/**
	 * @param addtime the addtime to set
	 */
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
}
