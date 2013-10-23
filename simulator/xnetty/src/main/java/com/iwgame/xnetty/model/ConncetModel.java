package com.iwgame.xnetty.model;

import java.io.Serializable;

public class ConncetModel implements Serializable   {

	/**
	 *
	 */
	private static final long serialVersionUID = 1697353536806710478L;

	private String uid;

	private final SystemInfoModel systemInfoModel = new SystemInfoModel();

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public SystemInfoModel getSystemInfo() {
		return systemInfoModel;
	}
}
