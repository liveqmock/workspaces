/**      
 * RealtimeLoginView.java Create on 2012-8-31     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.game.client.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.log.XMVPLogger;

/**
 * @ClassName: RealtimeLoginView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-31 下午03:05:01
 * @Version 1.0
 * 
 */
public class RealtimeLoginView extends SchemaGridView {

	private static final XMVPLogger logger = new XMVPLogger(RealtimeLoginView.class);

	private final XFormPanel formPanel;
	private final FormLayout layout;
	private final SimpleSelector selector;

	private final TextField zoneFilter;
	private final NumberField errTimesFilter;

	public void setInterval(final String interval) {
		selector.setValue(interval);
		int s = Integer.valueOf(selector.getValue());
		intervalLoad(s);
	}

	public RealtimeLoginView() {
		super(100);
		getPanel().getGrid().setHasCheckBoxColumn(false);
		getPanel().setHeader("实时登录次数监控");

		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		layout.setColumn(3);
		formPanel.setLayout(layout);

		zoneFilter = new TextField("区组", "zone");
		layout.add(zoneFilter);

		errTimesFilter = new NumberField("登录错误次数", "errTimes");
		errTimesFilter.setAlignment(TextAlignment.LEFT);
		errTimesFilter.setEmptyText("输入数字，为空则显示所有数据");
		layout.add(2, errTimesFilter);

		selector = new SimpleSelector("刷新频率") {

			@Override
			public void initItems() {
				addItem("手动刷新", "0");
				addItem("每30秒刷新", "30");
				addItem("每1分钟刷新", "60");
				addItem("每5分钟刷新", "300");
				addItem("每10分钟刷新", "600");
			}
		};
		selector.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				int s = Integer.valueOf(selector.getValue());
				logger.info("设置刷新间隔为" + s + "秒");
				getQueryParams();
				intervalLoad(s);
			}
		});
		layout.add(3, selector);

		ButtonToolItem btnRefresh = new ButtonToolItem("立即刷新");
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				getGrid().getLoadConfig().set("zone", zoneFilter.getValue());
				getGrid().getLoadConfig().set("errTimes", errTimesFilter.getValue());
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
			getQueryParams();
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
				getQueryParams();
				reload();
				return !stop;
			}
		}, interval * 1000);
	}

	/**
	 * 
	 */
	protected void getQueryParams() {
		getGrid().getLoadConfig().set("zone", zoneFilter.getValue());
		getGrid().getLoadConfig().set("errTimes", errTimesFilter.getValue());
		logger.info("查询参数为 【大区=" + zoneFilter.getValue() + "】【错误次数=" + errTimesFilter.getValue() + "】");
	}

}
