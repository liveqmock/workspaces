/**      
 * Context.java Create on 2012-6-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.context;

import com.iwgame.xengine.xtask.guild.model.Race;

/**
 * @ClassName: Context
 * @Description: 公会赛后台计算上下文环境
 * @author 吴江晖
 * @date 2012-6-28 下午05:10:03
 * @Version 1.0
 * 
 */
public class Context {

	private boolean hasOpenRace = false;

	private Race race;

	private static Context instance;

	public static Context get() {
		if (instance == null) {
			instance = new Context();
		}
		return instance;
	}

	public boolean hasOpenRace() {
		return hasOpenRace;
	}

	public void setHasOpenRace(final boolean hasOpenRace) {
		this.hasOpenRace = hasOpenRace;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(final Race race) {
		this.race = race;
	}

}
