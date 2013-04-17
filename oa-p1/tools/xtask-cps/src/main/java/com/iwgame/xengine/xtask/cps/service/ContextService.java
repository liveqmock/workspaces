/**      
 * ContextService.java Create on 2012-5-16     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.xengine.xtask.cps.service;

import java.util.Date;

import com.iwgame.xengine.xtask.cps.context.CRL;
import com.iwgame.xengine.xtask.cps.context.CRL.Type;
import com.iwgame.xengine.xtask.cps.model.CacheUser;

/**
 * @ClassName: ContextService
 * @Description: 上下文环境服务接口
 * @author 吴江晖
 * @date 2012-5-16 上午08:52:28
 * @Version 1.0
 * 
 */
public interface ContextService {

	/**
	 * 加载黑名单
	 */
	public void initializeCRLs(CRL crl);

	/**
	 * 更新黑名单
	 * 
	 * @param type
	 *            黑名单类型
	 */
	public void updateCRL(Type type);

	/**
	 * 初始化缓存
	 */
	public void initializeCache();

	/**
	 * 向缓存中添加一个CPS用户
	 */
	public void addCacheUser(CacheUser user);

	/**
	 * 依据帐号ID获取缓存用户对象
	 * 
	 * @param accountId
	 *            帐号ID
	 */
	public CacheUser getCacheUser(long accountId);

	/**
	 * 初始化分成方案查询表
	 */
	public void initializeProfitSharingPolicyTable();

	/**
	 * 初始化全局变量
	 */
	public void initializeGlobalConfig();

	/**
	 * 初始化停用媒体列表
	 */
	public void initializeDisableMedias();

	/**
	 * 初始化停用链接列表
	 */
	public void initializeDisableLinks();

	/**
	 * 更新停用媒体列表
	 * 
	 * @param mediaId
	 *            媒体ID
	 * @param remove
	 *            是移除还是更新
	 */
	public void updateDisableMedias(Integer mediaId, boolean remove);

	/**
	 * 更新停用链接列表
	 * 
	 * @param linkId
	 *            链接ID
	 * @param remove
	 *            是移除还是更新
	 */
	public void updateDisableLinks(Integer linkId, boolean remove);

	/**
	 * 判断是否为CPS用户
	 * 
	 * @param accountId
	 *            用户帐号ID
	 */
	public boolean isCpsUser(long accountId);

	/**
	 * 更新缓存用户的登录时间
	 * 
	 * @param accountId
	 *            用户帐号ID
	 * @param loginTime
	 *            登录时间
	 */
	public void updateCacheUserLoginTime(Long accountId, Date loginTime);

	/**
	 * 更新分成方案
	 * 
	 * @param mediaId
	 *            媒体ID
	 */
	public void updateProfitSharingPolicy(int mediaId);

	public abstract void updateCacheUserBonus(final long accountId, final double bonus);

}
