/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LandingActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.MaterialMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.MaterialMgrView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 素材控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-21 下午04:19:43
 */
public class MaterialMgrActivity extends AbstractActivity implements MaterialMgrPresenter {

	private final String productId = ApplicationContext.getCurrentProductId();

	private final MaterialMgrView view;

	public MaterialMgrActivity(final MaterialMgrView landingView) {
		super();
		view = landingView;
	}

	@Override
	public void goTo(final Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		AdminMgrService.Util.get().getMaterialList(productId, (BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"ID\",\"width\":100},"
				+ "{\"index\":\"name\",\"header\":\"素材名\",\"width\":120},"
				+ "{\"index\":\"type\",\"header\":\"素材类型\",\"width\":100},"
				+ "{\"index\":\"media\",\"header\":\"素材\",\"width\":60,\"type\":\"image\",\"downloadbean\":\"ad.download\"},"
				+ "{\"index\":\"width\",\"header\":\"宽度\",\"width\":60,\"type\":\"number\"},"
				+ "{\"index\":\"height\",\"header\":\"高度\",\"width\":60,\"type\":\"number\"},"
				+ "{\"index\":\"capacity\",\"header\":\"素材容量\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"note\",\"header\":\"素材说明\",\"width\":100},"
				+ "{\"index\":\"app\",\"header\":\"素材应用\",\"type\":\"link\",\"width\":80},"
				+ "{\"index\":\"addTime\",\"header\":\"添加日期\",\"width\":130,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm\"}"
				+ "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
	}

	@Override
	public void getMaterialType(final int type, final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().getType(productId, type, callback);
	}

	@Override
	public void chenkAddMaterialAuthority(AsyncCallback<Void> callback) {
		AdminMgrService.Util.get().chenkAddMaterialAuthority(callback);
	}

}
