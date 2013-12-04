/****************************************************************
 *  系统名称  ： '[LogMonitor]'
 *  文件名    ： ConfigGinContainer.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.config.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

/**
 * 
 * @简述：
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class ConfigGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(ConfigModule.class).to(ConfigModuleImpl.class).in(Singleton.class);
	}

	public static class ConfigModuleImpl implements ConfigModule {

		private final ConfigActivityMapper mapper;

		@Inject
		public ConfigModuleImpl(final ConfigActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "config";
		}

		@Override
		public ConfigActivityMapper getActivityMapper() {
			return mapper;
		}
	}
}
