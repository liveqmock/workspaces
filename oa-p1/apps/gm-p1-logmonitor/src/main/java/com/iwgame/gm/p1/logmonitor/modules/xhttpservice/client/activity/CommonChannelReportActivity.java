/****************************************************************
 *  文件名     ： TestActivity.java
 *  日期         :  2012-10-18
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.activity;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.presenter.CommonChannelActivity;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.ui.CommonChannelReportView;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc.CommonChannelService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xportal.common.client.ApplicationContext;

/** 
 * @ClassName:    TestActivity 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-18下午03:27:35
 * @Version:      1.0 
 */
public class CommonChannelReportActivity extends CommonChannelActivity {
	
	private final CommonChannelReportView view;
	
	public CommonChannelReportActivity(CommonChannelReportView view){
		this.view = view;
	}
	
	private static Map<String, DateTimeFormat> formats = new HashMap<String, DateTimeFormat>();
	static {
		formats.put("minute", DateTimeFormat.getFormat("yyyy-MM-dd HH:mm"));
		formats.put("hour", DateTimeFormat.getFormat("yyyy-MM-dd HH:00"));
		formats.put("day", DateTimeFormat.getFormat("yyyy-MM-dd"));
	}

	/* (non-Javadoc)
	 * @see com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client.ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
	 */
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		view.setSchemaJson("{\"table\":{\"key\":\"product_id\",\"columns\":["
				+ "{\"index\":\"dates\",\"header\":\"统计年月\",\"width\":145},"
				+ "{\"index\":\"product_id\",\"header\":\"产品ID\",\"visiable\":false},"
				+ "{\"index\":\"alias_name\",\"header\":\"日志来源\",\"width\":76},"
				+ "{\"index\":\"biz_name\",\"header\":\"业务类型\",\"width\":76},"
				+ "{\"index\":\"success\",\"header\":\"成功\",\"width\":40,\"type\":\"number\"},"
				+ "{\"index\":\"failure\",\"header\":\"失败\",\"width\":40,\"type\":\"number\"}"
				+ "]}}");
		view.setPresenter(this);
		panel.setWidget(view);
		view.getQueryParams();
		view.load();
	}

	/* (non-Javadoc)
	 * @see com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#loadData(java.lang.Object, com.iwgame.ui.core.client.AsyncCallbackEx)
	 */
	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		CommonChannelService.Util.get().getLogreport(ApplicationContext.getCurrentProductId(),(BaseFilterPagingLoadConfig) loadConfig, callback);
	}
}
