/**      
 * LevelFilter.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.filters;

import com.iwgame.xengine.xtask.killer.model.LoginMessageData;
import com.iwgame.xengine.xtask.killer.pipe.IFilter;
import com.iwgame.xengine.xtask.killer.pipe.IMatchResult;

/**
 * @ClassName: LevelFilter
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-7-11 下午03:17:48
 * @Version 1.0
 * 
 */
public class LevelFilter implements IFilter<LoginMessageData> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.xengine.xtask.killer.pipe.IFilter#match(java.lang.Object)
	 */
	@Override
	public IMatchResult match(final LoginMessageData target) {
		int level = target.getPolicy().getRule().getLevel();
		if (level == -1) {
			return new LoginDataMatchResult(target, true, true);
		} else {
			int maxLevel = target.getMaxLevel();
			boolean result = false;
			String opt = target.getPolicy().getRule().getLevelOpt();
			if ("gt".equals(opt)) {
				result = maxLevel >= level;
			} else {
				result = maxLevel < level;
			}
			return new LoginDataMatchResult(target, result);
		}
	}

}
