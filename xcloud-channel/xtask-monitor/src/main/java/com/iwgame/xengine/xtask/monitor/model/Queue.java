package com.iwgame.xengine.xtask.monitor.model;

import java.io.Serializable;

/**
 * 
 * 类说明
 * @简述： XXX
 * @作者： 吴君杰
 * @版本： 1.0.0
 * @时间： 2013-4-9 下午11:55:49
 * @邮箱： wujunjie@iwgame.com
 */
public class Queue implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6333035447803942155L;

	private String name;
	
	private int maxwarn;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxwarn() {
		return maxwarn;
	}

	public void setMaxwarn(int maxwarn) {
		this.maxwarn = maxwarn;
	}
	
}
