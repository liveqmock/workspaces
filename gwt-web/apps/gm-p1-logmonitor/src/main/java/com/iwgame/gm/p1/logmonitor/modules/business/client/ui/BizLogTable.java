/**      
 * BizLogTable.java Create on 2012-9-10     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.client.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.Style.FontWeight;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlexTable;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.model.SourceWarningViewBean;
import com.iwgame.gm.p1.logmonitor.modules.business.shared.rpc.BusinessService;
import com.iwgame.ui.client.widgets.portals.AutoRefreshPortlet;
import com.iwgame.ui.client.widgets.portals.AutoRefreshPortlet.HasRefresh;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: BizLogTable
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-10 上午11:31:01
 * @Version 1.0
 * 
 */
public class BizLogTable extends FlexTable implements HasRefresh {

	private static final Map<String, String> sourceTitle;

	static {
		sourceTitle = new HashMap<String, String>();
		sourceTitle.put("ip", "独立访问IP");
		sourceTitle.put("source", "来源模块");
		sourceTitle.put("account", "独立帐号");
	}

	private final AutoRefreshPortlet<BizLogTable> portlet;

	/**
	 * 
	 */
	public BizLogTable(final AutoRefreshPortlet<BizLogTable> portlet) {

		this.portlet = portlet;

		getElement().getStyle().setProperty("width", "100%");

		setHTML(0, 0, "来源");
		getCellFormatter().getElement(0, 0).getStyle().setWidth(10, Unit.PCT);
		setHTML(0, 1, "注册（天/小时(成功+失败)/阈值）");
		getCellFormatter().getElement(0, 1).getStyle().setWidth(20, Unit.PCT);
		setHTML(0, 2, "登录（天/小时(成功+失败)/阈值）");
		getCellFormatter().getElement(0, 2).getStyle().setWidth(20, Unit.PCT);
		setHTML(0, 3, "账号检测（天/小时(成功+失败)/阈值）");
		getCellFormatter().getElement(0, 3).getStyle().setWidth(20, Unit.PCT);

		getRowFormatter().getElement(0).getStyle().setBackgroundColor("#C0D5F0");
		getRowFormatter().getElement(0).getStyle().setColor("black");
		getRowFormatter().getElement(0).getStyle().setFontWeight(FontWeight.BOLD);
		getRowFormatter().getElement(0).getStyle().setProperty("textAlign", "center");
	}

	@Override
	public void refresh() {
		clearData();
		BusinessService.Util.get().getWaringData(ApplicationContext.getCurrentProductId(), new Date(),
				new AsyncCallbackEx<List<SourceWarningViewBean>>() {

					@Override
					public void onSuccess(final List<SourceWarningViewBean> result) {
						CellFormatter cf = getCellFormatter();
						for (int index = 0; index < result.size(); index++) {
							SourceWarningViewBean bean = result.get(index);
							setHTML(index + 1, 0, bean.getName());
							setHTML(index + 1, 1, bean.getRegistDayTotal() + " / " + bean.getRegistHourTotal() + "("
									+ bean.getRegistHourSuccess() + "+" + bean.getRegistHourFailed() + ")" + " / "
									+ bean.getThreshold());
							if (bean.getRegistHourFailed() > bean.getThreshold()) {
								cf.addStyleName(index + 1, 1, "logmonitor-warning");
							}
							setHTML(index + 1,
									2,
									bean.getLoginDayTotal() + " / " + bean.getLoginHourTotal() + "("
											+ bean.getLoginHourSuccess() + "+" + bean.getLoginHourFailed() + ")"
											+ " / " + bean.getThreshold());
							if (bean.getLoginHourFailed() > bean.getThreshold()) {
								cf.addStyleName(index + 1, 2, "logmonitor-warning");
							}
							setHTML(index + 1, 3, bean.getAccountDayTotal() + " / " + bean.getAccountHourTotal() + "("
									+ bean.getAccountHourSuccess() + "+" + bean.getAccountHourFailed() + ")" + " / "
									+ bean.getThreshold());
							if (bean.getAccountHourFailed() > bean.getThreshold()) {
								cf.addStyleName(index + 1, 3, "logmonitor-warning");
							}
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
