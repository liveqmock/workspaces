package com.iwgame.xengine.xtask.sms.util;

import java.io.IOException;

/**
 * 类说明
 * 
 * @简述： 邮件异常类
 * @作者： 吴君杰
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @时间：2012-10-8 下午04:41:09
 */
public class MailException extends IOException {

	private static final long serialVersionUID = 1;

	public MailException() {

	}

	public MailException(String message) {
		super(message);
	}
}
