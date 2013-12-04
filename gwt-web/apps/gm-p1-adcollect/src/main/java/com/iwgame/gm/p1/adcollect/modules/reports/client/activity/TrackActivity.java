/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： TrackActivity.java
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
import com.iwgame.gm.p1.adcollect.modules.reports.client.presenter.TrackPresenter;
import com.iwgame.gm.p1.adcollect.modules.reports.client.ui.TrackListView;
import com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc.HourTrackService;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述：百度小时关键字追踪表
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-20 上午10:57:13
 */
public class TrackActivity extends AbstractActivity implements TrackPresenter {

	private final TrackListView view;

	public TrackActivity(TrackListView trackListView) {
		super();
		view = trackListView;
	}

	@Override
	public void goTo(Place place) {

	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		final String productId = ApplicationContext.getCurrentProductId();
		final BaseFilterPagingLoadConfig bfploadConfig = (BaseFilterPagingLoadConfig) loadConfig;
		if (bfploadConfig.<String> get("type").equals("0")) {
			HourTrackService.Util.get().getHourTrackListByTxt(productId, bfploadConfig, callback);
		} else if (bfploadConfig.<String> get("type").equals("1")) {
			HourTrackService.Util.get().getHourTrackListById(productId, bfploadConfig, callback);
		}
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":[" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(false);
	}

	@Override
	public void getRaceListByKeyName(String productId, String keyName, AsyncCallback<List<AdvertisementInfo>> callback) {
		HourTrackService.Util.get().getRaceListByKeyName(productId, keyName, callback);
	}

}
