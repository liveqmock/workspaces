/****************************************************************
 *  系统名称  ： '[LogMonitor]'
 *  文件名    ： ConfigGinContainer.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.business.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

/**
 * 
 * @简述：
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class BusinessGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(BusinessModule.class).to(BusinessModuleImpl.class).in(Singleton.class);
	}

	public static class BusinessModuleImpl implements BusinessModule {

		private final BusinessActivityMapper mapper;

		@Inject
		public BusinessModuleImpl(final BusinessActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "business";
		}

		@Override
		public BusinessActivityMapper getActivityMapper() {
			return mapper;
		}
	}
}
