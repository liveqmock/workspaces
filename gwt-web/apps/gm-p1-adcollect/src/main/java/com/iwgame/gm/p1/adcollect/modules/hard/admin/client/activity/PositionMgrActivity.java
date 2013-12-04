/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： PositionMgrActivity.java
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
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.PositionMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.PositionMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 广告位控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-8 下午12:22:34
 */
public class PositionMgrActivity extends AbstractActivity implements PositionMgrPresenter {

	private final String productId = ApplicationContext.getCurrentProductId();

	private final PositionMgrListView view;

	public PositionMgrActivity(final PositionMgrListView positionMgrListView) {
		super();
		view = positionMgrListView;
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"mediaName\",\"header\":\"所属媒体\",\"width\":120},"
				+ "{\"index\":\"name\",\"header\":\"广告位名\",\"width\":120},"
				+ "{\"index\":\"id\",\"header\":\"广告位ID\",\"width\":100},"
				+ "{\"index\":\"typeShow\",\"header\":\"广告位类型\",\"width\":110},"
				+ "{\"index\":\"adId\",\"header\":\"对应广告ID\",\"width\":100},"
				+ "{\"index\":\"media\",\"header\":\"广告位图列\",\"width\":100,\"type\":\"image\",\"downloadbean\":\"ad.download\"},"
				+ "{\"index\":\"unitsName\",\"header\":\"售卖单位\",\"width\":100},"
				+ "{\"index\":\"generalPrice\",\"header\":\"单位原价一般日\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"specialPrice\",\"header\":\"单位原价特殊日\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"format\",\"header\":\"广告位规格\",\"width\":120},"
				+ "{\"index\":\"explain\",\"header\":\"广告位说明\",\"width\":120},"
				+ "{\"index\":\"remark\",\"header\":\"备注\",\"width\":120},"
				+ "{\"index\":\"addTime\",\"header\":\"添加日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"updateTime\",\"header\":\"修改日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"logger\",\"header\":\"修改日志\",\"type\":\"button\"}" + "]}}");

		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
	}

	@Override
	public void goTo(final Place place) {

	}

	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		AdminMgrService.Util.get().getPositionList(productId, (BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void getMediaType(final int type, final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().getType(productId, type, callback);
	}

	@Override
	public void getMedia(final int type, final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().getMedia(productId, type, callback);
	}

	@Override
	public void checkPositionName(final String name, final AsyncCallback<Boolean> callback) {
		AdminMgrService.Util.get().checkPositionName(productId, name, callback);
	}

	@Override
	public void chenkAddPosAuthority(AsyncCallback<Void> callback) {
		AdminMgrService.Util.get().chenkAddPosAuthority(callback);
	}

	@Override
	public void chenkUpdatePosAuthority(AsyncCallback<Void> callback) {
		AdminMgrService.Util.get().chenkUpdatePosAuthority(callback);
	}

	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.PositionMgrPresenter#getUnitsIsNetBar(com.google.gwt.user.client.rpc.AsyncCallback)
	 */
	@Override
	public void getUnitsIsNetBar(
		AsyncCallback<List<DropDownListData>> callback) {
	    AdminMgrService.Util.get().getUnitsIsNetBar(productId, callback);
	}

}
