/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： KillMgrGinContainer.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-2 下午05:52:41
 */
public class ReportsGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(ReportsModule.class).to(ReportsModuleImpl.class).in(Singleton.class);
	}

	public static class ReportsModuleImpl implements ReportsModule {

		private final ReportsActivityMapper mapper;

		@Inject
		public ReportsModuleImpl(final ReportsActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "reports";
		}

		@Override
		public ReportsActivityMapper getActivityMapper() {
			return mapper;
		}
	}
}
