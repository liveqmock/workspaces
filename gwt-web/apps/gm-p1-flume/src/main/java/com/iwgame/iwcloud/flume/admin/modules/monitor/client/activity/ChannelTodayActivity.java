/**      
 * DetailsActivity.java Create on 2012-3-14     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.monitor.client.activity;

import java.util.Date;
import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.iwcloud.flume.admin.client.i18n.Messages;
import com.iwgame.iwcloud.flume.admin.modules.monitor.client.ui.ChannelTodayView;
import com.iwgame.iwcloud.flume.admin.modules.monitor.shared.service.MonitorService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.PagingLoadResult;
import com.iwgame.ui.grid.client.SchemaGrid.Action;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: GroupTodayActivity
 * @Description: 通道Activity
 * @author 吴江晖
 * @date 2012-3-14 上午10:07:36
 * @Version 1.0
 * 
 */
public class ChannelTodayActivity extends AbstractActivity implements SchemaGridViewPresenter {

	private final ChannelTodayView view;

	private final String channelId;// 通道ID
	private final String channelName;// 通道名称
	private final Date date;// 查询日期
	private String interval;

	Messages messages = GWT.create(Messages.class);

	/**
	 * 构造函数
	 * 
	 * @param view
	 *            通道显示视图
	 * @param queryParams
	 *            查询参数
	 */
	public ChannelTodayActivity(final ChannelTodayView view, final Map<String, String> queryParams) {
		super();
		this.view = view;
		channelId = queryParams.get("channelId");
		channelName = queryParams.get("channelName");
		date = DateTimeFormat.getFormat("yyyyMMdd").parse(queryParams.get("date"));
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
		view.setSchemaJson("{\"table\":{\"key\":\"channelId\",\"columns\":["
				+ "{\"index\":\"channelId\",\"header\":\"channelId\",\"visiable\":false},"
				+ "{\"index\":\"channelName\",\"header\":\"通道名称\",\"width\":150},"
				+ "{\"index\":\"fetcherCount\",\"header\":\"采集汇总\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"fetcherStatus\",\"header\":\"采集状态\",\"width\":80},"
				+ "{\"index\":\"fetcherLastTime\",\"header\":\"采集最后更新时间\",\"width\":150,\"type\":\"timestamp\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"sinkCount\",\"header\":\"存储汇总\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"sinkStatus\",\"header\":\"存储状态\",\"width\":80},"
				+ "{\"index\":\"sinkLastTime\",\"header\":\"收集最后更新时间\",\"width\":150,\"type\":\"timestamp\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"costtime\",\"header\":\"入库累计耗时（秒）\",\"width\":150,\"type\":\"number\",\"visiable\":false},"
				+ "{\"index\":\"monitorStatus\",\"header\":\"通道状态\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"complete\",\"header\":\"完成率\",\"width\":80,\"type\":\"button\"}" + "]}}");
		view.setPresenter(this);
		view.setDate(date);
		view.getPanel().setHeader("");
		view.getGrid().setOnDataLoad(new Action<BaseModelData>() {

			@Override
			public void execute(final PagingLoadResult<BaseModelData> datas) {
				String title = null;
				if (DateWrapper.isToday(date)) {
					title = messages.channelTitle(channelName, messages.today(), view.getGrid().getVisibleItemCount());
				} else {
					DateTimeFormat format = DateTimeFormat.getFormat(messages.titleDateFormat());
					title = messages.channelTitle(channelName, format.format(date), datas.getTotal());
				}
				view.getPanel().setHeader(title);

			}
		});
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
		MonitorService.Util.get().getChannelDatas(ApplicationContext.getCurrentProduct().getName(), channelId, date,
				callback);

	}

}
