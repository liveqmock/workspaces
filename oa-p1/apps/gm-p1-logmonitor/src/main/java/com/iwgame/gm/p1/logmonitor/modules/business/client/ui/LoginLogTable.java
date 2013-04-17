/**      
 * BizLogTable.java Create on 2012-9-10     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.client.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlexTable;
import com.iwgame.gm.p1.logmonitor.modules.game.shared.model.LoginModelBean;
import com.iwgame.gm.p1.logmonitor.modules.game.shared.rpc.LoginService;
import com.iwgame.ui.client.widgets.portals.AutoRefreshPortlet;
import com.iwgame.ui.client.widgets.portals.AutoRefreshPortlet.HasRefresh;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: BizLogTable
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-10 上午11:31:01
 * @Version 1.0
 * 
 */
public class LoginLogTable extends FlexTable implements HasRefresh {

	private static final Map<String, String> sourceTitle;

	static {
		sourceTitle = new HashMap<String, String>();
		sourceTitle.put("ip", "独立访问IP");
		sourceTitle.put("source", "来源模块");
		sourceTitle.put("account", "独立帐号");
	}

	private final AutoRefreshPortlet<LoginLogTable> portlet;

	/**
	 * 
	 */
	public LoginLogTable(final AutoRefreshPortlet<LoginLogTable> portlet) {

		this.portlet = portlet;

		getElement().getStyle().setProperty("width", "100%");

		setHTML(0, 0, "登录时间");
		getCellFormatter().getElement(0, 0).getStyle().setWidth(20, Unit.PCT);
		setHTML(0, 1, "大区");
		getCellFormatter().getElement(0, 1).getStyle().setWidth(20, Unit.PCT);
		setHTML(0, 2, "帐号");
		getCellFormatter().getElement(0, 2).getStyle().setWidth(20, Unit.PCT);
		setHTML(0, 3, "错误次数");
		getCellFormatter().getElement(0, 3).getStyle().setWidth(10, Unit.PCT);
		setHTML(0, 4, "登录IP");
		getCellFormatter().getElement(0, 4).getStyle().setWidth(10, Unit.PCT);
		setHTML(0, 5, "登录MAC");

		getRowFormatter().getElement(0).getStyle().setBackgroundColor("#C0D5F0");
		getRowFormatter().getElement(0).getStyle().setColor("black");
		getRowFormatter().getElement(0).getStyle().setFontWeight(FontWeight.BOLD);
		getRowFormatter().getElement(0).getStyle().setProperty("textAlign", "center");
	}

	@Override
	public void refresh() {
		clearData();
		LoginService.Util.get().getWarningData(ApplicationContext.getCurrentProductId(),
				new AsyncCallbackEx<List<LoginModelBean>>() {

					@Override
					public void onSuccess(final List<LoginModelBean> result) {

						int i = 1;
						for (LoginModelBean bean : result) {
							setHTML(i, 0, DateWrapper.format(bean.getLoginTime(), "yyyy-MM-dd HH:mm:ss"));
							setHTML(i, 1, bean.getZoneId());
							setHTML(i, 2, bean.getUsername());
							setHTML(i, 3, Integer.toString(bean.getErrorTimes()));
							setHTML(i, 4, bean.getLoginIp());
							setHTML(i, 5, bean.getLoginMac());
							i++;
						}
					}

					/*
					 * (non-Javadoc)
					 * 
					 * @see
					 * com.iwgame.ui.core.client.AsyncCallbackEx#onFailure(java
					 * .lang.Throwable)
					 */
					@Override
					public void onFailure(final Throwable cause) {
						portlet.stopRefresh();
						MessageBox.error("与服务器通信异常，请检查网络环境");
					}
				});
	}

	public void clearData() {
		for (int i = getRowCount() - 1; i > 0; i--) {
			removeRow(i);
		}
	}

	@Override
	public void removeRow(final int row) {
		super.removeRow(row);
	}
}
