/**      
 * BusinessLogMonitorActivity.java Create on 2012-9-3     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.client.activity;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.logmonitor.modules.business.client.presenter.BusinessActivity;
import com.iwgame.gm.p1.logmonitor.modules.business.client.ui.BusinessLogMonitorView;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc.BusinessService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.client.log.XMVPLogger;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: BusinessLogMonitorActivity
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午03:46:19
 * @Version 1.0
 * 
 */
public class BusinessLogMonitorActivity extends BusinessActivity {

	private static final XMVPLogger logger = new XMVPLogger(BusinessLogMonitorActivity.class);

	private final BusinessLogMonitorView view;
	private final String action;

	private static final Map<String, String> sourceTitle;

	static {
		sourceTitle = new HashMap<String, String>();
		sourceTitle.put("ip", "独立访问IP");
		sourceTitle.put("source", "来源模块");
		sourceTitle.put("account", "独立帐号");
	}

	public BusinessLogMonitorActivity(final BusinessLogMonitorView view, final String action) {
		super();
		this.view = view;
		this.action = action;
		this.view.setAction(action);
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
		view.getFilterField().setLabel(sourceTitle.get(action));
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"ID\",\"visiable\":false},"
				+ "{\"index\":\"date\",\"header\":\"时间\",\"width\":150,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"source\",\"header\":\"" + sourceTitle.get(action)
				+ "\",\"width\":150,\"type\":\"button\"}," + "{\"index\":\"type\",\"header\":\"业务类型\",\"width\":100},"
				+ "{\"index\":\"successCount\",\"header\":\"成功处理数\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"failedCount\",\"header\":\"失败处理数\",\"width\":60,\"type\":\"number\"},"
				+ "{\"index\":\"totalCount\",\"header\":\"总请求数\",\"width\":80,\"type\":\"number\"}" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.getQueryParams();
		view.load();
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
		logger.info("监控类型为：" + action);
		BaseFilterPagingLoadConfig config = (BaseFilterPagingLoadConfig) loadConfig;
		Number threshold = config.get("threshold");
		String filter = config.get("filter");
		BusinessService.Util.get().getLogData(ApplicationContext.getCurrentProductId(), action,
				config.<String> get("dateType"), threshold != null ? threshold.intValue() : 0, filter, callback);

	}

}
