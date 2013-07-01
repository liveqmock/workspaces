/****************************************************************
 *  文件名   	:	Apptest.java
 *  日期		:  	2013-7-1
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps;


import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

import com.iwgame.cps.security.User;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-7-1 上午10:23:04
 * @版本:   	v1.0.0
 */
public class Apptest {

	@Test
	public void test1() throws Exception{
		User user = new User();
		user.setName("wjj");
		JSONObject jsonObj = new JSONObject(user);
		System.out.println(XML.toString(jsonObj));
	}

}
