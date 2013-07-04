/****************************************************************
 *  文件名   	:	Apptest.java
 *  日期		:  	2013-7-1
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.cps;


import java.util.Calendar;

import org.junit.Test;

/**
 * @描述:	TODO(...)
 *
 * @作者:	吴君杰
 * @邮箱:	wujunjie@iwgame.com
 * @日期:	2013-7-1 上午10:23:04
 * @版本:   	v1.0.0
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(value={"classpath:/etc/conf/*.xml"})
public class Apptest {

	@Test
	public void test1() throws Exception{

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2013);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
		System.out.println(calendar.getFirstDayOfWeek());

	}

}
