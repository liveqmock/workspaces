/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： SheduleMgrActivity.java
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
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.SheduleMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.SheduleMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.SheduleDataBase;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述：广告排期控制类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 上午09:58:38
 */
public class SheduleMgrActivity extends AbstractActivity implements SheduleMgrPresenter {

	private final String productId = ApplicationContext.getCurrentProductId();

	private final SheduleMgrListView view;
	private final String sid;

	public SheduleMgrActivity(final SheduleMgrListView sheduleMgrListView, final String id) {
		super();
		this.view = sheduleMgrListView;
		this.sid = id;
	}

	@Override
	public void goTo(final Place place) {
		// TODO Auto-generated method stub

	}

	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"mediaName\",\"header\":\"所属媒体\",\"width\":120},"
				+ "{\"index\":\"posName\",\"header\":\"广告位名\",\"width\":120},"
				+ "{\"index\":\"adId\",\"header\":\"广告ID\",\"width\":100},"
				+ "{\"index\":\"materialShow\",\"header\":\"素材名称\",\"width\":100},"
				+ "{\"index\":\"contractItemno\",\"header\":\"所属合同\",\"width\":100},"
				+ "{\"index\":\"name\",\"header\":\"排期名称\",\"width\":120},"
				+ "{\"index\":\"startTime\",\"header\":\"开始时间\",\"width\":100,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
				+ "{\"index\":\"endTime\",\"header\":\"结束时间\",\"width\":100,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
				+ "{\"index\":\"id\",\"header\":\"排期码\",\"width\":200},"
				+ "{\"index\":\"groupName\",\"header\":\"所属广告组\",\"width\":120},"
				+ "{\"index\":\"rebate\",\"header\":\"折扣\",\"width\":120,\"type\":\"number\"},"
				+ "{\"index\":\"unitsName\",\"header\":\"实际售卖单位\",\"width\":100},"
				+ "{\"index\":\"generalPrice\",\"header\":\"普通日价格\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"specialPrice\",\"header\":\"特殊日价格\",\"width\":120,\"type\":\"currency\"},"
				+ "{\"index\":\"usedTypeShow\",\"header\":\"消费类型\",\"width\":80},"
				+ "{\"index\":\"distribuRatio\",\"header\":\"配送比\",\"width\":120,\"type\":\"number\"},"
				+ "{\"index\":\"typesShow\",\"header\":\"类型\",\"width\":80},"
				+ "{\"index\":\"adUrlShow\",\"header\":\"广告链接\",\"width\":80,\"type\":\"button\"},"
				+ "{\"index\":\"sourceShow\",\"header\":\"配送来源\",\"width\":80,\"type\":\"button\"},"
				+ "{\"index\":\"addTime\",\"header\":\"添加日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"updateTime\",\"header\":\"修改日期\",\"width\":140,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"logger\",\"header\":\"修改日志\",\"type\":\"button\"}" + "]}}");
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
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		BaseFilterPagingLoadConfig _loadConfig = (BaseFilterPagingLoadConfig) loadConfig;
		if (this.sid.length() > 0) {
			_loadConfig.set("sid", this.sid);
		}
		AdminMgrService.Util.get().getSheduleList(productId, _loadConfig, callback);
	}

	@Override
	public void autoMaterialId(final String query,
			final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().autoMaterialId(productId, query, callback);
	}

	@Override
	public void autoContractId(final String query,
			final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().autoContractIdAndName(productId, query, callback);
	}

	@Override
	public void autoGroupId(final String query, final AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().autoGroupIdAndName(productId, query, callback);
	}

	@Override
	public void addShedule(final SheduleDataBase newDateBase, final AsyncCallback<Integer> callback) {
		AdminMgrService.Util.get().addShedule(productId, newDateBase, callback);
	}

	@Override
	public void updateShedule(final SheduleDataBase newDateBase, final SheduleDataBase oldDateBase,
			final AsyncCallback<Integer> callback) {
		AdminMgrService.Util.get().updateShedule(productId, newDateBase, oldDateBase, callback);
	}

	@Override
	public void checkSheduleId(final String id, final AsyncCallback<Boolean> callback) {
		AdminMgrService.Util.get().checkSheduleId(productId, id, callback);
	}

	@Override
	public void checkMaterialId(String id, AsyncCallback<String> callback) {
		AdminMgrService.Util.get().checkMaterialId(productId, id, callback);
	}

	@Override
	public void checkGroupId(String id, AsyncCallback<String> callback) {
		AdminMgrService.Util.get().checkGroupId(productId, id, callback);
	}

	@Override
	public void checkContractId(String id, AsyncCallback<String> callback) {
		AdminMgrService.Util.get().checkContractId(productId, id, callback);
	}

	@Override
	public void getPosition(String mediaId, AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().getPositionListDorp(productId, mediaId, callback);
	}

	@Override
	public void autoAdUrl(String query, AsyncCallback<List<DropDownListData>> callback) {
		AdminMgrService.Util.get().getAutoAdUrlListDorp(productId, query, callback);
	}

	@Override
	public void checkAdUrl(String id, AsyncCallback<String> callback) {
		AdminMgrService.Util.get().checkAdUrl(productId, id, callback);
	}

	@Override
	public void getPosNameById(String id, AsyncCallback<String> callback) {
		AdminMgrService.Util.get().getPosNameById(productId,id,callback);
	}

	@Override
	public void getUnitsIsNetBar(AsyncCallback<List<DropDownListData>> callback) {
		    AdminMgrService.Util.get().getUnitsIsNetBar(productId, callback);
	}

}
