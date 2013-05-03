/****************************************************************
 * 文件名 : AppTest.java
 * 日期 : 2013-4-29
 * Company: 上海绿岸网络科技有限公司
 * (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 * All Rights Reserved.
 * 注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.test;

import jjwu.xdeveloper.xvalidators.bean.UsersBean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @描述: TODO(...)
 * 
 * @作者: 吴君杰
 * @邮箱: wujunjie@iwgame.com
 * @日期: 2013-4-29 上午9:04:59
 * @版本: v1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath*:/conf/app-Xvalidator.xml" })
public class AppTest {

	//	@Resource
	//	private UsersBean usersBean;

	@Test
	public void test() {
		final UsersBean usersBean = new UsersBean();
		usersBean.getUsername();
		usersBean.getPassword();
	}
}
