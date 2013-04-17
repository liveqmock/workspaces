/**      
 * BusinessLogMonitorActivity.java Create on 2012-9-3     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.client.activity;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.logmonitor.modules.business.client.presenter.BusinessActivity;
import com.iwgame.gm.p1.logmonitor.modules.business.client.ui.WarningPortalView;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc.BusinessService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.xmvp.client.log.XMVPLogger;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: BusinessWarningActivity
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午03:46:19
 * @Version 1.0
 * 
 */
public class BusinessWarningActivity extends BusinessActivity {

	private static final XMVPLogger logger = new XMVPLogger(BusinessWarningActivity.class);

	private final WarningPortalView view;

	public BusinessWarningActivity(final WarningPortalView view) {
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
		// view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
		// + "{\"index\":\"id\",\"header\":\"ID\",\"visiable\":false},"
		// + "{\"index\":\"monitorDate\",\"header\":\"日期\",\"width\":150},"
		// + "{\"index\":\"monitorType\",\"header\":\"监控类型\",\"width\":150},"
		// +
		// "{\"index\":\"maxCount\",\"header\":\"设定的警戒值\",\"width\":60,\"type\":\"number\"},"
		// +
		// "{\"index\":\"successCount\",\"header\":\"成功请求数\",\"width\":100,\"type\":\"number\"},"
		// +
		// "{\"index\":\"failedCount\",\"header\":\"失败请求数\",\"width\":100,\"type\":\"number\"}"
		// + "]}}");
		// view.setPresenter(this);
		panel.setWidget(view);
		// view.load();
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
		BusinessService.Util.get().getWarningData(ApplicationContext.getCurrentProductId(), callback);
	}

}
