/**      
 * CompositeFilter.java Create on 2012-4-27     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.pipe;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CompositeFilter
 * @Description: 抽象组合型过滤器
 * @author 吴江晖
 * @date 2012-4-27 下午06:40:34
 * @Version 1.0
 * 
 */
public abstract class CompositeFilter<T> implements IFilter<T> {

	// 过滤器类型
	public enum Type {
		AND, OR;
	}

	protected List<IFilter<T>> filters;

	public CompositeFilter() {
		filters = new ArrayList<IFilter<T>>();
	}

	public abstract Type getType();

	/**
	 * 添加过滤器链
	 * 
	 * @param filters
	 *            过滤器链
	 */
	public void addFilters(final List<IFilter<T>> filters) {
		this.filters.addAll(filters);
	}

	/**
	 * 添加一个过滤器
	 * 
	 * @param filter
	 *            过滤器
	 */
	public void addFilter(final IFilter<T> filter) {
		this.filters.add(filter);
	}

	/**
	 * 获取过滤器链
	 */
	public List<IFilter<T>> getFilters() {
		return filters;
	}

}
