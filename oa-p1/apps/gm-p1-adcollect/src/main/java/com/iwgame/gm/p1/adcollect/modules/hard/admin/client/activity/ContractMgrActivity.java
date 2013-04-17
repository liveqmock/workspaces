/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ContractMgrActivity.java
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
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.ContractMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.ContractMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 广告合同控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午05:22:06
 */
public class ContractMgrActivity extends AbstractActivity implements ContractMgrPresenter {

	private final String productId = ApplicationContext.getCurrentProductId();

	private final ContractMgrListView view;

	public ContractMgrActivity(final ContractMgrListView contractMgrListView) {
		super();
		view = contractMgrListView;
	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"type\",\"header\":\"类型\",\"width\":60},"
				+ "{\"index\":\"itemno\",\"header\":\"合同编号\",\"width\":100},"
				+ "{\"index\":\"signed\",\"header\":\"合同日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
				+ "{\"index\":\"name\",\"header\":\"合同名称\",\"width\":100},"
				+ "{\"index\":\"applyman\",\"header\":\"申请人\",\"width\":100},"
				+ "{\"index\":\"section\",\"header\":\"所属部门\",\"width\":100},"
				+ "{\"index\":\"level\",\"header\":\"合同等级\",\"width\":100},"
				+ "{\"index\":\"mediaName\",\"header\":\"媒体\",\"width\":100},"
				+ "{\"index\":\"total\",\"header\":\"合同金额\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"stdRegcost\",\"header\":\"注册成本\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"stdLogincost\",\"header\":\"登录成本\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"stdDiscount\",\"header\":\"折扣标准\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"payed\",\"header\":\"付款日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
				+ "{\"index\":\"adamt\",\"header\":\"广告数\",\"width\":100,\"type\":\"number\"},"
				+ "{\"index\":\"createTime\",\"header\":\"添加日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"updateTime\",\"header\":\"修改日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"info\",\"header\":\"详细信息\",\"width\":100,\"type\":\"button\"},"
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
		AdminMgrService.Util.get().getContractList(productId,
				(BaseFilterPagingLoadConfig) loadConfig, callback);
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
	public void getlogoList(final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().getLogoList(productId, callback);
	}

	@Override
	public void getPayableList(final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().getPayableList(productId, callback);
	}

	@Override
	public void checkItemno(final String itemno, final AsyncCallback<Boolean> callback) {
		AdminMgrService.Util.get().checkItemno(productId, itemno, callback);
	}

	@Override
	public void chenkAddContractAuthority(AsyncCallback<Void> callback) {
		AdminMgrService.Util.get().chenkAddContractAuthority(callback);
	}

	@Override
	public void chenkUpdateContractAuthority(AsyncCallback<Void> callback) {
		AdminMgrService.Util.get().chenkUpdateContractAuthority(callback);
	}

}
