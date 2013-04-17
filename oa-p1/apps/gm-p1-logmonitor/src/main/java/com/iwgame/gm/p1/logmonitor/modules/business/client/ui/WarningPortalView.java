/**      
 * WarningPortalView.java Create on 2012-9-10     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.ui.client.widgets.portals.AutoRefreshPortlet;
import com.iwgame.ui.client.widgets.portals.Portal;
import com.iwgame.ui.client.widgets.portals.PortalLayout;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;

/**
 * @ClassName: WarningPortalView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-10 上午11:00:46
 * @Version 1.0
 * 
 */
public class WarningPortalView extends Composite {

	private final VerticalPanel container;

	private final Portal portal;
	private final PortalLayout layout;
	// private final AutoRefreshPortlet<BizLogTable> portletIP;
	private final AutoRefreshPortlet<BizLogTable> portletSource;
	// private final AutoRefreshPortlet<BizLogTable> portletAccount;
	private final AutoRefreshPortlet<LoginLogTable> portletLogin;

	private final XFormPanel formPanel;
	private final FormLayout formlayout;
	private final SimpleSelector refreshSelector;

	public WarningPortalView() {

		container = new VerticalPanel();
		container.getElement().getStyle().setProperty("width", "100%");

		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		formlayout = new FormLayout();
		formlayout.setAutoWidth(false);
		formPanel.setLayout(formlayout);

		refreshSelector = new SimpleSelector("刷新频率") {

			@Override
			public void initItems() {
				addItem("每5秒刷新", "5");
				addItem("每30秒刷新", "30");
				addItem("每1分钟刷新", "60");
				addItem("每5分钟刷新", "300");
			}
		};
		refreshSelector.setValue("30");
		refreshSelector.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				int s = Integer.valueOf(refreshSelector.getValue());
				// portletIP.setRefreshMs(s);
				portletSource.setRefreshMs(s);
				// portletAccount.setRefreshMs(s);
				portletLogin.setRefreshMs(s);
			}
		});
		formlayout.add(refreshSelector);

		container.add(formPanel);

		portal = new Portal();
		layout = new PortalLayout();
		// layout.setColumns(2);
		portal.setLayout(layout);

		// portletIP = new
		// AutoRefreshPortlet<BizLogTable>("<a href='#logmonitor/business/ip'>按IP监控</a>",
		// true, false,
		// false);
		// portletIP.add(new BizLogTable("ip"));
		// layout.add(portletIP);

		portletSource = new AutoRefreshPortlet<BizLogTable>("<a href='#logmonitor/business/source'>按来源监控</a>", true,
				false, false);
		portletSource.add(new BizLogTable(portletSource));
		portletSource.setHeight(300);
		layout.add(portletSource);

		// portletAccount = new
		// AutoRefreshPortlet<BizLogTable>("<a href='#logmonitor/business/account'>按帐号监控</a>",
		// true,
		// false, false);
		// portletAccount.add(new BizLogTable("account"));
		// layout.add(portletAccount);

		portletLogin = new AutoRefreshPortlet<LoginLogTable>("<a href='#logmonitor/game/realtimelogin'>登录错误监控</a>",
				true, false, false);
		portletLogin.add(new LoginLogTable(portletLogin));
		layout.add(portletLogin);

		portal.layoutPortlets(true);

		container.add(portal);

		initWidget(container);

		Scheduler.get().scheduleDeferred(new ScheduledCommand() {

			@Override
			public void execute() {
				// portletIP.startRefresh();
				portletSource.startRefresh();
				// portletAccount.startRefresh();
				portletLogin.startRefresh();
			}
		});
	}

}
