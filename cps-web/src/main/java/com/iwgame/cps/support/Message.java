package com.iwgame.cps.support;

import java.util.HashMap;

/**
 * 
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-7-1 上午11:43:17
 * @版本:   	v1.0.0
 */
public class Message implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6459626062489429018L;

	private String code;

	private String text;

	private HashMap<String, Object> obj;

	public Message() {

	}

	public Message(String text) {
		this.text = text;
	}

	public Message(String code, String text) {
		this.code = code;
		this.text = text;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public HashMap<String, Object> getObj() {
		return obj;
	}

	public void setObj(HashMap<String, Object> obj) {
		this.obj = obj;
	}

}
