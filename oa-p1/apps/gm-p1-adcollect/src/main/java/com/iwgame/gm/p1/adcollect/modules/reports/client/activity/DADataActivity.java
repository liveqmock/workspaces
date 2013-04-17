/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DADataActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.client.activity;

import java.util.Date;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.reports.client.presenter.DADataPresenter;
import com.iwgame.gm.p1.adcollect.modules.reports.client.ui.ADDataView;
import com.iwgame.gm.p1.adcollect.modules.reports.shared.rpc.ADDataService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 广告数据实时追踪
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-13 上午09:12:34
 */
public class DADataActivity extends AbstractActivity implements DADataPresenter {

	private final ADDataView view;
	private final int adid;
	private final Date date;

	public DADataActivity(final int adid1, final Date date1, final ADDataView adDataView) {
		super();
		view = adDataView;
		adid = adid1;
		date = date1;
	}

	@Override
	public void goTo(final Place place) {

	}

	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		final BaseFilterPagingLoadConfig _loadConfig = (BaseFilterPagingLoadConfig) loadConfig;
		if (adid != 0) {
			final DateWrapper dateWrapper = new DateWrapper(date);
			_loadConfig.set("adid", adid);
			_loadConfig.set("startTime", DateWrapper.format(dateWrapper.addHours(-1).asDate(), "yyyy-MM-dd HH:mm"));
			_loadConfig.set("endTime", DateWrapper.format(dateWrapper.asDate(), "yyyy-MM-dd HH:mm"));
		}
		ADDataService.Util.get().getADDataListById(ApplicationContext.getCurrentProductId(), _loadConfig, callback);
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"date\",\"header\":\"时间\",\"width\":240},"// ,\"type\":\"date\",\"format\":\"yyyy-MM-dd
																			// HH:mm\"
				+ "{\"index\":\"adId\",\"header\":\"广告ID\",\"width\":100},"
				+ "{\"index\":\"click\",\"header\":\"点击\",\"type\":\"number\",\"width\":100},"
				+ "{\"index\":\"ipClick\",\"header\":\"独立IP点击数\",\"type\":\"number\",\"width\":100},"
				+ "{\"index\":\"reg\",\"header\":\"注册\",\"type\":\"number\",\"width\":100},"
				+ "{\"index\":\"ipReg\",\"header\":\"独立IP注册数\",\"type\":\"number\"}" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		if (adid == 0) {
			view.load(false);
		} else {
			view.load(true);
		}

	}

}
