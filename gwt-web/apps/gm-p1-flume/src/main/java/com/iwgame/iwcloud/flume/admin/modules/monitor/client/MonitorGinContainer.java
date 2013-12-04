package com.iwgame.iwcloud.flume.admin.modules.monitor.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

public class MonitorGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(MonitorModule.class).to(MonitorModuleImpl.class).in(
				Singleton.class);
	}

	public static class MonitorModuleImpl implements MonitorModule {

		private final MonitorActivityMapper mapper;

		@Inject
		public MonitorModuleImpl(final MonitorActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "monitor";
		}

		@Override
		public MonitorActivityMapper getActivityMapper() {
			return this.mapper;
		}

	}
}