package com.iwgame.iwcloud.flume.admin.modules.history.client;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.iwgame.xmvp.client.module.XMVPModuleGinContainer;

public class HistoryGinContainer extends XMVPModuleGinContainer {

	@Override
	protected void configure() {
		super.configure();
		bind(HistoryModule.class).to(HistoryModuleImpl.class).in(
				Singleton.class);
	}

	public static class HistoryModuleImpl implements HistoryModule {

		private final HistoryActivityMapper mapper;

		@Inject
		public HistoryModuleImpl(final HistoryActivityMapper mapper) {
			super();
			this.mapper = mapper;
		}

		@Override
		public String getName() {
			return "history";
		}

		@Override
		public HistoryActivityMapper getActivityMapper() {
			return this.mapper;
		}

	}
}