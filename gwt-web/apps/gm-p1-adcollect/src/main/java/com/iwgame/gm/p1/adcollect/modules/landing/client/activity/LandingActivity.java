/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LandingActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.landing.client.activity;

import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.landing.client.presenter.LandingPresenter;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.LandingView;
import com.iwgame.gm.p1.adcollect.modules.landing.shared.rpc.LandingService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 到达页控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-21 下午04:19:43
 */
public class LandingActivity extends AbstractActivity implements LandingPresenter {

	private final LandingView view;

	private final String productId = ApplicationContext.getCurrentProductId();

	public LandingActivity(LandingView landingView) {
		super();
		view = landingView;
	}

	@Override
	public void goTo(Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		LandingService.Util.get().getLandingList(productId, (BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"ID\",\"type\":\"number\",\"width\":100},"// ,\"type\":\"date\",\"format\":\"yyyy-MM-dd
																							// HH:mm\"
				+ "{\"index\":\"name\",\"header\":\"名称\",\"width\":120},"
				+ "{\"index\":\"adUrlShow\",\"header\":\"链接\",\"width\":80,\"type\":\"button\"},"																			
//				+ "{\"index\":\"link\",\"header\":\"\",\"width\":280},"
				+ "{\"index\":\"media\",\"header\":\"当前素材\",\"width\":90,\"type\":\"image\",\"downloadbean\":\"ad.download\"},"
				+ "{\"index\":\"statsShow\",\"header\":\"状态\",\"width\":60},"
				+ "{\"index\":\"note\",\"header\":\"说明\",\"width\":100},"
				+ "{\"index\":\"addTime\",\"header\":\"添加时间\",\"width\":130,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm\"},"
				+ "{\"index\":\"updateTime\",\"header\":\"修改时间\",\"width\":130,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm\"},"
				+ "{\"index\":\"logger\",\"header\":\"修改日志\",\"type\":\"button\"}" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
	}

	@Override
	public void addLanding(Map<String, Object> parameter, AsyncCallback<Integer> callback) {
		LandingService.Util.get().addLanding(productId, parameter, callback);
	}

	@Override
	public void updateLanding(Map<String, Object> parameter, Map<String, Object> lodBase,
			AsyncCallback<Integer> callback) {
		LandingService.Util.get().updateLanding(productId, parameter, lodBase, callback);
	}

}
