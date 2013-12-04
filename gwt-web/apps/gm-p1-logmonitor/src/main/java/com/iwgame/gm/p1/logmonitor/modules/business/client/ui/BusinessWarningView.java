/**      
 * BusinessLogMonitorView.java Create on 2012-9-3     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.xmvp.client.log.XMVPLogger;

/**
 * @ClassName: BusinessLogMonitorView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午03:45:15
 * @Version 1.0
 * 
 */
public class BusinessWarningView extends SchemaGridView {

	private static final XMVPLogger logger = new XMVPLogger(BusinessWarningView.class);

	private final XFormPanel formPanel;
	private final FormLayout layout;
	private final SimpleSelector refreshSelector;

	public BusinessWarningView() {
		super(100);
		getPanel().getGrid().setHasCheckBoxColumn(false);
		getPanel().setHeader("预警列表");

		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		formPanel.setLayout(layout);

		refreshSelector = new SimpleSelector("刷新频率") {

			@Override
			public void initItems() {
				addItem("手动刷新", "0");
				addItem("每5秒刷新", "5");
				addItem("每30秒刷新", "30");
				addItem("每1分钟刷新", "60");
				addItem("每5分钟刷新", "300");
			}
		};
		refreshSelector.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				int s = Integer.valueOf(refreshSelector.getValue());
				logger.info("设置刷新间隔为" + s + "秒");
				intervalLoad(s);
			}
		});
		layout.add(refreshSelector);

		ButtonToolItem btnRefresh = new ButtonToolItem("立即刷新");
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				reload();
			}
		});

		formPanel.addButton(btnRefresh);

		getPanel().getToolbar().addItem(formPanel);

	}

	private final int defaultInterval = 30;// 默认间隔时间，单位为秒,默认值为30秒

	/**
	 * 指示是否停止间隔刷新数据
	 */
	private boolean stop;

	/**
	 * 使用指定的间隔时间刷新数据
	 * 
	 * @param interval
	 *            间隔时间（单位：秒）
	 */
	public void intervalLoad(int interval) {

		if (interval == 0) {
			logger.fine("停止定时刷新数据，改成手动刷新");
			stop = true;
			reload();
			return;
		}

		stop = false;
		if (interval < 0) {
			interval = defaultInterval;
		}

		Scheduler.get().scheduleFixedPeriod(new RepeatingCommand() {

			@Override
			public boolean execute() {
				logger.fine("定时刷新数据");
				reload();
				return !stop;
			}
		}, interval * 1000);
	}

}
