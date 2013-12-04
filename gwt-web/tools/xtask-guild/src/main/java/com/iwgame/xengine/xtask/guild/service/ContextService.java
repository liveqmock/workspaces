/**      
 * ContextService.java Create on 2012-6-28     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.guild.service;

import com.iwgame.xengine.xtask.guild.model.CachedJoiner;
import com.iwgame.xengine.xtask.guild.model.CachedServer;

/**
 * @ClassName: ContextService
 * @Description: 上下文环境服务接口
 * @author 吴江晖
 * @date 2012-6-28 下午05:13:27
 * @Version 1.0
 * 
 */
public interface ContextService {

	/**
	 * 加载当前的开启的公会赛
	 */
	public void loadCurrentRace();

	/**
	 * 初始化缓存，将当前开启公会赛的参赛者加载到缓存中
	 */
	public void initializeCache();

	/**
	 * 清空缓存
	 */
	public void cleanCache();

	/**
	 * 获取缓存在Redis中的当前开启公会赛的参赛者<br/>
	 * 如果在当前开启的公会赛中找到对应的信息，将返回参赛者，否则返回null；<br/>
	 * 如果当前没有开启的公会赛，直接返回null
	 * 
	 * @param name
	 *            用户名
	 */
	public CachedJoiner getCachedJoiner(String name, boolean forbit);

	public CachedServer getCachedServer(String zone);

	/**
	 * 更新缓存中的参赛者信息
	 * 
	 * @param joiner
	 */
	public void updateCachedJoiner(CachedJoiner joiner);

	public void addRaceGuild(int guildId);

	public void updateRank(int guildId, int score);
}
