/**      
 * RealtimeLoginView.java Create on 2012-8-31     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.game.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.log.XMVPLogger;
import com.iwgame.xmvp.client.utils.DateWrapper;

/**
 * @ClassName: RealtimeLoginView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-8-31 下午03:05:01
 * @Version 1.0
 * 
 */
public class HistoryLoginView extends SchemaGridView {

	private static final XMVPLogger logger = new XMVPLogger(HistoryLoginView.class);

	private final XFormPanel formPanel;
	private final FormLayout layout;

	private final TextField zoneFilter;
	private final NumberField errTimesFilter;
	private final DateRangeField dates;

	public HistoryLoginView() {
		super(20);
		getPanel().getGrid().setHasCheckBoxColumn(false);
		getPanel().setHeader("历史登录次数查询");

		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		layout.setColumn(2);
		formPanel.setLayout(layout);

		zoneFilter = new TextField("区组", "zone");
		layout.add(zoneFilter);

		errTimesFilter = new NumberField("登录错误次数", "errTimes");
		errTimesFilter.setAlignment(TextAlignment.LEFT);
		errTimesFilter.setEmptyText("输入数字，为空则显示所有数据");
		layout.add(2, errTimesFilter);

		dates = new DateRangeField("登录时间");
		layout.add(dates);

		ButtonToolItem btnRefresh = new ButtonToolItem("查询");
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				getQueryParams();
				reload();
			}
		});

		formPanel.addButton(btnRefresh);

		getPanel().getToolbar().addItem(formPanel);
	}

	/**
	 * 
	 */
	protected void getQueryParams() {
		getGrid().getLoadConfig().set("zoneId", zoneFilter.getValue());
		getGrid().getLoadConfig().set("errorTimes", errTimesFilter.getValue());
		if (dates.getStartValue() != null) {
			getGrid().getLoadConfig()
					.set("startDate", DateWrapper.format(dates.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
		}
		if (dates.getEndValue() != null) {
			DateWrapper dw = new DateWrapper(dates.getEndValue());
			dw = dw.clearTime().addHours(23).addMinutes(59).addSeconds(59);
			getGrid().getLoadConfig().set("endDate", DateWrapper.format(dw.asDate(), "yyyy-MM-dd HH:mm:ss"));
		}

		logger.info("查询参数为 【大区=" + zoneFilter.getValue() + "】【错误次数=" + errTimesFilter.getValue() + "】");
	}

}
