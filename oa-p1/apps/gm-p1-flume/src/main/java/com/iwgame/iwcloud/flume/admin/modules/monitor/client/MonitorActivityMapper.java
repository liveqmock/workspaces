package com.iwgame.iwcloud.flume.admin.modules.monitor.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.iwcloud.flume.admin.modules.monitor.client.activity.ChannelTodayActivity;
import com.iwgame.iwcloud.flume.admin.modules.monitor.client.activity.GroupTodayActivity;
import com.iwgame.iwcloud.flume.admin.modules.monitor.client.ui.ChannelTodayView;
import com.iwgame.iwcloud.flume.admin.modules.monitor.client.ui.GroupTodayView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

public class MonitorActivityMapper extends AbstractModuleActivityMapper {

	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public MonitorActivityMapper(final PlaceController placeController,
			final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action,
			final String[] params, final Map<String, String> queryParams) {

		if (action.equals("group") && queryParams != null) {
			String a = queryParams.get("action");
			if ("d".equals(a)) {
				return new ChannelTodayActivity(new ChannelTodayView(), queryParams);
			} else if ("m".equals(a)) {
				return new GroupTodayActivity(new GroupTodayView(DateTimeFormat
						.getFormat("yyyyMMdd").parse(queryParams.get("date"))));
			}
		}
		if (action.equals("group")) {
			return new GroupTodayActivity(new GroupTodayView());
		}
		return super.onGetActivity(action, params, queryParams);
	}
}
