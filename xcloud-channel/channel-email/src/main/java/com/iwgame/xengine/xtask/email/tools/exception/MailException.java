package com.iwgame.xengine.xtask.email.tools.exception;

/**
 * 类说明
 * 
 * @简述： 邮件异常类
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间： 2012-12-18 下午04:41:09
 */
public class MailException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6872719667375667133L;

	public MailException() {

	}

	public MailException(String message) {
		super(message);
	}
}
