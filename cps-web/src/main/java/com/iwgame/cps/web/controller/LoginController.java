/****************************************************************
 *  文件名   	:	LoginController.java
 *  日期		:  	2013-6-29
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iwgame.cps.security.SecurityManager;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-6-29 下午4:36:42
 * @版本:   	v1.0.0
 */
@Controller
public class LoginController {




	/**
	 * 
	 */
	public LoginController() {
		System.out.println("init初始化成功!...");
	}


	@RequestMapping("/login.do")
	public String login(ModelMap modelMap){
		System.out.println("收到登录请求");
		modelMap.put("title", "欢迎登录");
		HttpSession session = SecurityManager.SecurityContext.getSessionFromCache("1");
		System.out.println(session.getAttribute("name"));
		return "login";
	}


	@RequestMapping("/main.do")
	public String main(ModelMap modelMap){
		System.out.println("收到登录请求");
		modelMap.put("title", "欢迎登录");
		return "login";
	}

}
