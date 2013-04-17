/**      
 * BusinessLogMonitorView.java Create on 2012-9-3     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.logmonitor.modules.business.client.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.log.XMVPLogger;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * @ClassName: BusinessLogMonitorView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-9-3 下午03:45:15
 * @Version 1.0
 * 
 */
public class BusinessLogMonitorView extends SchemaGridView {

	private static final XMVPLogger logger = new XMVPLogger(BusinessLogMonitorView.class);

	private static final Map<String, String> dateformats;

	static {
		dateformats = new HashMap<String, String>();
		dateformats.put("minute", "yyyy-MM-dd HH:mm");
		dateformats.put("hour", "yyyy-MM-dd HH:00");
		dateformats.put("day", "yyyy-MM-dd");
	}

	private final XFormPanel formPanel;
	private final FormLayout layout;
	private final SimpleSelector refreshSelector;
	private final SimpleSelector dateSelector;
	private final NumberField threshold;

	private TextField filterField;

	private String action;

	public void setAction(final String action) {
		this.action = action;
	}

	public BusinessLogMonitorView() {
		super(100);
		getPanel().getGrid().setHasCheckBoxColumn(false);
		getPanel().setHeader("业务日志监控");

		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		layout.setColumn(4);
		formPanel.setLayout(layout);

		dateSelector = new SimpleSelector("统计维度") {

			@Override
			public void initItems() {
				addItem("按分钟监控", "minute");
				addItem("按小时监控", "hour");
				addItem("按天监控", "day");
			}
		};
		dateSelector.setValue("hour");
		dateSelector.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				getQueryParams();
				reload();
			}
		});
		layout.add(dateSelector);

		threshold = new NumberField("警戒值", "threshold");
		layout.add(3, threshold);

		filterField = new TextField("", "filter");
		layout.add(2, filterField);

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
				getQueryParams();
				intervalLoad(s);
			}
		});
		layout.add(4, refreshSelector);

		ButtonToolItem btnRefresh = new ButtonToolItem("立即刷新");
		btnRefresh.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				getQueryParams();
				reload();
			}
		});

		formPanel.addButton(btnRefresh);

		getPanel().getToolbar().addItem(formPanel);

		getGrid().setColumnRenderer("date", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				Date date = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(
						object.<String> get(config.getIndex()));
				String format = dateformats.get(dateSelector.getValue());
				sb.append(SafeHtmlUtils.fromString(DateWrapper.format(date, format)));
			}
		});

		getGrid().setColumnRenderer("source", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public boolean isAutoWidth() {
				return false;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						Date date = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(object.<String> get("date"));
						String format = dateformats.get(dateSelector.getValue());
						History.newItem("logmonitor/business/detail?" + action + "=" + object.get("source") + "&time="
								+ DateWrapper.format(date, format) + "&type=" + dateSelector.getValue());
					}
				};
			}

		});
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

	public void getQueryParams() {
		getGrid().getLoadConfig().set("dateType", dateSelector.getValue());
		getGrid().getLoadConfig().set("threshold", threshold.getValue());
		getGrid().getLoadConfig().set("filter", filterField.getValue());
		logger.info("查询参数为 【监控维度=" + dateSelector.getValue() + "】【警戒值=" + threshold.getValue() + "】");
	}

	public TextField getFilterField() {
		return filterField;
	}

	public void setFilterField(final TextField filterField) {
		this.filterField = filterField;
	}

}
