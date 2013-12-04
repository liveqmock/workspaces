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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.iwcloud.flume.admin.client.dialogs.TimeDataDialog;
import com.iwgame.iwcloud.flume.admin.client.i18n.Messages;
import com.iwgame.iwcloud.flume.admin.modules.history.shared.service.HistoryService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.Cell;
import com.iwgame.ui.grid.client.IntervalRefreshColumn;
import com.iwgame.ui.grid.client.IntervalRefreshSchemaGrid;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.IntervalSchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: TodayGroupView
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-3-14 上午10:08:02
 * @Version 1.0
 * 
 */
public class ChannelHistoryView extends IntervalSchemaGridView {

	private final XFormPanel formPanel;
	private final FormLayout layout;
	private Date date;

	Messages messages = GWT.create(Messages.class);
	DateTimeFormat dtFormat = DateTimeFormat.getFormat("yyyy年MM月dd日");
	NumberFormat nFormat = NumberFormat.getDecimalFormat();

	public Date getDate() {
		return date;
	}

	public void setDate(final Date date) {
		this.date = date;
	}

	/**
	 * 
	 */
	public ChannelHistoryView() {
		super(-1);
		getPanel().getGrid().setHasCheckBoxColumn(false);

		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
		formPanel.setLayout(layout);

		ColumnRenderer<BaseModelData> countRenderer = new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row,
					final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				int fetcherCount = object.getInt("fetcherCount");
				int sinkCount = object.getInt("sinkCount");
				Double v = object.get(config.getIndex());
				if (fetcherCount != sinkCount) {
					sb.append(SafeHtmlUtils
							.fromTrustedString("<span style='color:red;font-weight:bolder;'>"
									+ nFormat.format(v) + "</sapn>"));
				} else {
					sb.append(SafeHtmlUtils.fromString(nFormat.format(v)));
				}

			}
		};

		ColumnRenderer<BaseModelData> statusRenderer = new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row,
					final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				String status = object.get(config.getIndex());
				if (!"active".equalsIgnoreCase(status)
						&& !"idle".equalsIgnoreCase(status)) {
					sb.append(SafeHtmlUtils
							.fromTrustedString("<span style='color:red;font-weight:bolder;'>"
									+ status + "</sapn>"));
				} else {
					sb.append(SafeHtmlUtils
							.fromTrustedString("<span style='color:green;font-weight:bolder;'>"
									+ status + "</sapn>"));
				}

			}
		};

		getPanel().getGrid().setColumnRenderer("fetcherCount", countRenderer);
		getPanel().getGrid().setColumnRenderer("sinkCount", countRenderer);
		getPanel().getGrid().setColumnRenderer("fetcherStatus", statusRenderer);
		getPanel().getGrid().setColumnRenderer("sinkStatus", statusRenderer);

		getPanel().getGrid().setColumnRenderer("complete",
				new ButtonColumnRenderer<BaseModelData>() {

					@Override
					public boolean isPopupWindow() {
						return true;
					}

					@Override
					public String getText(final int column, final int row,
							final BaseModelData rowData,
							final ColumnConfig config) {
						String text = rowData.get("complete");
						if ("100.00%".equals(text)) {
							if (rowData.getInt("fetcherCount") == rowData
									.getInt("sinkCount")) {
								return text;
							} else {
								return "99.99%";
							}
						} else {
							return text;
						}
					}

					@Override
					public Delegate<BaseModelData> getDelegate() {
						return new Delegate<BaseModelData>() {

							@Override
							public void execute(final BaseModelData rowData) {
								new TimeDataDialog() {

									@Override
									public void loadData(
											final Object loadConfig,
											final AsyncCallbackEx<String> callback) {
										HistoryService.Util
												.get()
												.getChannelTimeDatas(
														ApplicationContext
																.getCurrentProduct()
																.getName(),
														rowData.<String> get("channelId"),
														date, callback);
									}

									@Override
									public String getDialogTitle() {
										return messages.channelTimeDialogTitle(
												rowData.<String> get("channelName"),
												dtFormat.format(date));
									}
								}.center();
							}
						};
					}

				});

		getPanel().getGrid().setColumnRenderer("sinkv",
				new ColumnRenderer<BaseModelData>() {

					@Override
					public void render(final int column, final int row,
							final BaseModelData object,
							final ColumnConfig config, final SafeHtmlBuilder sb) {
						Double costtime = object.get("costtime");
						Double sinkcount = object.get("sinkCount");
						NumberFormat format = NumberFormat.getDecimalFormat();
						sb.appendEscapedLines(format.format(costtime
								/ sinkcount));
					}
				});

		ButtonToolItem btnBack = new ButtonToolItem("返回");
		btnBack.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				History.newItem("flumeadmin/history/group?action=m&date="
						+ DateTimeFormat.getFormat("yyyyMMdd").format(date));
			}
		});
		formPanel.addButton(btnBack);

		getPanel().getToolbar().addItem(formPanel);

		setIntervalRefreshColumn(new IntervalRefreshColumn() {

			@Override
			public void setValue(final TableCellElement cellElement,
					final Cell cell) {
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
				} else if ("sinkStatus".equals(cell.getIndex())
						|| "fetcherStatus".equals(cell.getIndex())) {
					if ("ACTIVE".equals(cell.getV())
							|| "IDLE".equals(cell.getV())) {
						if (cellElement.getChildCount() > 0) {
							Element e = cellElement.getChild(0).cast();
							StringBuffer sb = new StringBuffer();
							sb.append("<span style='color:green;font-weight:bolder;'>");
							sb.append(cell.getV());
							sb.append("</span>");
							e.setInnerHTML(sb.toString());
						}
					} else {
						if (cellElement.getChildCount() > 0) {
							Element e = cellElement.getChild(0).cast();
							StringBuffer sb = new StringBuffer();
							sb.append("<span style='color:red;font-weight:bolder;'>");
							sb.append(cell.getV());
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

	private static ChannelHistoryView instance;

	public static ChannelHistoryView getInstance() {
		if (ChannelHistoryView.instance == null) {
			ChannelHistoryView.instance = new ChannelHistoryView();
		}
		return ChannelHistoryView.instance;
	}
}
