/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LogosMgrActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.LogosMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.LogosMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： LOGOS控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午03:04:48
 */
public class LogosMgrActivity extends AbstractActivity implements LogosMgrPresenter {

	private final String productId = ApplicationContext.getCurrentProductId();

	private final LogosMgrListView view;

	public LogosMgrActivity(final LogosMgrListView contractMgrListView) {
		super();
		view = contractMgrListView;
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"Logo ID\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"name\",\"header\":\"Logo 名称\",\"width\":100},"
				+ "{\"index\":\"media\",\"header\":\"Logo\",\"width\":60,\"type\":\"image\",\"downloadbean\":\"ad.download\"},"
				+ "{\"index\":\"createTime\",\"header\":\"添加日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"updateTime\",\"header\":\"修改日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}"
				+ "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
	}

	@Override
	public void goTo(final Place place) {

	}

	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		AdminMgrService.Util.get().getLogosList(productId, (BaseFilterPagingLoadConfig) loadConfig,
				callback);
	}

	@Override
	public void deleteLogos(final int id, final AsyncCallback<Integer> callback) {
		AdminMgrService.Util.get().deleteLogos(productId, id, callback);
	}

}
