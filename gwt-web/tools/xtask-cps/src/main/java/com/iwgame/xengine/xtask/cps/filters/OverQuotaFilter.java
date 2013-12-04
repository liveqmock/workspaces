/**      
 * OutOfDateFilter.java Create on 2012-4-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.filters;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.cps.context.Context;
import com.iwgame.xengine.xtask.cps.model.CacheUser;
import com.iwgame.xengine.xtask.cps.model.ConsumeMessageData;
import com.iwgame.xengine.xtask.cps.pipe.IFilter;

/**
 * @ClassName: OverQuotaFilter
 * @Description: 用户限额过滤器
 *               <p>
 *               消费金额×分成比例+用户已获得的分成金额 > 设定的值，为超过限额。<br/>
 *               设定值为全局变量中的MaxQuota
 *               </p>
 * @author 吴江晖
 * @date 2012-4-28 上午11:48:56
 * @Version 1.0
 * 
 */
public class OverQuotaFilter implements IFilter<ConsumeMessageData> {

	private static final Logger logger = Logger
			.getLogger(OverQuotaFilter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.cps.pipefilter.engine.IFilter#match(java.lang
	 * .Object)
	 */
	@Override
	public ConsumeDataMatchResult match(final ConsumeMessageData target) {
		logger.info("分成总额限额判断");
		CacheUser user = target.getCacheUser();
		logger.info("用户" + user.getAccountId() + "理论分成="
				+ user.getBonusTheory());
		logger.info("用户" + user.getAccountId() + "已获分成=" + user.getBonus());
		logger.info("用户" + user.getAccountId() + "最大分成="
				+ Context.get().getMaxBonus());
		return new ConsumeDataMatchResult(target, user.getBonusTheory()
				+ user.getBonus() <= Context.get().getMaxBonus(), 3);

	}

}
