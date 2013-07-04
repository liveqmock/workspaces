/****************************************************************
 * 文件名 : d.java
 * 日期 : 2013-6-29
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.cache;

import javax.annotation.Resource;

import net.sf.ehcache.Cache;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-6-29 下午3:57:19
 * @版本: v1.0.0
 */
@Component
public class SessionCacheFactory implements InitializingBean {

	@Resource
	private Cache userCache;

	@Resource
	private Cache sessionCache;

	@Override
	public void afterPropertiesSet() throws Exception {
		com.iwgame.cps.security.SecurityManager.SecurityContext.setSessionCache(this.sessionCache);
		com.iwgame.cps.security.SecurityManager.SecurityContext.setUserCache(this.userCache);
	}
}
