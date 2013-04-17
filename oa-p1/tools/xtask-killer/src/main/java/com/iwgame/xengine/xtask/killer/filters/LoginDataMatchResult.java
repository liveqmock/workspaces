/**      
 * LoginDataMatchResult.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.filters;

import com.iwgame.xengine.xtask.killer.model.LoginMessageData;
import com.iwgame.xengine.xtask.killer.pipe.MatchResult;

/**
 * @ClassName: LoginDataMatchResult
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-11 下午03:00:23
 * @Version 1.0
 * 
 */
public class LoginDataMatchResult extends MatchResult<LoginMessageData> {

	private boolean ignore = false;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3902320550403483025L;

	public LoginDataMatchResult(final LoginMessageData target, final boolean match) {
		super(target, match);
	}

	/**
	 * @param target
	 * @param match
	 */
	public LoginDataMatchResult(final LoginMessageData target, final boolean match, final boolean ignore) {
		super(target, match);
		this.ignore = ignore;
	}

	public boolean isIgnore() {
		return ignore;
	}

}
