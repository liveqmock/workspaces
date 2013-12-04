/****************************************************************
 *  系统名称  ： '[Security]'
 *  文件名    ： QueryGinContainer.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.manage.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

/**
 * 
 * @简述： 
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class ManageGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(ManageModule.class).to(QueryModuleImpl.class).in(Singleton.class);
	}

	public static class QueryModuleImpl implements ManageModule {

		private final ManageActivityMapper mapper;

		@Inject
		public QueryModuleImpl(final ManageActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "manage";
		}

		@Override
		public ManageActivityMapper getActivityMapper() {
			return mapper;
		}
	}
}
