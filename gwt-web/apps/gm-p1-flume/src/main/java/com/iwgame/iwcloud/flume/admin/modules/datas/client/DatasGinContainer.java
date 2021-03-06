package com.iwgame.iwcloud.flume.admin.modules.datas.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

public class DatasGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(DatasModule.class).to(MonitorModuleImpl.class).in(Singleton.class);
	}

	public static class MonitorModuleImpl implements DatasModule {

		private final DatasActivityMapper mapper;

		@Inject
		public MonitorModuleImpl(final DatasActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "datas";
		}

		@Override
		public DatasActivityMapper getActivityMapper() {
			return mapper;
		}

	}
}