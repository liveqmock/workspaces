package com.iwgame.cps.security;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-6-29 下午2:37:05
 * @版本:   	v1.0.0
 */
public abstract interface SecurityManager {

	public static class SecurityContext implements Serializable {

		private static final long serialVersionUID = -2436882504187413680L;

		public static final String SECURITY_CONTEXT_KEY = "com.iwgame.cps.security.securityContext";

		private static final ThreadLocal<SecurityContext> threadLocal = new ThreadLocal<SecurityContext>();

		private static Cache sessionCache = null;

		private static Cache removedSessionCache = null;

		private static Cache userCache = null;
		private User user;

		public static void setSessionCache(Cache sessionCache) {
			SecurityContext.sessionCache = sessionCache;
		}

		public static void setRemovedSessionCache(Cache removedSessionCache) {
			SecurityContext.removedSessionCache = removedSessionCache;
		}

		public static void setUserCache(Cache userCache) {
			SecurityContext.userCache = userCache;
		}

		public static void putSessionToCache(String sessionId, HttpSession session) {
			SecurityContext.sessionCache.put(new Element(sessionId, session));
		}

		public static void removeSessionFromCache(String sessionId) {
			SecurityContext.removedSessionCache.put(new Element(sessionId, sessionId));
			SecurityContext.sessionCache.remove(sessionId);
		}

		public static HttpSession getSessionFromCache(String sessionId) {
			Element emt = SecurityContext.sessionCache.get(sessionId);
			if (emt != null) {
				return (HttpSession) emt.getObjectValue();
			}
			return null;
		}

		public static boolean getRemovedSessionFromCache(String sessionId) {
			Element emt = SecurityContext.removedSessionCache.get(sessionId);
			if (emt != null) {
				return true;
			}
			return false;
		}

		public static void putUserToCache(User user) {
			SecurityContext.userCache.put(new Element(user.getCacheKey(), user));
		}

		public static void removeUserFromCache(User user) {
			SecurityContext.userCache.remove(user.getCacheKey());
		}


		public static int getUserListSize() {
			return SecurityContext.userCache.getSize();
		}

		public static void setContext(SecurityContext sc) {
			SecurityContext.threadLocal.set(sc);
		}

		public static SecurityContext getContext() {
			return SecurityContext.threadLocal.get();
		}

		public User getUser() {
			return this.user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public void bindSession(HttpSession session) {
			if (session != null) {
				session.setAttribute(SecurityContext.SECURITY_CONTEXT_KEY, this);
			}
		}
	}

	public static class Session {
		public static User getCurrentUser() {
			try {
				SecurityManager.SecurityContext sc = SecurityManager.SecurityContext.getContext();
				if (sc != null) {
					return sc.getUser();
				}
				return null;
			} catch (Exception e) {
			}
			return null;
		}
	}
}
