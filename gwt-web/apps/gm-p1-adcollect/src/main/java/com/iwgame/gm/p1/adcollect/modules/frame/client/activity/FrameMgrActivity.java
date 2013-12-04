/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： FrameMgrActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.client.activity;

import java.util.List;
import java.util.Map;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.frame.client.presenter.FrameMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.frame.client.ui.FrameMgrView;
import com.iwgame.gm.p1.adcollect.modules.frame.shared.rpc.FrameMgrService;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.FrameDataBase;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @Description: 框架管理控制类
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-13 下午12:31:36
 */
public class FrameMgrActivity extends AbstractActivity implements FrameMgrPresenter {

	private final String productId = ApplicationContext.getCurrentProductId();
	
	private FrameMgrView view;
	
	public FrameMgrActivity(FrameMgrView mgrView){
		super();
		this.view = mgrView;
	}
	
	@Override
	public void goTo(Place place) {
		
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"mediaName\",\"header\":\"媒体\",\"width\":100},"
				+ "{\"index\":\"name\",\"header\":\"框架名称\",\"width\":150},"
				+ "{\"index\":\"amount\",\"header\":\"框架金额\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"rebate\",\"header\":\"框架折扣\",\"width\":120,\"type\":\"number\",\"format\":\"#.####\"},"
				+ "{\"index\":\"securityDeposit\",\"header\":\"框架保证金\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"startTime\",\"header\":\"开始时间\",\"width\":120,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
				+ "{\"index\":\"endTime\",\"header\":\"结束时间\",\"width\":120,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
				+ "{\"index\":\"balance\",\"header\":\"框架余额\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"info\",\"header\":\"详细信息\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"logger\",\"header\":\"修改日志\",\"type\":\"button\"}" 
				+ "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.load(true);
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		FrameMgrService.Util.get().getFrameList(productId, (BaseFilterPagingLoadConfig) loadConfig, callback);
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
	public void addFrame(FrameDataBase newBase, AsyncCallback<Integer> callback) {
		FrameMgrService.Util.get().addFrame(productId, newBase, callback);
	}

	@Override
	public void checkFrameName(String name, AsyncCallback<Boolean> callback) {
		FrameMgrService.Util.get().checkFrameName(productId, name, callback);
	}

	@Override
	public void checkFrameTime(Map<String, Object> parameter, AsyncCallback<Integer> callback) {
		FrameMgrService.Util.get().checkFrameTime(productId, parameter, callback);
	}

	@Override
	public void updateFrame(FrameDataBase newBase, AsyncCallback<Integer> callback) {
		FrameMgrService.Util.get().updateFrame(productId, newBase, callback);
	}

	@Override
	public void delFrameByIds(String ids, AsyncCallback<Integer> callback) {
		FrameMgrService.Util.get().delFrameByIds(productId, ids, callback);
	}

}
