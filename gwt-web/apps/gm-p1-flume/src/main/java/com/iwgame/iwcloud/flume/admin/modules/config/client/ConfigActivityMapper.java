package com.iwgame.iwcloud.flume.admin.modules.config.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.iwcloud.flume.admin.modules.config.client.activity.FlumeChannelActivity;
import com.iwgame.iwcloud.flume.admin.modules.config.client.activity.FlumeEtcConfigActivity;
import com.iwgame.iwcloud.flume.admin.modules.config.client.activity.FlumeNodeActivity;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.FlumeChannelView;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.FlumeEtcConfigView;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.FlumeNodeView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

public class ConfigActivityMapper extends AbstractModuleActivityMapper {

	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public ConfigActivityMapper(final PlaceController placeController,
			final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action,
			final String[] params, final Map<String, String> queryParams) {

		if (action.equals("nodes")) {
			return new FlumeNodeActivity(FlumeNodeView.getInstance());
		}
		if (action.equals("channels")) {
			return new FlumeChannelActivity(new FlumeChannelView());
		}
		if (action.equals("etcconfig")){
			return new FlumeEtcConfigActivity(new FlumeEtcConfigView());
		}
		return super.onGetActivity(action, params, queryParams);
	}
}
