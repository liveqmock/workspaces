/**      
 * DetailsActivity.java Create on 2012-3-14     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.config.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.FlumeEtcConfigView;
import com.iwgame.iwcloud.flume.admin.modules.config.shared.service.ConfigService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: TodayGroupActivity
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-3-14 上午10:07:36
 * @Version 1.0
 * 
 */
public class FlumeEtcConfigActivity extends AbstractActivity implements
		SchemaGridViewPresenter {

	private final FlumeEtcConfigView view;

	/**
	 * @param view
	 */
	public FlumeEtcConfigActivity(final FlumeEtcConfigView view) {
		super();
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

		view.setSchemaJson("{\"table\":{\"key\":\"t_channel_id\",\"columns\":["
				+ "{\"index\":\"t_channel_id\",\"width\":44,\"header\":\"通道ID\"},"
				+ "{\"index\":\"t_channel_name\",\"header\":\"通道名称\",\"width\":55},"
				+ "{\"index\":\"t_exec\",\"header\":\"执行命令\",\"width\":300},"
				+ "{\"index\":\"t_status\",\"header\":\"状态\",\"width\":30},"
				+ "{\"index\":\"t_zone\",\"header\":\"大区\",\"width\":30},"
				+ "{\"index\":\"t_group\",\"header\":\"游戏组\",\"width\":33}"
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
	public void loadData(final Object loadConfig,
			final AsyncCallbackEx<String> callback) {
		ConfigService.Util.get().getEtcConfig(
				ApplicationContext.getCurrentProduct().getName(), callback);

	}

}
