/**      
 * DetailsActivity.java Create on 2012-3-14     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.history.client.activity;

import java.util.Date;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.iwcloud.flume.admin.modules.history.client.ui.DataIntegrityView;
import com.iwgame.iwcloud.flume.admin.modules.history.shared.service.HistoryService;
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
public class DataIntegrityActivity extends AbstractActivity implements SchemaGridViewPresenter {

	private final DataIntegrityView view;

	/**
	 * @param view
	 */
	public DataIntegrityActivity(final DataIntegrityView view) {
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
		
		String productId = ApplicationContext.getCurrentProduct().getName();
		
		if("P-P1".equalsIgnoreCase(productId) || "P-P1.5".equalsIgnoreCase(productId)){
			view.setSchemaJson("{\"table\":{\"key\":\"zone\",\"columns\":["
					+ "{\"index\":\"date\",\"header\":\"日期\",\"width\":88,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
					+ "{\"index\":\"zone\",\"header\":\"大区\",\"width\":50},"
					+ "{\"index\":\"activity_paid\",\"header\":\"计算充值\",\"width\":100,\"type\":\"number\"},"
					+ "{\"index\":\"activity_consume\",\"header\":\"计算消费\",\"width\":100,\"type\":\"number\"},"
					+ "{\"index\":\"paid_count\",\"header\":\"备份充值人数\",\"width\":100,\"type\":\"number\"},"
					+ "{\"index\":\"bak_paid\",\"header\":\"备份充值金额\",\"width\":100,\"type\":\"number\"}," 
					+ "{\"index\":\"consume_count\",\"header\":\"备份消费人数\",\"width\":100,\"type\":\"number\"}," 
					+ "{\"index\":\"bak_consume\",\"header\":\"备份消费金额\",\"width\":100,\"type\":\"number\"}," 
					+ "{\"index\":\"contrast_paid\",\"header\":\"计算充值-备份充值\",\"width\":130,\"type\":\"number\"}," 
					+ "{\"index\":\"contrast_consume\",\"header\":\"计算消费-备份消费\",\"width\":130,\"type\":\"number\"}" 
					+ "]}}");
		}else{
			view.setSchemaJson("{\"table\":{\"key\":\"zone\",\"columns\":["
					+ "{\"index\":\"date\",\"header\":\"日期\",\"width\":120,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
					+ "{\"index\":\"zone\",\"header\":\"大区\",\"width\":120},"
					+ "{\"index\":\"paid_min_value\",\"header\":\"当日充值最小值\",\"width\":120,\"type\":\"number\"},"
					+ "{\"index\":\"paid_max_value\",\"header\":\"当日充值最大值\",\"width\":120,\"type\":\"number\"},"
					+ "{\"index\":\"paid_count_value\",\"header\":\"当日总条数\",\"width\":120,\"type\":\"number\"},"
					+ "{\"index\":\"paid_sum_value\",\"header\":\"当日RMB总充值\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"paid_sum_free_value\",\"header\":\"当日免费总充值\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"paid_activity_value\",\"header\":\"计算RMB总充值\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"paid_activity_free_value\",\"header\":\"计算免费总充值\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"consume_min_value\",\"header\":\"当日消费最小值\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"consume_max_value\",\"header\":\"当日消费最大值\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"consume_count_value\",\"header\":\"当日总条数\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"consume_sum_value\",\"header\":\"当日RMB总消费\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"consume_sum_free_value\",\"header\":\"当日免费总消费\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"consume_activity_value\",\"header\":\"计算RMB总消费\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"consume_activity_free_value\",\"header\":\"计算免费总消费\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"contrast_paid_count\",\"header\":\"总条数对比\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"contrast_paid_activity\",\"header\":\"计算RMB充值\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"contrast_paid_activity_free\",\"header\":\"计算免费充值\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"contrast_consume_count\",\"header\":\"总条数对比\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"contrast_consume_activity\",\"header\":\"计算RMB消费\",\"width\":120,\"type\":\"number\"}," 
					+ "{\"index\":\"contrast_consume_activity_free\",\"header\":\"计算免费消费\",\"width\":120,\"type\":\"number\"}" 
					+ "]}}");
		}
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
		BasePagingLoadConfig config = (BasePagingLoadConfig) loadConfig;
		Date date = config.get("viewdate");
		String filters = config.get("filter");
		HistoryService.Util.get().getDataIntegrityReport(ApplicationContext.getCurrentProduct().getName(), filters, date,
				callback);

	}

}
