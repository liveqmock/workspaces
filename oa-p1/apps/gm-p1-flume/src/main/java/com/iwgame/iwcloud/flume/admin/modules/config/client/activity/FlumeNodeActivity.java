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
import com.iwgame.iwcloud.flume.admin.modules.config.client.ui.FlumeNodeView;
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
public class FlumeNodeActivity extends AbstractActivity implements
		SchemaGridViewPresenter {

	private final FlumeNodeView view;

	/**
	 * @param view
	 */
	public FlumeNodeActivity(final FlumeNodeView view) {
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

		view.setSchemaJson("{\"table\":{\"key\":\"nodeId\",\"columns\":["
				+ "{\"index\":\"nodeId\",\"header\":\"节点编号\"},"
				+ "{\"index\":\"nodeName\",\"header\":\"节点名称\",\"width\":150},"
				+ "{\"index\":\"nodeType\",\"header\":\"节点类型\",\"width\":100},"
				+ "{\"index\":\"nodeIpInfo\",\"header\":\"机房IP\",\"width\":100}"
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
		ConfigService.Util.get().retrieveFlumeNodes(
				ApplicationContext.getCurrentProduct().getName(), callback);

	}

}
