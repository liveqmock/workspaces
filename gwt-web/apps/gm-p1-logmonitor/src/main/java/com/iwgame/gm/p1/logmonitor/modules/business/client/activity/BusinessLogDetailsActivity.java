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
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.logmonitor.modules.business.client.presenter.BusinessActivity;
import com.iwgame.gm.p1.logmonitor.modules.business.client.ui.BusinessLogDetailsView;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc.BusinessService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.client.log.XMVPLogger;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: BusinessLogDetailsActivity
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午03:46:19
 * @Version 1.0
 * 
 */
public class BusinessLogDetailsActivity extends BusinessActivity {

	private static final XMVPLogger logger = new XMVPLogger(BusinessLogDetailsActivity.class);

	private final BusinessLogDetailsView view;
	private final Map<String, String> queryParams;

	public BusinessLogDetailsActivity(final BusinessLogDetailsView view, final Map<String, String> queryParams) {
		super();
		this.view = view;
		this.queryParams = queryParams;
	}

	private static Map<String, DateTimeFormat> formats = new HashMap<String, DateTimeFormat>();
	static {
		formats.put("minute", DateTimeFormat.getFormat("yyyy-MM-dd HH:mm"));
		formats.put("hour", DateTimeFormat.getFormat("yyyy-MM-dd HH:00"));
		formats.put("day", DateTimeFormat.getFormat("yyyy-MM-dd"));
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
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"ID\",\"visiable\":false},"
				+ "{\"index\":\"requestTime\",\"header\":\"请求时间\",\"width\":150,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"requesetSource\",\"header\":\"业务来源\",\"width\":100},"
				+ "{\"index\":\"requestType\",\"header\":\"业务类型\",\"width\":100},"
				+ "{\"index\":\"requestUsername\",\"header\":\"帐号\",\"width\":200},"
				+ "{\"index\":\"requestIp\",\"header\":\"登录IP\",\"width\":100},"
				+ "{\"index\":\"requestStatus\",\"header\":\"状态\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"requestMsg\",\"header\":\"备注消息\",\"width\":200}" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);

		if ((queryParams != null) && !queryParams.isEmpty()) {
			String time = queryParams.remove("time");
			String type = queryParams.remove("type");
			DateWrapper dw = new DateWrapper(formats.get(type).parse(time));
			view.setStartDate(dw.asDate());
			view.setEndDate(dw.clearTime().addDays(1).asDate());
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if ("account".equals(key)) {
					view.setUsername(value);
				}
				if ("ip".equals(key)) {
					view.setIp(value);
				}
				if ("source".equals(key)) {
					view.getGrid().getLoadConfig().set("requestSourceExt", value);
					if (value.contains("活动")) {
						value = "活动";
					}
					view.setSource(value);
				}
			}
		}

		view.getQueryParams();
		view.load();
		view.getGrid().getLoadConfig().remove("requestSourceExt");
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
		BusinessService.Util.get().getLogDetailDatas(ApplicationContext.getCurrentProductId(),
				(BaseFilterPagingLoadConfig) loadConfig, callback);
	}

}
