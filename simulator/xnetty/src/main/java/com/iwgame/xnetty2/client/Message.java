package com.iwgame.xnetty2.client;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;

	private String value;

	private String clientName;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {

		this.clientName = clientName;

	}

	public Message(byte[] valueBytes) {
		try {
			value = new String(valueBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}