/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DayListActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.client.activity;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.reports.client.presenter.DayListPresenter;
import com.iwgame.gm.p1.adcollect.modules.reports.client.ui.DayListView;
import com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc.DayService;
import com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc.HourTrackService;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.AggregatePresenter;
import com.iwgame.xmvp.shared.data.RpcMap;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 百度关键字日汇总表(流水)
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-20 上午11:33:05
 */
public class DayListActivity extends AbstractActivity implements DayListPresenter, AggregatePresenter {

	private final DayListView view;

	public DayListActivity(final DayListView dayListView) {
		super();
		view = dayListView;
	}

	@Override
	public void goTo(final Place place) {

	}

	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		final String productId = ApplicationContext.getCurrentProductId();
		final BaseFilterPagingLoadConfig bfploadConfig = (BaseFilterPagingLoadConfig) loadConfig;
		if (bfploadConfig.<String> get("statistics").equals("0")) {// 文本
			DayService.Util.get().getDayListByTxt(productId, bfploadConfig, callback);
		} else if (bfploadConfig.<String> get("statistics").equals("1")) {// 广告ID
			DayService.Util.get().getDayListById(productId, bfploadConfig, callback);
		}
		loadAggregate();
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":[" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(false);
	}

	@Override
	public void getRaceListByKeyName(final String productId, final String keyName,
			final AsyncCallback<List<AdvertisementInfo>> callback1) {
		HourTrackService.Util.get().getRaceListByKeyName(productId, keyName, callback1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.grid.client.AggregatePresenter#loadAggregate(java.lang.
	 * Integer)
	 */
	@Override
	public void loadAggregate() {
		final String productId = ApplicationContext.getCurrentProductId();
		final int type = view.getAggregateType();
		if (type == 4) {
			DayService.Util.get().getAggregateDayByTxt(productId, view.getGrid().getLoadConfig(),
					new AsyncCallbackEx<RpcMap>() {

						@Override
						public void onSuccess(final RpcMap result) {
							view.getGrid().createAggregates(result.getTransientMap());
						}
					});
		}
		if (type == 3) {
			DayService.Util.get().getAggregateDayById(productId, view.getGrid().getLoadConfig(),
					new AsyncCallbackEx<RpcMap>() {

						@Override
						public void onSuccess(final RpcMap result) {
							view.getGrid().createAggregates(result.getTransientMap());
						}
					});
		}
	}

}
