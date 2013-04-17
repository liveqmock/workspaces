/**      
 * OutOfDateFilter.java Create on 2012-4-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.filters;

import org.joda.time.DateTime;

import com.iwgame.xengine.xtask.cps.context.Context;
import com.iwgame.xengine.xtask.cps.model.CacheUser;
import com.iwgame.xengine.xtask.cps.model.ConsumeMessageData;
import com.iwgame.xengine.xtask.cps.pipe.IFilter;

/**
 * @ClassName: OutOfDateFilter
 * @Description: 过期过滤器
 *               <p>
 *               消费时间 - 用户的注册时间 > 设定的值，为过期。<br/>
 *               设定值为全局变量中的MaxDays
 *               </p>
 * @author 吴江晖
 * @date 2012-4-28 上午11:48:56
 * @Version 1.0
 * 
 */
public class OutOfDateFilter implements IFilter<ConsumeMessageData> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.cps.pipefilter.engine.IFilter#match(java.lang
	 * .Object)
	 */
	@Override
	public ConsumeDataMatchResult match(final ConsumeMessageData target) {
		CacheUser user = target.getCacheUser();
		DateTime targetDate = new DateTime(user.getCreateTime().getTime())
				.plusDays(Context.get().getMaxDays());
		return new ConsumeDataMatchResult(target, targetDate.isAfter(target
				.getCtime().getTime()), 4);
	}

}
