/****************************************************************
 * 文件名 : dd.java
 * 日期 : 2013-6-29
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.cache;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-6-29 下午3:53:44
 * @版本: v1.0.0
 */

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodCacheInterceptor {

	protected Logger logger = Logger.getLogger(MethodCacheInterceptor.class);

	private Cache cache;

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	@Pointcut("execution(* com.iwgame.cps.service.CommonService.*(..))")
	public void cacheMethod() {
	}

	@Around("com.iwgame.cps.cache.MethodCacheInterceptor.cacheMethod()")
	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		String targetName = pjp.getThis().getClass().getName();
		if (targetName.indexOf("$$EnhancerByCGLIB$$") > -1) {
			targetName = targetName.substring(0, targetName.indexOf("$$EnhancerByCGLIB$$"));
		}
		String methodName = pjp.getSignature().getName();
		Object[] arguments = pjp.getArgs();
		String cacheKey = getCacheKey(targetName, methodName, arguments);
		Element element = null;
		synchronized (this) {
			element = this.cache.get(cacheKey);
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Cache: Key[" + cacheKey + "],GetObject : [" + element + "]");
			}
			if (element == null) {
				Object result = pjp.proceed();
				element = new Element(cacheKey, (Serializable) result);
				this.cache.put(element);
				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Cache: Key[" + cacheKey + "],Success Cached :[" + element + "]");
				}
			}
		}
		return element.getObjectValue();
	}

	private String getCacheKey(String targetName, String methodName, Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (Object argument : arguments) {
				sb.append(".").append(argument);
			}
		}
		return sb.toString();
	}
}
