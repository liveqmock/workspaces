/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MediaMgrActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity;

import java.util.List;
import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.MediaMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.MediaMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 媒体控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-26 下午05:04:18
 */
public class MediaMgrActivity extends AbstractActivity implements MediaMgrPresenter {

	private final String productId = ApplicationContext.getCurrentProductId();

	private final MediaMgrListView view;

	public MediaMgrActivity(final MediaMgrListView mediaMgrListView) {
		super();
		view = mediaMgrListView;
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"typeStr\",\"header\":\"媒体分类\",\"width\":100},"
				+ "{\"index\":\"name\",\"header\":\"媒体名称\",\"width\":120},"
				+ "{\"index\":\"id\",\"header\":\"媒体ID\",\"width\":100},"
				+ "{\"index\":\"sortStr\",\"header\":\"媒体类型\",\"width\":100},"
				+ "{\"index\":\"subclass\",\"header\":\"媒体子类\",\"width\":100},"
				+ "{\"index\":\"addsShow\",\"header\":\"地址\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"data1\",\"header\":\"数据1\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"data2Btn\",\"header\":\"数据2\",\"width\":80,\"type\":\"button\"},"
				+ "{\"index\":\"data3\",\"header\":\"数据3\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"data4\",\"header\":\"数据4\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"count\",\"header\":\"广告位数量\",\"width\":90,\"type\":\"number\"},"
				+ "{\"index\":\"contractCount\",\"header\":\"合同数量\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"addTime\",\"header\":\"添加日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"updateTime\",\"header\":\"修改日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"logger\",\"header\":\"修改日志\",\"type\":\"button\"}" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
	}

	@Override
	public void goTo(final Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		AdminMgrService.Util.get().getMediaList(productId, (BaseFilterPagingLoadConfig) loadConfig, callback);
	}

	@Override
	public void getType(final int type, final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().getType(productId, type, callback);
	}

	@Override
	public void addMedia(final Map<String, Object> parameter, final AsyncCallback<Integer> callback) {
		AdminMgrService.Util.get().addMedia(productId, parameter, callback);
	}

	@Override
	public void updateMedia(final Map<String, Object> parameter, final Map<String, Object> oldBase,
			final AsyncCallback<Integer> callback) {
		AdminMgrService.Util.get().updateMedia(productId, parameter, oldBase, callback);
	}

}
