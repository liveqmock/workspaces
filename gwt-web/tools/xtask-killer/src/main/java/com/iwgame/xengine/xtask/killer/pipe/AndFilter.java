/**      
 * AndFilter.java Create on 2012-4-27     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.pipe;

/**
 * @ClassName: AndFilter
 * @Description: AND过滤器<br/>
 *               只要过滤器链中有一个过滤器不匹配目标数据，即停止匹配
 * @author 吴江晖
 * @date 2012-4-27 下午06:45:13
 * @Version 1.0
 * 
 */
public class AndFilter<T> extends CompositeFilter<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.killer.pipefilter.CompositeFilter#match(java.lang
	 * .Object)
	 */
	@Override
	public IMatchResult match(final T target) {
		IMatchResult result = null;
		for (IFilter<T> filter : getFilters()) {
			result = filter.match(target);
			if (!result.isMatch()) {
				// 有一个过滤器不匹配即跳出过滤器链不再匹配
				break;
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.xengine.xtask.killer.pipe.CompositeFilter#getType()
	 */
	@Override
	public com.iwgame.xengine.xtask.killer.pipe.CompositeFilter.Type getType() {
		return Type.AND;
	}

}
