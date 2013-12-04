/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： KillMgrGinContainer.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.landing.client;

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
public class LandingGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(LandingModule.class).to(ReportsModuleImpl.class).in(Singleton.class);
	}

	public static class ReportsModuleImpl implements LandingModule {

		private final LandingActivityMapper mapper;

		@Inject
		public ReportsModuleImpl(final LandingActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "landing";
		}

		@Override
		public LandingActivityMapper getActivityMapper() {
			return mapper;
		}
	}
}
