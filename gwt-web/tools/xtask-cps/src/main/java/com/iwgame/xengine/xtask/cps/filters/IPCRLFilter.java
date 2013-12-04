/**      
 * UserCRLFilter.java Create on 2012-4-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.filters;

import com.iwgame.xengine.xtask.cps.context.Context;
import com.iwgame.xengine.xtask.cps.model.ConsumeMessageData;
import com.iwgame.xengine.xtask.cps.pipe.IFilter;

/**
 * @ClassName: IPCRLFilter
 * @Description: IP黑名单过滤器
 * @author 吴江晖
 * @date 2012-4-28 上午11:38:48
 * @Version 1.0
 * 
 */
public class IPCRLFilter implements IFilter<ConsumeMessageData> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.xengine.xtask.cps.pipefilter.engine.IFilter#match(java.lang
	 * .Object)
	 */
	@Override
	public ConsumeDataMatchResult match(final ConsumeMessageData target) {
		return new ConsumeDataMatchResult(target, !Context.get().getCRL()
				.blockIP(target.getIp()), 8);
	}

}
