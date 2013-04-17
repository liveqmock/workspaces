/**      
 * FlumeChannelActivity.java Create on 2012-5-30     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.config.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.FlumeChannelView;
import com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BasePagingLoadConfig;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: FlumeChannelActivity
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-5-30 下午06:00:39
 * @Version 1.0
 * 
 */
public class FlumeChannelActivity extends AbstractActivity implements SchemaGridViewPresenter {

	private final FlumeChannelView view;

	/**
	 * 
	 */
	public FlumeChannelActivity(final FlumeChannelView view) {
		this.view = view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
	 * .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
	 */
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"channelId\",\"columns\":["
				+ "{\"index\":\"channelId\",\"header\":\"通道编号\",\"visiable\":false},"
				+ "{\"index\":\"channelName\",\"header\":\"通道名称\",\"width\":150,\"type\":\"button\"},"
				+ "{\"index\":\"status\",\"header\":\"通道状态\",\"width\":150,\"type\":\"number\"},"
				+ "{\"index\":\"aNodeStatus\",\"header\":\"采集状态\",\"width\":100},"
				+ "{\"index\":\"aNodeActive\",\"header\":\"采集管理\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"aNodeDeactive\",\"header\":\"\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"aNodeCommand\",\"header\":\"\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"cNodeStatus\",\"header\":\"存储状态\",\"width\":100},"
				+ "{\"index\":\"cNodeActive\",\"header\":\"存储管理\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"cNodeDeactive\",\"header\":\"\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"room\",\"header\":\"采集机房信息\",\"width\":100},"
				+ "{\"index\":\"admin\",\"header\":\"管理\",\"width\":100,\"type\":\"button\"}," 
				+ "{\"index\":\"action\",\"header\":\"操作\",\"width\":100,\"type\":\"button\"}"
				+ "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#goTo(com.google
	 * .gwt.place.shared.Place)
	 */
	@Override
	public void goTo(final Place place) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#loadData(java.
	 * lang.Object, com.iwgame.ui.core.client.AsyncCallbackEx)
	 */
	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		BasePagingLoadConfig _loadConfig = (BasePagingLoadConfig) loadConfig;
		_loadConfig.set("productId", ApplicationContext.getCurrentProduct().getName());
		ConfigService.Util.get().retrieveFlumeChannels(_loadConfig, callback);

	}

}
