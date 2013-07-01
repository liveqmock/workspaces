/****************************************************************
 * 文件名 : UserSessionListener.java
 * 日期 : 2013-6-29
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.security;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.iwgame.cps.security.SecurityManager.SecurityContext;
import com.iwgame.cps.util.CryptUtils;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-6-29 上午10:04:29
 * @版本: v1.0.0
 */
public class UserSessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	protected Logger logger = Logger.getLogger(UserSessionListener.class);

	public UserSessionListener() {
		if (this.logger.isInfoEnabled()) {
			this.logger.info("Startup Application Listener");
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		SecurityManager.SecurityContext.putSessionToCache(CryptUtils.makMd5Digest(se.getSession().getId()), se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		SecurityManager.SecurityContext.removeSessionFromCache(CryptUtils.makMd5Digest(((HttpSession) se.getSource()).getId()));
	}


	@Override
	public void attributeAdded(HttpSessionBindingEvent hsbe) {
		String context_key = hsbe.getName();
		if (context_key.equals(SecurityContext.SECURITY_CONTEXT_KEY)) {
			User user = ((SecurityManager.SecurityContext) hsbe.getValue()).getUser();
			SecurityManager.SecurityContext.putUserToCache(user);
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("user [" + user.getLoginname() + "] logon , exists same size :[" + SecurityManager.SecurityContext.getUserListSize() + "] , total size :"
						+ SecurityManager.SecurityContext.getUserListSize());
			}
		}
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent hsbe) {
		String context_key = hsbe.getName();
		if (context_key.equals(SecurityContext.SECURITY_CONTEXT_KEY)) {
			User user = ((SecurityManager.SecurityContext) hsbe.getValue()).getUser();
			SecurityManager.SecurityContext.removeUserFromCache(user);
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("user [" + user.getLoginname() + "] logout , exists same size :[" + SecurityManager.SecurityContext.getUserListSize()
						+ "] , total size :" + SecurityManager.SecurityContext.getUserListSize());
			}
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {

	}
}
