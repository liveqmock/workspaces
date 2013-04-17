/**      
 * OutOfDateFilter.java Create on 2012-4-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.filters;

import org.apache.log4j.Logger;

import com.iwgame.xengine.xtask.cps.model.CacheUser;
import com.iwgame.xengine.xtask.cps.model.ConsumeMessageData;
import com.iwgame.xengine.xtask.cps.pipe.IFilter;

/**
 * @ClassName: OverLevelQuotaFilter
 * @Description: 消费区间限额过滤器
 *               <p>
 *               根据用户的等级在分成方案中查找相对应的分成比例以及分成限额， 消费金额×分成比例>分成限额 为超区间限额
 *               </p>
 * @author 吴江晖
 * @date 2012-4-28 上午11:48:56
 * @Version 1.0
 * 
 */
public class OverLevelQuotaFilter implements IFilter<ConsumeMessageData> {

	private static final Logger logger = Logger
			.getLogger(OverLevelQuotaFilter.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.cps.pipefilter.engine.IFilter#match(java.lang
	 * .Object)
	 */
	@Override
	public ConsumeDataMatchResult match(final ConsumeMessageData target) {
		logger.info("分成区间限额判断");
		CacheUser user = target.getCacheUser();
		logger.info("用户" + user.getAccountId() + "等级=" + target.getLevel());
		logger.info("用户" + user.getAccountId() + "理论分成="
				+ user.getBonusTheory());
		logger.info("用户" + user.getAccountId() + "以获得的区间分成总额="
				+ user.getLevelTotalBonus());
		logger.info("用户" + user.getAccountId() + "区间限额=" + user.getLevelQuota());
		return new ConsumeDataMatchResult(target, user.getBonusTheory()
				+ user.getLevelTotalBonus() <= user.getLevelQuota(), 5);
	}

}
