/**      
 * DetailsView.java Create on 2012-3-14     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.history.client.ui;

import java.util.Date;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.iwcloud.flume.admin.client.dialogs.TimeDataDialog;
import com.iwgame.iwcloud.flume.admin.client.i18n.Messages;
import com.iwgame.iwcloud.flume.admin.modules.history.shared.service.HistoryService;
import com.iwgame.ui.client.widgets.date.AdvanceDateField;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.Cell;
import com.iwgame.ui.grid.client.IntervalRefreshColumn;
import com.iwgame.ui.grid.client.IntervalRefreshSchemaGrid;
import com.iwgame.ui.grid.client.PagingLoadResult;
import com.iwgame.ui.grid.client.SchemaGrid.Action;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.view.IntervalSchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: FlumeNodeView
 * @Description: 组历史监控数据
 * @author 吴江晖
 * @date 2012-3-14 上午10:08:02
 * @Version 1.0
 * 
 */
public class GroupHistoryView extends IntervalSchemaGridView {

	private final XFormPanel formPanel;
	private final FormLayout layout;

	private AdvanceDateField dateField;

	private TextField filterField;

	Messages messages = GWT.create(Messages.class);
	DateTimeFormat dtFormat = DateTimeFormat.getFormat("yyyy年MM月dd日");
	NumberFormat nFormat = NumberFormat.getDecimalFormat();

	/**
	 * 
	 */
	public GroupHistoryView() {
		this(null);
	}

	/**
	 * 
	 */
	public GroupHistoryView(final Date date) {
		super(-1);
		getPanel().getGrid().setHasCheckBoxColumn(false);
		getPanel().setHeader("");
		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		layout.setColumn(2);
		formPanel.setLayout(layout);

		ColumnRenderer<BaseModelData> renderer = new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				int fetcherCount = object.getInt("fetcherCount");
				int sinkCount = object.getInt("sinkCount");
				Double v = object.get(config.getIndex());
				if (fetcherCount != sinkCount) {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:red;font-weight:bolder;'>"
							+ nFormat.format(v) + "</sapn>"));
				} else {
					sb.append(SafeHtmlUtils.fromString(nFormat.format(v)));
				}

			}
		};

		getPanel().getGrid().setColumnRenderer("fetcherCount", renderer);
		getPanel().getGrid().setColumnRenderer("sinkCount", renderer);
		getPanel().getGrid().setColumnRenderer("status", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				String status = object.get(config.getIndex());
				if (!"active".equalsIgnoreCase(status)) {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:red;font-weight:bolder;'>异常</sapn>"));
				} else {
					sb.append(SafeHtmlUtils
							.fromTrustedString("<span style='color:green;font-weight:bolder;'>正常</sapn>"));
				}
			}
		});
		getPanel().getGrid().setColumnRenderer("groupName", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {

				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						String channelId = object.get("groupId");
						String url = "flumeadmin/history/group?action=d&channelId=" + channelId + "&channelName="
								+ object.get("groupName") + "&date="
								+ DateTimeFormat.getFormat("yyyyMMdd").format(dateField.getValue());
						History.newItem(url);
					}

				};

			}
		});
		final DateWrapper dw = new DateWrapper(new Date());
		dateField = new AdvanceDateField("查看日期", "viewdate");
		if (date == null) {
			dateField.setValue(dw.addDays(-1).asDate());
		} else {
			dateField.setValue(date);
		}
		getGrid().setOnDataLoad(new Action<BaseModelData>() {

			@Override
			public void execute(final PagingLoadResult<BaseModelData> datas) {
				changePanelTitle(dw, new Date(), datas.getTotal());
			}
		});

		layout.add(dateField);
		dateField.getWidget().getDateBox().addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(final ValueChangeEvent<Date> event) {
				int s = Integer.valueOf(0);
				getGrid().getLoadConfig().set("viewdate", event.getValue());
				getGrid().getLoadConfig().set("filter", filterField.getValue());
				intervalLoad(s);
			}

		});

		filterField = new TextField("过滤条件", "filter");
		layout.add(2, filterField);
		filterField.getWidget().addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(final ValueChangeEvent<String> event) {
				int s = Integer.valueOf(0);
				getGrid().getLoadConfig().set("viewdate", dateField.getValue());
				getGrid().getLoadConfig().set("filter", event.getValue());
				intervalLoad(s);

			}
		});
		getGrid().getLoadConfig().set("viewdate", dateField.getValue());
		getGrid().getLoadConfig().set("filter", filterField.getValue());

		getPanel().getGrid().setColumnRenderer("complete", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData rowData) {
						new TimeDataDialog() {

							@Override
							public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
								HistoryService.Util.get().getGroupTimeDatas(
										ApplicationContext.getCurrentProduct().getName(),
										rowData.<String> get("groupId"), dateField.getValue(), callback);
							}

							@Override
							public String getDialogTitle() {
								return messages.groupTimeDialogTitle(rowData.<String> get("groupName"),
										dtFormat.format(dateField.getValue()));
							}
						}.center();
					}
				};
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see com.iwgame.ui.grid.client.column.renderer.
			 * ButtonColumnRenderer#getText(int, int, java.lang.Object,
			 * com.iwgame.ui.grid.shared.model.ColumnConfig)
			 */
			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				String text = rowData.get("complete");
				if ("100.00%".equals(text)) {
					if (rowData.getInt("fetcherCount") == rowData.getInt("sinkCount")) {
						return text;
					} else {
						return "99.99%";
					}
				} else {
					return text;
				}
			}

		});

		getPanel().getToolbar().addItem(formPanel);

		setIntervalRefreshColumn(new IntervalRefreshColumn() {

			@Override
			public void setValue(final TableCellElement cellElement, final Cell cell) {
				IntervalRefreshSchemaGrid<BaseModelData> grid = (IntervalRefreshSchemaGrid<BaseModelData>) getGrid();
				String index = cell.getIndex();
				int row = cell.getRow();

				if ("fetcherCount".equals(index) || "sinkCount".equals(index)) {
					Object f = grid.getCacheData().get(row).get("fetcherCount");
					Object s = grid.getCacheData().get(row).get("sinkCount");
					boolean isEqual = f.equals(s);
					if (isEqual) {
						if (cellElement.getChildCount() > 0) {
							Element e = cellElement.getChild(0).cast();
							StringBuffer sb = new StringBuffer();
							Object v = cell.getV();
							if (v instanceof Double) {
								sb.append(nFormat.format((Double) v));
							}
							e.setInnerHTML(sb.toString());
						}
					} else {
						if (cellElement.getChildCount() > 0) {
							Element e = cellElement.getChild(0).cast();
							StringBuffer sb = new StringBuffer();
							sb.append("<span style='color:red;font-weight:bolder;'>");
							Object v = cell.getV();
							if (v instanceof Double) {
								sb.append(nFormat.format((Double) v));
							}
							sb.append("</span>");
							e.setInnerHTML(sb.toString());
						}
					}
				} else if ("status".equals(cell.getIndex())) {
					if ("ACTIVE".equals(cell.getV())) {
						if (cellElement.getChildCount() > 0) {
							Element e = cellElement.getChild(0).cast();
							StringBuffer sb = new StringBuffer();
							sb.append("<span style='color:green;font-weight:bolder;'>");
							sb.append("正常");
							sb.append("</span>");
							e.setInnerHTML(sb.toString());
						}
					} else {
						if (cellElement.getChildCount() > 0) {
							Element e = cellElement.getChild(0).cast();
							StringBuffer sb = new StringBuffer();
							sb.append("<span style='color:red;font-weight:bolder;'>");
							sb.append("异常");
							sb.append("</span>");
							e.setInnerHTML(sb.toString());
						}
					}
				} else if ("complete".equals(cell.getIndex())) {
					String text = cell.getV().toString();
					Object f = grid.getCacheData().get(row).get("fetcherCount");
					Object s = grid.getCacheData().get(row).get("sinkCount");
					if ("100.00%".equals(text)) {
						if (!f.equals(s)) {
							text = "99.99%";
						}
					}
					if (cellElement.getChildCount() > 0) {
						Element e = cellElement.getChild(0).cast();
						if (e.getChildCount() > 0) {
							e = e.getChild(0).cast();
						}
						e.setInnerHTML(text);
					}
				} else {
					if (cellElement.getChildCount() > 0) {
						Element e = cellElement.getChild(0).cast();
						if (e.getChildCount() > 0) {
							e = e.getChild(0).cast();
						}
						StringBuffer sb = new StringBuffer();
						Object v = cell.getV();
						if (v instanceof Double) {
							sb.append(((Double) v).intValue());
						} else {
							sb.append(v);
						}
						e.setInnerHTML(sb.toString());
					}
				}
			}
		});
	}

	public Date getDate() {
		Date date = dateField.getValue();
		if (date == null) {
			date = new Date();
		}
		return date;
	}

	/**
	 * @param dw
	 * @param event
	 */
	public void changePanelTitle(final DateWrapper dw, final Date date, final int num) {
		if (dw.isSameDay(date)) {
			getPanel().setHeader(messages.groupTitle(messages.today(), num));
		} else {
			DateTimeFormat format = DateTimeFormat.getFormat(messages.titleDateFormat());
			getPanel().setHeader(messages.groupTitle(format.format(date), num));
		}
	}

	private static GroupHistoryView instance;

	public static GroupHistoryView getInstance() {
		if (GroupHistoryView.instance == null) {
			GroupHistoryView.instance = new GroupHistoryView();
		}
		return GroupHistoryView.instance;
	}

}
