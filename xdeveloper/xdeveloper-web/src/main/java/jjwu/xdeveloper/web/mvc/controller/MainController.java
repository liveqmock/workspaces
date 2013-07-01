/****************************************************************
 *  文件名   	:	MainController.java
 *  日期		:  	2013-4-27
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package jjwu.xdeveloper.web.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-4-27 下午6:03:02
 * @版本:   	v1.0.0
 */
@Controller
public class MainController extends BaseAction{

	/**
	 * 
	 */
	public MainController() {
		System.out.println("init初始化成功!...");
	}

	@RequestMapping("/main.do")
	public String main(ModelMap modelMap){
		final Map<String,Object> data = new HashMap<String, Object>();
		data.put("name", "wujunjie");
		data.put("age", "28");
		data.put("sex", "male");
		data.put("title", "Freemarker");
		modelMap.put("data", data);
		return "main";
	}

	@RequestMapping("/test.do")
	public void test(ModelMap modelMap,HttpServletResponse response,HttpServletRequest request){
		final Map<String,Object> data = new HashMap<String, Object>();
		data.put("name", "wujunjie");
		data.put("age", "28");
		data.put("sex", "男");
		data.put("title", "Freemarker");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		BaseAction.renderJson(response,data);
	}


	@RequestMapping("/login.do")
	public String login(ModelMap modelMap,HttpServletResponse response,HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		modelMap.put("username", username);
		modelMap.put("password", password);
		modelMap.put("title", "登录成功!");
		return "security";

	}
}
