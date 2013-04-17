/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： GroupMgrActivity.java
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
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.GroupMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.GroupMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 广告组控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-29 下午03:13:33
 */
public class GroupMgrActivity extends AbstractActivity implements GroupMgrPresenter {

	private final String productId = ApplicationContext.getCurrentProductId();

	private GroupMgrListView view;

	public GroupMgrActivity(final GroupMgrListView groupMgrListView) {
		super();
		view = groupMgrListView;
	}

	@Override
	public void goTo(final Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"sheduleId\",\"columns\":["
				+ "{\"index\":\"mediaName\",\"header\":\"媒体名称\",\"width\":100},"
				+ "{\"index\":\"idShow\",\"header\":\"广告组ID\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"name\",\"header\":\"广告组名称\",\"width\":120},"
				+ "{\"index\":\"budgetPrice\",\"header\":\"预计费用\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"sheduleId\",\"header\":\"排期码\",\"width\":150},"
				+ "{\"index\":\"sheduleName\",\"header\":\"广告名称\",\"width\":120},"
				+ "{\"index\":\"usedTypeShow\",\"header\":\"消费类型\",\"width\":80},"
				+ "{\"index\":\"sumPrice\",\"header\":\"总计金额\",\"width\":100,\"type\":\"currency\"},"
				+ "{\"index\":\"adUrlShow\",\"header\":\"到达页\",\"width\":80,\"type\":\"button\"},"
				+ "{\"index\":\"materialShow\",\"header\":\"素材\",\"width\":100},"
				+ "{\"index\":\"addTime\",\"header\":\"添加日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"}"
				// +
				// "{\"index\":\"updateTime\",\"header\":\"修改日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				// +
				// "{\"index\":\"logger\",\"header\":\"修改日志\",\"type\":\"button\"}"
				+ "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
	}

	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		BaseFilterPagingLoadConfig _loadConfig = (BaseFilterPagingLoadConfig) loadConfig;
		if (null == _loadConfig.get("media")) {
			_loadConfig.set("media", "");
		}
		if (null == _loadConfig.get("name")) {
			_loadConfig.set("name", "");
		}
		if (null == _loadConfig.get("id")) {
			_loadConfig.set("id", "");
		}
		if (_loadConfig.get("name").equals("") && _loadConfig.get("id").equals("")) {
			_loadConfig.set("type", "all");
		}
		AdminMgrService.Util.get().getGroupList(productId, _loadConfig, callback);
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
	public void checkGroupName(final String name, final AsyncCallback<Boolean> callback) {
		AdminMgrService.Util.get().checkGroupName(productId, name, callback);
	}

	@Override
	public void addGroup(final String name, final String mediaId,
			final AsyncCallback<Integer> callback) {
		AdminMgrService.Util.get().addGroup(productId, name, mediaId, callback);
	}
}
