/**      
 * FlumeAdminException.java Create on 2012-4-13     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.datas.shared.service;

/**
 * @ClassName: FlumeAdminException
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-4-13 下午03:32:22
 * @Version 1.0
 * 
 */
public class FlumeAdminException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4615530306524344564L;

	/**
	 * 
	 */
	public FlumeAdminException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FlumeAdminException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public FlumeAdminException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public FlumeAdminException(final Throwable cause) {
		super(cause);
	}

}
