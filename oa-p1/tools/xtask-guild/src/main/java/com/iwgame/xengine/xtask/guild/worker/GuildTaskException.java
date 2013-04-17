/**      
 * GuildTaskException.java Create on 2012-7-1     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.worker;

/**
 * @ClassName: GuildTaskException
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-1 上午10:41:23
 * @Version 1.0
 * 
 */
public class GuildTaskException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8398716109483043913L;

	/**
	 * 
	 */
	public GuildTaskException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public GuildTaskException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public GuildTaskException(final String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public GuildTaskException(final Throwable cause) {
		super(cause);
	}

}
