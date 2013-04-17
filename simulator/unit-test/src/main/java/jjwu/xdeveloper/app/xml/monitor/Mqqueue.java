package jjwu.xdeveloper.app.xml.monitor;

import java.util.List;

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
