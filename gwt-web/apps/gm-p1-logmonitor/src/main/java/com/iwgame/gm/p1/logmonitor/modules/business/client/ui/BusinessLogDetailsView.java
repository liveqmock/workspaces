/**      
 * BusinessLogMonitorView.java Create on 2012-9-3     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.client.ui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.log.XMVPLogger;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * @ClassName: BusinessLogDetailsView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午03:45:15
 * @Version 1.0
 * 
 */
public class BusinessLogDetailsView extends SchemaGridView {

	private static final XMVPLogger logger = new XMVPLogger(BusinessLogDetailsView.class);

	private final XFormPanel formPanel;
	private final FormLayout layout;
	private final SimpleSelector typeSelector;
	private final SimpleSelector sourceSelector;
	private final SimpleSelector statusSelector;
	private final TextField ipField;
	private final TextField usernameField;

	private final DateRangeField dates;

	public BusinessLogDetailsView() {
		super(100);
		getPanel().getGrid().setHasCheckBoxColumn(false);
		getPanel().setHeader("监控日志明细");
		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		layout.setColumn(3);
		formPanel.setLayout(layout);

		dates = new DateRangeField(true, "查询时间");
		layout.add(dates);

		statusSelector = new SimpleSelector("状态") {

			@Override
			public void initItems() {
				addItem("— 所有 —", "-1");
				addItem("成功", "0");
				addItem("失败", "1");
			}
		};
		layout.add(2, statusSelector);

		getPanel().setColumnRenderer("requestStatus", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				int s = object.<Double> get(config.getIndex()).intValue();
				if (s == 0) {
					sb.append(SafeHtmlUtils.fromString("成功"));
				} else {
					sb.append(SafeHtmlUtils.fromString("失败"));
				}
			}
		});

		sourceSelector = new SimpleSelector("业务来源") {

			@Override
			public void initItems() {
				addItem("— 所有 —", "-1");
				addItem("会员中心", "会员中心");
				addItem("活动", "活动");
				addItem("社区", "社区");
				addItem("客服", "客服");
				addItem("投诉", "投诉");
				addItem("密保", "密保");
				addItem("安全", "安全");
			}
		};
		sourceSelector.setValue("minute");
		layout.add(3, sourceSelector);

		typeSelector = new SimpleSelector("业务类型") {

			@Override
			public void initItems() {
				addItem("— 所有 —", "-1");
				addItem("登录", "登录");
				addItem("注册", "注册");
				addItem("账号检测", "账号检测");
			}
		};
		layout.add(typeSelector);

		ipField = new TextField("登录IP", "ip");
		layout.add(2, ipField);

		usernameField = new TextField("用户帐号", "username");
		layout.add(3, usernameField);

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

	public void getQueryParams() {
		if (!sourceSelector.getValue().equals("-1")) {
			getGrid().getLoadConfig().set("requestSource", sourceSelector.getValue());
		} else {
			getGrid().getLoadConfig().remove("requestSource");
		}
		if (!typeSelector.getValue().equals("-1")) {
			getGrid().getLoadConfig().set("requestType", typeSelector.getValue());
		} else {
			getGrid().getLoadConfig().remove("requestType");
		}
		if (!statusSelector.getValue().equals("-1")) {
			getGrid().getLoadConfig().set("requestStatus", statusSelector.getValue());
		} else {
			getGrid().getLoadConfig().remove("requestStatus");
		}
		if (dates.getStartValue() != null) {
			getGrid().getLoadConfig()
					.set("startDate", DateWrapper.format(dates.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
		} else {
			getGrid().getLoadConfig().remove("startDate");
		}
		if (dates.getEndValue() != null) {
			getGrid().getLoadConfig().set("endDate", DateWrapper.format(dates.getEndValue(), "yyyy-MM-dd HH:mm:ss"));
		} else {
			getGrid().getLoadConfig().remove("endDate");
		}
		if (!"".equals(ipField.getValue())) {
			getGrid().getLoadConfig().set("requestIp", ipField.getValue());
		} else {
			getGrid().getLoadConfig().remove("requestIp");
		}
		if (!"".equals(usernameField.getValue())) {
			getGrid().getLoadConfig().set("requestUsername", usernameField.getValue());
		} else {
			getGrid().getLoadConfig().remove("requestUsername");
		}
		logger.info("查询参数为 【状态=" + statusSelector.getValue() + "】【业务来源=" + sourceSelector.getValue() + "】【业务类型="
				+ typeSelector.getValue() + "】【IP=" + ipField.getValue() + "】【 用户帐号=" + usernameField.getValue() + "】");
	}

	public void setUsername(final String username) {
		usernameField.setValue(username);
	}

	public void setIp(final String ip) {
		ipField.setValue(ip);
	}

	public void setSource(final String source) {
		sourceSelector.setValue(source);
	}

	public void setStartDate(final Date date) {
		dates.setStartValue(date);
	}

	public void setEndDate(final Date date) {
		dates.setEndValue(date);
	}
}
