/**      
 * Pipe.java Create on 2012-4-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.pipe;

/**
 * @ClassName: Pipe
 * @Description: 对数据进行过滤处理的管道
 * @author 吴江晖
 * @date 2012-4-28 上午10:15:51
 * @Version 1.0
 * 
 */
public class Pipe<T> {

	// 流入管道的数据
	private T target;

	// 数据过滤器树
	private final IFilter<T> filterTree;

	/**
	 * 管道构造函数
	 * 
	 * @param filterTree
	 *            过滤器树
	 */
	public Pipe(final IFilter<T> filterTree) {
		this.filterTree = filterTree;
	}

	public IMatchResult match() {
		return filterTree.match(getTarget());
	}

	/**
	 * @return 流入过滤管道的数据对象
	 */
	public T getTarget() {
		return target;
	}

	/**
	 * 设置流入过滤管道的数据对象
	 * 
	 * @param target
	 *            数据对象
	 */
	public void setTarget(final T target) {
		this.target = target;
	}

	/**
	 * @return 数据过滤器树
	 */
	public IFilter<T> getFilterTree() {
		return filterTree;
	}

}
