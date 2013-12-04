/**      
 * DetailsActivity.java Create on 2012-3-14     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.monitor.client.activity;

import java.util.Date;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.iwcloud.flume.admin.modules.monitor.client.ui.GroupTodayView;
import com.iwgame.iwcloud.flume.admin.modules.monitor.shared.service.MonitorService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BasePagingLoadConfig;
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
public class GroupTodayActivity extends AbstractActivity implements SchemaGridViewPresenter {

	private final GroupTodayView view;

	private String interval;

	/**
	 * @param view
	 */
	public GroupTodayActivity(final GroupTodayView view) {
		super();
		this.view = view;
		interval = Cookies.getCookie("com.iwgame.flume.monitor.interval");
		if (interval == null) {
			interval = "0";
		}
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

		view.setSchemaJson("{\"table\":{\"key\":\"groupId\",\"columns\":["
				+ "{\"index\":\"groupId\",\"header\":\"groupId\",\"visiable\":false},"
				+ "{\"index\":\"groupName\",\"header\":\"通道类型\",\"width\":150,\"type\":\"button\"},"
				+ "{\"index\":\"fetcherCount\",\"header\":\"采集汇总（条）\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"sinkCount\",\"header\":\"存储汇总（条）\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"status\",\"header\":\"状态\",\"width\":60},"
				+ "{\"index\":\"complete\",\"header\":\"完成率\",\"width\":80,\"type\":\"button\"},"
				+ "{\"index\":\"monitorStatus\",\"header\":\"通道状态\",\"width\":120,\"type\":\"button\"}" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load();
		view.setInterval(interval);
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
		BasePagingLoadConfig config = (BasePagingLoadConfig) loadConfig;
		Date date = config.get("viewdate");
		String filters = config.get("filter");
		MonitorService.Util.get().getGroupDatas(ApplicationContext.getCurrentProduct().getName(), filters, date,
				callback);

	}

}
