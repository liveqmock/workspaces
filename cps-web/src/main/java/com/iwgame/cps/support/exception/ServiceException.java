/****************************************************************
 * 文件名 : ServiceException.java
 * 日期 : 2013-7-1
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.support.exception;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-7-1 下午12:07:24
 * @版本: v1.0.0
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2284161618469610982L;

	public ServiceException() {
	}

	public ServiceException(Throwable e) {
		super(e);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(String message, Throwable e) {
		super(message, e);
	}
}
