/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ADDataView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.client.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.iwgame.gm.p1.adcollect.modules.reports.client.presenter.DADataPresenter;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.ExportButton;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FieldHeaderPlugin;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 广告数据实时追踪
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-13 上午09:11:29
 */
public class ADDataView extends SchemaGridView {

	private DADataPresenter presenter;

	public DADataPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(final DADataPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	private TextField key;
	private DateRangeField timeField;

	public ADDataView(final int adid, final Date date) {
		super(25);
		formPanel = new XFormPanel();
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(1);
		formPanel.setLayout(layout);

		key = new TextField("key");
		key.setAllowBlank(true);
		key.setValidateOnBlur(true);
		key.setWidth("450px");
		key.setEmptyText("最多只允许输入不超过10个广告ID，ID间以\",\"进行分隔。PS：\",\"必须为英文");
		key.setEnablePlugin(true);
		key.setHeaderPlugin(new FieldHeaderPlugin() {

			@Override
			public Widget getHeader(final Field<?, ?> field) {
				final ListBox listbox = new ListBox();
				listbox.addItem("广告ID", "id");
				listbox.addItem("关键字", "key");
				field.setFieldName("id");
				listbox.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(final ChangeEvent event) {
						field.setFieldName(listbox.getValue(listbox.getSelectedIndex()));
						key.setValue("");
					}
				});
				return listbox;
			}
		});
		
		layout.add(1, key);

		key.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				
				final String value = key.getValue();
				if(key.getFieldName().equals("id")){
					final String[] adid = value.split(",");
					if (value.subSequence(value.length() - 1, value.length()).equals(',')) {
						return "广告ID格式错误";
					}
					if (adid.length > 10) {
						return "广告ID不能超过10个";
					}
					for (int i = 0; i < adid.length; i++) {
						try {
							Integer.parseInt(adid[i]);
						} catch (final Exception e) {
							return "广告ID格式错误";
						}
					}
				}
				return null;
			}
		});

		timeField = new DateRangeField(true, "请选择检索时间");
		layout.add(1, timeField);

		if (0 != adid) {
			key.setValue(adid + "");
			final DateWrapper dw = new DateWrapper(date);
			timeField.setStartValue(dw.asDate());
			timeField.setEndValue(dw.addHours(1).asDate());
		} else {
			final DateWrapper dw = new DateWrapper(new Date());
			DateWrapper ndw = dw.clearTime();
			timeField.setStartValue(ndw.asDate());
			timeField.setEndValue(dw.addHours(1).asDate());
		}

		final ButtonToolItem query = new ButtonToolItem("查看");

		query.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (!key.getValue().equals("") && !formPanel.validate()) {
					return;
				}
				if ((null == timeField.getStartValue()) || (null == timeField.getEndValue())) {
					MessageBox.alert("请选择时间");
					return;
				}
				final BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
				loadConfig.set("isKey", key.getFieldName());
				loadConfig.set("adid", key.getValue());
				loadConfig.set("startTime", DateWrapper.format(timeField.getStartValue(), "yyyy-MM-dd HH:mm"));
				loadConfig.set("endTime", DateWrapper.format(timeField.getEndValue(), "yyyy-MM-dd HH:mm"));
				System.out.println(loadConfig.toString());
				getGrid().load();
			}
		});

		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);

		topToolbar.addItem(query);
		getPanel().setTopToolBar(topToolbar);
		// 导出
		getPanel().enableExport(true);
		getPanel().setExportButton(new ExportButton(getPanel()) {

			@Override
			public Map<String, String> getParameters() {
				if ((null == timeField.getStartValue()) || (null == timeField.getEndValue())) {
					MessageBox.alert("请选择时间");
					return null;
				}
				final Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("adid", key.getValue());
				parameters.put("startTime", DateWrapper.format(timeField.getStartValue(), "yyyy-MM-dd HH:mm"));
				parameters.put("endTime", DateWrapper.format(timeField.getEndValue(), "yyyy-MM-dd HH:mm"));
				parameters.put("isKey", key.getFieldName());
				parameters.put("productId", ApplicationContext.getCurrentProductId());
				return parameters;
			}

			@Override
			public String getExportBean() {
				return "gm-p1-ad-reports-aDDataService";
			}

			@Override
			public Map<String, String> getColumns() {
				final Map<String, String> columns = new LinkedHashMap<String, String>();
				columns.put("date", "日期~");
				columns.put("adId", "广告ID");
				columns.put("click", "点击");
				columns.put("ipClick", "独立IP点击数");
				columns.put("reg", "注册");
				columns.put("ipReg", "独立IP注册数");
				return columns;
			}

			@Override
			public String getFilename() {
				final StringBuilder sb = new StringBuilder();
				sb.append(ApplicationContext.getCurrentProduct().getTitle()).append("_");
				sb.append("实时报表").append("_");
				sb.append(DateTimeFormat.getFormat("yyyyMMddHHmmss").format(new Date())).append("_");// 导出时间
				String begin = "begin";
				String now = "now";
				if ((null == timeField.getStartValue()) && (null == timeField.getEndValue())) {
					sb.append("alltime");
					return sb.toString();
				}
				if (null != timeField.getStartValue()) {
					begin = DateWrapper.format(timeField.getStartValue(), "yyyyMMddHHmm");
				}
				if (null != timeField.getEndValue()) {
					now = DateWrapper.format(timeField.getEndValue(), "yyyyMMddHHmm");
				}
				sb.append(begin + "to" + now);
				return sb.toString();
			}
		});

	}

}
