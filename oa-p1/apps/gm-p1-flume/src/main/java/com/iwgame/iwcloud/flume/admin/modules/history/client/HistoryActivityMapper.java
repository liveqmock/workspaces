package com.iwgame.iwcloud.flume.admin.modules.history.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.iwcloud.flume.admin.modules.history.client.activity.ChannelHistoryActivity;
import com.iwgame.iwcloud.flume.admin.modules.history.client.activity.DataIntegrityActivity;
import com.iwgame.iwcloud.flume.admin.modules.history.client.activity.GroupHistoryActivity;
import com.iwgame.iwcloud.flume.admin.modules.history.client.ui.ChannelHistoryView;
import com.iwgame.iwcloud.flume.admin.modules.history.client.ui.DataIntegrityView;
import com.iwgame.iwcloud.flume.admin.modules.history.client.ui.GroupHistoryView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

public class HistoryActivityMapper extends AbstractModuleActivityMapper {

	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public HistoryActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {

		if (action.equals("group") && queryParams != null) {
			String a = queryParams.get("action");
			if ("d".equals(a)) {
				return new ChannelHistoryActivity(new ChannelHistoryView(), queryParams);
			} else if ("m".equals(a)) {
				return new GroupHistoryActivity(new GroupHistoryView(DateTimeFormat.getFormat("yyyyMMdd").parse(queryParams.get("date"))));
			}
		}
		if (action.equals("group")) {
			return new GroupHistoryActivity(new GroupHistoryView());
		}
		if(action.equals("dataintegrity")){
			return new DataIntegrityActivity(new DataIntegrityView());
		}
		
		return super.onGetActivity(action, params, queryParams);
	}
}
