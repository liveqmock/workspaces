/****************************************************************
 * 文件名 : AccessSecurityFilter.java
 * 日期 : 2013-6-29
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.iwgame.cps.security.SecurityManager.SecurityContext;
import com.iwgame.cps.util.CryptUtils;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-6-29 上午9:48:48
 * @版本: v1.0.0
 */
public class AccessSecurityFilter implements Filter {

	private FilterConfig filterConfig;

	private static String[] includeSuffixs = null;
	private static final String[] DEFAULT_INCLUDE_SUFFIXS = { ".do", ".dwr" };

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

		String includeSuffixStr = this.filterConfig.getInitParameter("suffixs");
		if (null != includeSuffixStr && !"".equals(includeSuffixStr)) {
			AccessSecurityFilter.includeSuffixs = includeSuffixStr.split(",");
			for (int i = 0; i < AccessSecurityFilter.includeSuffixs.length; i++) {
				AccessSecurityFilter.includeSuffixs[i] = ("." + AccessSecurityFilter.includeSuffixs[i]);
			}
		} else {
			AccessSecurityFilter.includeSuffixs = AccessSecurityFilter.DEFAULT_INCLUDE_SUFFIXS;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		String path = req.getRequestURI();

		for (String suffix : AccessSecurityFilter.includeSuffixs) {
			if (path.endsWith(suffix)) {
				HttpSession session = req.getSession();
				if (session != null) {
					Boolean isRemoved = Boolean.valueOf(SecurityManager.SecurityContext.getRemovedSessionFromCache(CryptUtils.makMd5Digest(session.getId())));
					if (!isRemoved.booleanValue()) {
						SecurityManager.SecurityContext o = (SecurityContext) session.getAttribute(SecurityContext.SECURITY_CONTEXT_KEY);
						if (o != null) {
							SecurityManager.SecurityContext.setContext(o);
						}
					} else {
						session.invalidate();
					}
				}
				try {
					chain.doFilter(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					SecurityManager.SecurityContext.setContext(null);
				}
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}
}
