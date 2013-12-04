/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： UseMgrActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.client.activity;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.frame.client.presenter.UseMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.frame.client.ui.UseMgrView;
import com.iwgame.gm.p1.adcollect.modules.frame.shared.rpc.FrameMgrService;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.UseFrameDataBase;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @Description: 使用框架控制类
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-17 下午12:05:18
 */
public class UseMgrActivity extends AbstractActivity implements UseMgrPresenter {

	private final String productId = ApplicationContext.getCurrentProductId();

	private UseMgrView view;
	private String name;
	private  boolean firstLoading = true;  // 首次访问

	public UseMgrActivity(final String n, UseMgrView useMgrView) {
		super();
		this.name = n;
		this.view = useMgrView;
	}

	@Override
	public void goTo(Place place) {

	}

	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"mediaName\",\"header\":\"媒体\",\"width\":100},"
				+ "{\"index\":\"frameName\",\"header\":\"框架名称\",\"width\":150},"
				+ "{\"index\":\"updateTime\",\"header\":\"变更日期\",\"width\":120,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
				+ "{\"index\":\"changes\",\"header\":\"变更情况\",\"width\":120},"
				+ "{\"index\":\"changeAmount\",\"header\":\"变更金额\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"balance\",\"header\":\"框架余额\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"contractItmo\",\"header\":\"关联合同\",\"width\":150},"
				+ "{\"index\":\"infoBtn\",\"header\":\"使用详情\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"sheduleBtn\",\"header\":\"效果追踪\",\"type\":\"button\"}" + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
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
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		BaseFilterPagingLoadConfig _loadConfig = (BaseFilterPagingLoadConfig) loadConfig;
		if (firstLoading){
			_loadConfig.set("name", this.name);
		}
		FrameMgrService.Util.get().getUseFrameList(productId, _loadConfig, callback);
		firstLoading = false;
	}

	@Override
	public void delUseFrameByIds(String ids, AsyncCallback<Integer> callback) {
		FrameMgrService.Util.get().delUseFrameByIds(productId, ids, callback);
	}

	@Override
	public void getFrameName(String mediaId, AsyncCallback<List<DropDownListData>> callback) {
		FrameMgrService.Util.get().getFrameName(productId, mediaId, callback);
	}

	@Override
	public void checkContractId(String id, AsyncCallback<String> callback) {
		AdminMgrService.Util.get().checkContractId(productId, id, callback);
	}

	@Override
	public void autoContractId(final String query, final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().autoContractIdAndName(productId, query, callback);
	}

	@Override
	public void addUseFrame(UseFrameDataBase useframe, AsyncCallback<Integer> callback) {
		FrameMgrService.Util.get().addUseFrame(productId, useframe, callback);
	}

}
