package jjwu.xdeveloper.app.model;

import java.io.Serializable;

public class Mqtotal implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7778851996133736257L;

	private String host;
	
	private int maxwarn;

	private String phones;
	
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

	public int getMaxwarn() {
		return maxwarn;
	}

	public void setMaxwarn(int maxwarn) {
		this.maxwarn = maxwarn;
	}
}
