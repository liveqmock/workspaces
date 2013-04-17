/****************************************************************
 *  系统名称  ： 'xTask-core'核心类
 *  文件名    ： ApplicationContextHelper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.xengine.xtask.core.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 类说明
 * @简述： Spring工具类
 * @作者： 陈小龙
 * @版本： 1.0
 * @邮箱： chenxiaolong@iwgame.com
 * @修改时间：2012-2-9 下午04:29:27
 */
public class ApplicationContextHelper {
	private static ApplicationContext ctx = null;

	private ApplicationContextHelper() {
	};

	public static void configSpringContextService() {
		if (ctx == null) {
			synchronized (ApplicationContextHelper.class) { // 保证了同一时间只能只能有一个对象访问此同步块
				if (ctx == null) {
					ctx = new ClassPathXmlApplicationContext("classpath*:conf/*.xml");
				}
			}
		}
	}
	
	public static ApplicationContext getApplicationContext() {
		if (ctx == null) {
			configSpringContextService();
		}
		return ctx;
	}
}
