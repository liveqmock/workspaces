/**      
 * IFilter.java Create on 2012-4-27     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.pipe;

/**
 * @ClassName: IFilter
 * @Description: 过滤器接口
 * @author 吴江晖
 * @date 2012-4-27 下午06:37:47
 * @Version 1.0
 * 
 */
public interface IFilter<T> {

	/**
	 * 对目标对象进行匹配操作
	 * 
	 * @param target
	 *            目标对象
	 * @return 匹配结果
	 */
	public IMatchResult match(T target);
}
