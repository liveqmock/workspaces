package com.iwgame.ioms.agent.model;

import java.io.Serializable;

public class Command implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -3203889616543811498L;

	// 表示这个机器的MAC地址作为唯一表示
	private String oid;
	// 命令ID 唯一表示
	private String commandid;
	// 命令中文描述
	private String name;
	// 参数
	private String params;

	public String getCommandid() {
		return commandid;
	}

	public void setCommandid(String id) {
		this.commandid = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
}
