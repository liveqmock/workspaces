package com.iwgame.xengine.xtask.monitor.model;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 类说明
 * 
 * @简述： 启动类
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间： 2013-4-9 下午11:54:34
 */
public class Monitor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2301234594035596382L;

	private String username;
	
	private String password;
	
	private List<Mqtotal> mqtotals;
	
	private List<Mqqueue> mqqueues;
	
	private List<Mport> mports;
	
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

	public List<Mqtotal> getMqtotals() {
		return mqtotals;
	}

	public void setMqtotals(List<Mqtotal> mqtotals) {
		this.mqtotals = mqtotals;
	}

	public List<Mqqueue> getMqqueues() {
		return mqqueues;
	}

	public void setMqqueues(List<Mqqueue> mqqueues) {
		this.mqqueues = mqqueues;
	}

   
   public List<Mport> getMports() {
      return mports;
   }

   
   public void setMports(List<Mport> mports) {
      this.mports = mports;
   }
	
}
