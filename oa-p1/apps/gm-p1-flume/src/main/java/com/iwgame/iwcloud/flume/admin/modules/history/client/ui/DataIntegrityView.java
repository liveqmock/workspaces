/**      
 * DetailsView.java Create on 2012-3-14     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.iwcloud.flume.admin.modules.history.client.ui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.iwgame.iwcloud.flume.admin.client.i18n.Messages;
import com.iwgame.ui.client.widgets.date.AdvanceDateField;
import com.iwgame.ui.grid.client.PagingLoadResult;
import com.iwgame.ui.grid.client.SchemaGrid.Action;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.view.SchemaGridView;
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
public class DataIntegrityView extends SchemaGridView {

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
	public DataIntegrityView() {
		this(null);
	}

	/**
	 * 
	 */
	public DataIntegrityView(final Date date) {
		super(-1);
		getPanel().getGrid().setHasCheckBoxColumn(false);
		getPanel().setHeader("");
		formPanel = new XFormPanel();
		formPanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		layout = new FormLayout();
		layout.setAutoWidth(false);
//		layout.setColumn(2);
		formPanel.setLayout(layout);

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
				changePanelTitle(ApplicationContext.getCurrentProductId());
			}
		});

		layout.add(dateField);
		dateField.getWidget().getDateBox().addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(final ValueChangeEvent<Date> event) {
				getGrid().getLoadConfig().set("viewdate", event.getValue());
				reload();
			}

		});

		ColumnRenderer<BaseModelData> contrast_paid = new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(int column, int row, BaseModelData object, ColumnConfig config, SafeHtmlBuilder sb) {
				int contrast_paid = object.getInt("contrast_paid");
				if (contrast_paid != 0) {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:red;font-weight:bolder;'>异常</sapn>"));
				} else {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:green;font-weight:bolder;'>正常</sapn>"));
				}
			}
		};
		
		ColumnRenderer<BaseModelData> contrast_consume = new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object, ColumnConfig config, SafeHtmlBuilder sb) {
				int contrast_consume = object.getInt("contrast_consume");
				if (contrast_consume != 0) {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:red;font-weight:bolder;'>异常</sapn>"));
				} else {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:green;font-weight:bolder;'>正常</sapn>"));
				}
			}
		};

		getPanel().getGrid().setColumnRenderer("contrast_paid", contrast_paid);
		getPanel().getGrid().setColumnRenderer("contrast_consume", contrast_consume);

		filterField = new TextField("过滤条件", "filter");
//		layout.add(2, filterField);
		filterField.getWidget().addValueChangeHandler(new ValueChangeHandler<String>() {

			@Override
			public void onValueChange(final ValueChangeEvent<String> event) {
				getGrid().getLoadConfig().set("viewdate", dateField.getValue());
				getGrid().getLoadConfig().set("filter", event.getValue());
				reload();
			}
		});
		getGrid().getLoadConfig().set("viewdate", dateField.getValue());
		getGrid().getLoadConfig().set("filter", filterField.getValue());

		getPanel().getToolbar().addItem(formPanel);

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
	public void changePanelTitle(final String productId) {
		if ("P-P1".equalsIgnoreCase(productId)) {
			getPanel().setHeader("蜀门数据完整性报表");
		} else if("P-P1.5".equalsIgnoreCase(productId)){
			getPanel().setHeader("醉逍遥数据完整性报表");
		}else{
			getPanel().setHeader("石器数据完整性报表");
		}
	}

	private static DataIntegrityView instance;

	public static DataIntegrityView getInstance() {
		if (DataIntegrityView.instance == null) {
			DataIntegrityView.instance = new DataIntegrityView();
		}
		return DataIntegrityView.instance;
	}

}
