/**      
 * ConsumeMatchResult.java Create on 2012-4-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.filters;

import com.iwgame.xengine.xtask.cps.model.ConsumeMessageData;
import com.iwgame.xengine.xtask.cps.pipe.MatchResult;

/**
 * @ClassName: ConsumeMatchResult
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-4-28 上午11:53:17
 * @Version 1.0
 * 
 */
public class ConsumeDataMatchResult extends MatchResult<ConsumeMessageData> {

	private static final long serialVersionUID = -8091048278171721595L;
	private int statusCode;

	/**
	 * @param target
	 * @param match
	 */
	public ConsumeDataMatchResult(final ConsumeMessageData target, final boolean match,
			final int statusCode) {
		super(target, match);
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(final int statusCode) {
		this.statusCode = statusCode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.cps.pipefilter.engine.MatchResult#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "; statusCode is " + statusCode;
	}
}
