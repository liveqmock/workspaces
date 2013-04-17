/****************************************************************
 *  系统名称  ： '广告系统任务处理'
 *  文件名    ： HttpRequestException.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.task.exception;

/**
 * 类说明
 * 
 * @简述： HTTP请求异常 
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-3-28 上午11:20:48
 */
public class HttpRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	public HttpRequestException() {
	}

	/**
	 * @param message
	 */
	public HttpRequestException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HttpRequestException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HttpRequestException(String message, Throwable cause) {
		super(message, cause);
	}
}
