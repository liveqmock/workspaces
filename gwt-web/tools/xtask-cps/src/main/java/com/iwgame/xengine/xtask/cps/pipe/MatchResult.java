/**      
 * MatchResult.java Create on 2012-4-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.pipe;

import java.io.Serializable;

/**
 * @ClassName: MatchResult
 * @Description: 目标数据匹配结果对象
 * @author 吴江晖
 * @date 2012-4-28 上午11:17:27
 * @Version 1.0
 * 
 */
public class MatchResult<T> implements Serializable, IMatchResult {

	private static final long serialVersionUID = -2475077566105260121L;

	// 目标数据
	private T target;

	// 匹配结果
	private boolean match;

	/**
	 * 构造函数
	 * 
	 * @param target
	 *            目标数据
	 */
	public MatchResult(final T target) {
		super();
		this.target = target;
	}

	/**
	 * 构造函数
	 * 
	 * @param target
	 *            目标数据
	 * @param match
	 *            匹配结果
	 */
	public MatchResult(final T target, final boolean match) {
		super();
		this.target = target;
		this.match = match;
	}

	public T getTarget() {
		return target;
	}

	public void setTarget(final T target) {
		this.target = target;
	}

	@Override
	public boolean isMatch() {
		return match;
	}

	public void setMatch(final boolean match) {
		this.match = match;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Target is : " + target + "; match is " + match;
	}
}
