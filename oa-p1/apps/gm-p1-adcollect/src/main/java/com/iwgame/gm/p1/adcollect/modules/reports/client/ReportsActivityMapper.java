/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： ForceoutMgrActivityMapper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.client;

import java.util.Date;
import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.adcollect.modules.reports.client.activity.DADataActivity;
import com.iwgame.gm.p1.adcollect.modules.reports.client.activity.DayListActivity;
import com.iwgame.gm.p1.adcollect.modules.reports.client.activity.TrackActivity;
import com.iwgame.gm.p1.adcollect.modules.reports.client.ui.ADDataView;
import com.iwgame.gm.p1.adcollect.modules.reports.client.ui.DayListView;
import com.iwgame.gm.p1.adcollect.modules.reports.client.ui.TrackListView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-2 下午05:50:45
 */
public class ReportsActivityMapper extends AbstractModuleActivityMapper {
	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public ReportsActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {
		if ("addata".equals(action)) {
			int adid = 0;
			if (null != queryParams) {
				try {
					final String sadid = queryParams.get("adid").toString();
					adid = Integer.parseInt(sadid);
				} catch (final Exception e) {
				}
			}
			final Date date = new Date();
			return new DADataActivity(adid, date, new ADDataView(adid, date));
		} else if ("list".equals(action)) {
			return new TrackActivity(new TrackListView());
		} else if ("daylist".equals(action)) {
			String beginDate = "";
			String endDate = "";
			String type = "";
			if (null != queryParams) {
				if (queryParams.containsKey("beginDate")) {
					try {
						beginDate = queryParams.get("beginDate").toString();
					} catch (final Exception e) {
					}
				}
				if (queryParams.containsKey("endDate")) {
					try {
						endDate = queryParams.get("endDate").toString();
					} catch (final Exception e) {
					}
				}
				if (queryParams.containsKey("type")) {
					try {
						type = queryParams.get("type").toString();
					} catch (final Exception e) {
					}
				}
			}
			return new DayListActivity(new DayListView(beginDate, endDate, type));
		}

		return super.onGetActivity(action, params, queryParams);
	}
}
