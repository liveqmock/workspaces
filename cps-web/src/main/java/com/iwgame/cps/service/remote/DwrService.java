package com.iwgame.cps.service.remote;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iwgame.cps.security.SecurityManager.SecurityContext;
import com.iwgame.cps.security.User;
import com.iwgame.cps.service.CommonService;
import com.iwgame.cps.support.Message;
import com.iwgame.cps.util.CryptUtils;
import com.iwgame.cps.util.Utils;


/**
 * Dwr 通用方法定义
 **/
@Service
@RemoteProxy
public class DwrService {

	protected Logger logger = Logger.getLogger(DwrService.class);

	@Autowired
	private CommonService commonService;

	private String lookupHost(HttpServletRequest request) {
		String hostip = request.getHeader("X-Forwarded-For");
		if (null != hostip && !"".equals(hostip)) {
			hostip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (null != hostip && !"".equals(hostip)) {
			hostip = request.getRemoteAddr();
		}
		if (null != hostip && !"".equals(hostip)) {
			hostip = "unknown";
		}
		return hostip;
	}

	/**
	 * 登录用户验证
	 **/
	@RemoteMethod
	public Message forCheckUser(String username, String passwd, String captcha,Date endDate, HttpServletRequest request) {
		HttpSession session = WebContextFactory.get().getSession();
		if (Utils.Constant.jcaptcha.toLowerCase().equals("true")) {
			String valiCode = (String) session.getAttribute("valiCode");
			if (!captcha.equalsIgnoreCase(valiCode)) {
				return new Message("-1", "校验码错误");
			}
		}
		try {
			SecurityContext sc = new SecurityContext();
			User user = commonService.checkUser(username, passwd, captcha,endDate);
			user.setHostip(lookupHost(request));
			user.setServerIP(request.getServerName() + ":"+ request.getServerPort());
			user.setSessionId(CryptUtils.makMd5Digest(session.getId()));
			sc.setUser(user);
			sc.bindSession(session);
		} catch (Exception e) {
			return new Message("-1", "登录异常:" + e.getMessage());
		}
		return new Message("1", "success");
	}
}