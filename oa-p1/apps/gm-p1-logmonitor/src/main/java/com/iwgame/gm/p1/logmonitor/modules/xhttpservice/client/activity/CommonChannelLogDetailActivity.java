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
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.ui.CommonChannelLogDetailView;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.shared.rpc.CommonChannelService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xportal.common.client.ApplicationContext;

/** 
 * @ClassName:    TestActivity 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-18下午03:27:35
 * @Version:      1.0 
 */
public class CommonChannelLogDetailActivity extends CommonChannelActivity {
	
	private final CommonChannelLogDetailView view;
	private final Map<String, String> queryParams;
	
	public CommonChannelLogDetailActivity(CommonChannelLogDetailView view, final Map<String, String> queryParams){
		this.view = view;
		this.queryParams = queryParams;
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
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"ID\",\"visiable\":false,\"type\":\"number\"},"
				+ "{\"index\":\"productid\",\"header\":\"产品ID\",\"visiable\":false},"
				+ "{\"index\":\"logintime\",\"header\":\"日志时间\",\"width\":150,\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\"},"
				+ "{\"index\":\"appname\",\"header\":\"日志来源\",\"width\":150},"
				+ "{\"index\":\"bizcode\",\"header\":\"业务类型\",\"width\":150},"
				+ "{\"index\":\"var1\",\"header\":\"关键字1\",\"width\":200},"
				+ "{\"index\":\"var2\",\"header\":\"关键字2\",\"width\":100},"
				+ "{\"index\":\"resourcebtn\",\"header\":\"请求信息\",\"width\":100,\"type\":\"button\"},"
				+ "{\"index\":\"logtype\",\"header\":\"状态\",\"width\":70,\"type\":\"number\"},"
				+ "{\"index\":\"lognote\",\"header\":\"描述\",\"width\":250}"
				 + "]}}");
		view.setPresenter(this);
		panel.setWidget(view);

		if ((queryParams != null) && !queryParams.isEmpty()) {
			String time = queryParams.remove("time");
			String type = queryParams.remove("type");
			DateWrapper dw = new DateWrapper(formats.get(type).parse(time));
			view.setStartDate(dw.asDate());
			view.setEndDate(dw.clearTime().addDays(1).asDate());
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				String key = entry.getKey();
				String value = entry.getValue();
				if ("account".equals(key)) {
					view.setUsername(value);
				}
				if ("source".equals(key)) {
					view.getGrid().getLoadConfig().set("requestSourceExt", value);
					if (value.contains("活动")) {
						value = "活动";
					}
					view.setSource(value);
				}
			}
		}
		view.getQueryParams();
		view.load();
		view.getGrid().getLoadConfig().remove("requestSourceExt");
	}

	/* (non-Javadoc)
	 * @see com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#loadData(java.lang.Object, com.iwgame.ui.core.client.AsyncCallbackEx)
	 */
	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
		CommonChannelService.Util.get().getAllLogData(ApplicationContext.getCurrentProductId(),(BaseFilterPagingLoadConfig) loadConfig, callback);
	}
}
