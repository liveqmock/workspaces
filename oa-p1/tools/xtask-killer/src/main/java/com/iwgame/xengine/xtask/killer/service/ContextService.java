/**      
 * ContextService.java Create on 2012-7-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.killer.service;

import com.iwgame.xengine.xtask.killer.model.PolicyMeta;

/**
 * @ClassName: ContextService
 * @Description: 环境服务接口
 * @author 吴江晖
 * @date 2012-7-11 下午12:28:37
 * @Version 1.0
 * 
 */
public interface ContextService {

	/**
	 * 加载所有启用的封杀策略
	 */
	public void loadActivedKillPolicies();

	/**
	 * 启用封杀策略
	 */
	public void loadActivedKillPolicy(PolicyMeta meta);

	/**
	 * 延长封杀策略有效期
	 */
	public void prolongActivedKillPolicy(PolicyMeta meta);

	/**
	 * 停用封杀策略
	 */
	public void deactiveKillPolicy(PolicyMeta meta);

	/**
	 * 每天的策略封杀数量计数
	 */
	public void counter(String mac);

	/**
	 * 计数器清零
	 */
	public void cleanCounter();

	/**
	 * 计数器清零
	 */
	public void cleanCounter(String mac);

	/**
	 * 取得当天的封杀数
	 */
	public Integer getCounter(String mac);
}
