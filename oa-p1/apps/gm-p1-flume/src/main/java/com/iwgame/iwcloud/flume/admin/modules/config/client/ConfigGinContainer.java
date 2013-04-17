package com.iwgame.iwcloud.flume.admin.modules.config.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

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
			return this.mapper;
		}

	}
}