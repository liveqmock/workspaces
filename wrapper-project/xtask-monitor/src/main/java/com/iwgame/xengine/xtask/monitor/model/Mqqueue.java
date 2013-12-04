package com.iwgame.xengine.xtask.monitor.model;

import java.util.List;

/**
 * 
 * 类说明
 * 
 * @简述： 启动类
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间： 2013-4-9 下午11:54:42
 */
public class Mqqueue {

	private String host;
	
	private String phones;
	
	private String virtualhost;
	
	private List<Queue> queues;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

	public String getVirtualhost() {
		return virtualhost;
	}

	public void setVirtualhost(String virtualhost) {
		this.virtualhost = virtualhost;
	}

	public List<Queue> getQueues() {
		return queues;
	}

	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}
}
