/**      
 * FrontService.java Create on 2012-6-26     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.service;

import com.iwgame.xengine.xtask.guild.model.RegistGuild;

/**
 * @ClassName: FrontService
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-6-26 上午09:29:19
 * @Version 1.0
 * 
 */
public interface FrontService {

	public void registGuild(RegistGuild registGuild);

	public boolean canJoinRace(String gameId, int guildId);

	public void joinRace(String gameId, int guildId, String name, long regTime);
}
