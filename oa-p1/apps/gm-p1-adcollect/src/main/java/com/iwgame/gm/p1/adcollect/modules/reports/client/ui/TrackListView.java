/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： TrackListView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.client.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.iwgame.gm.p1.adcollect.modules.reports.client.presenter.TrackPresenter;
import com.iwgame.gm.p1.adcollect.modules.reports.client.ui.dialog.InfoDialog;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.ExportButton;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 百度关键字小时追踪
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-20 上午11:01:54
 */
public class TrackListView extends SchemaGridView {

	private TrackPresenter presenter;

	public void setPresenter(final TrackPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	public TrackPresenter getPresenter() {
		return presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	private TextField keyField;
	private DateRangeField time;
	private RadioButtonGroup rbg;

	public TrackListView() {
		super(25);
		getPanel().setHeader("百度关键字小时追踪");

		formPanel = new XFormPanel();
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(3);
		formPanel.setLayout(layout);

		keyField = new TextField("请输入关键字");
		layout.add(keyField);

		time = new DateRangeField("统计时间");
		time.getWidget().setEndDateChangeLinks();
		time.setEndValue(new Date());
		layout.add(2, time);

		rbg = new RadioButtonGroup("统计方式", "Statistical", Direction.Horizontal);
		rbg.addRadioButton("按文本", "0", true);
		rbg.addRadioButton("按广告ID", "1", false);
		layout.add(3, rbg);

		final ButtonToolItem query = new ButtonToolItem("检索");

		query.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				String josnView = null;
				if (rbg.getValue().equals("0")) {// 文本
					josnView = "{\"table\":{\"key\":\"id\",\"columns\":["
							+ "{\"index\":\"keyName\",\"header\":\"关键字\",\"width\":200,\"type\":\"button\"},"
							+ "{\"index\":\"date\",\"header\":\"日期\",\"width\":100,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
							+ "{\"index\":\"date\",\"header\":\"时间\",\"width\":60,\"type\":\"date\",\"format\":\"HH:mm\"},"
							+ "{\"index\":\"click\",\"header\":\"点击数\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计这个日期的这个时间段内，该关键词所属所有广告链接的点击总数\"},"
							+ "{\"index\":\"reg\",\"header\":\"注册数\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计这个日期的这个时间段内，该关键词所属所有广告链接的账号注册总数\"},"
							+ "{\"index\":\"newReg\",\"header\":\"新帐号注册数\",\"width\":120,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计这个日期的这个时间段内，该关键词所属所有广告链接的新账号注册总数\"},"
							+ "{\"index\":\"subNewReg\",\"header\":\"次新帐号注册数\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计这个日期的这个时间段内，该关键词所属所有广告链接的次新账号注册总数\"},"
							+ "{\"index\":\"adIdSub\",\"header\":\"广告ID字符串\",\"width\":100,\"type\":\"string\"}"
							+ "]}}";
				} else if (rbg.getValue().equals("1")) { // 广告ID
					josnView = "{\"table\":{\"key\":\"id\",\"columns\":["
							+ "{\"index\":\"keyName\",\"header\":\"关键字\",\"width\":200,\"type\":\"button\"},"
							+ "{\"index\":\"adId\",\"header\":\"广告ID\",\"width\":100,\"type\":\"string\"},"
							+ "{\"index\":\"date\",\"header\":\"日期\",\"width\":100,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
							+ "{\"index\":\"date\",\"header\":\"时间\",\"width\":60,\"type\":\"date\",\"format\":\"HH:mm\"},"
							+ "{\"index\":\"click\",\"header\":\"点击数\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计这个日期的这个时间段内，该关键词所属所有广告链接的点击总数\"},"
							+ "{\"index\":\"reg\",\"header\":\"注册数\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计这个日期的这个时间段内，该关键词所属所有广告链接的账号注册总数\"},"
							+ "{\"index\":\"newReg\",\"header\":\"新帐号注册数\",\"width\":120,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计这个日期的这个时间段内，该关键词所属所有广告链接的新账号注册总数\"},"
							+ "{\"index\":\"subNewReg\",\"header\":\"次新帐号注册数\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计这个日期的这个时间段内，该关键词所属所有广告链接的次新账号注册总数\"}"
							+ "]}}";
				}
				setSchemaJson(josnView);
				getGrid().initialGridSchema(false);
				getPanel().onResize();

				final BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
				if (null != time.getStartValue()) {
					loadConfig.set("startTime", DateWrapper.format(time.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					loadConfig.set("startTime", null);
				}
				if (null != time.getEndValue()) {
					final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
					loadConfig.set("endTime", DateWrapper.format(dateWrapper.clearTime().addDays(1).addSeconds(-1)
							.asDate(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					loadConfig.set("endTime", null);
				}
				loadConfig.set("key", keyField.getValue());
				loadConfig.set("type", rbg.getValue());
				// 加载 数据
				getGrid().load();
			}
		});

		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(query);
		getPanel().setTopToolBar(topToolbar);
		getPanel().enableExport(true);
		getPanel().setExportButton(new ExportButton(getPanel()) {

			@Override
			public Map<String, String> getParameters() {
				final Map<String, String> parameters = new HashMap<String, String>();
				if (null != time.getStartValue()) {
					parameters.put("startTime", DateWrapper.format(time.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					parameters.put("startTime", null);
				}
				if (null != time.getEndValue()) {
					final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
					parameters.put("endTime", DateWrapper.format(dateWrapper.clearTime().addDays(1).addSeconds(-1)
							.asDate(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					parameters.put("endTime", null);
				}
				parameters.put("productId", ApplicationContext.getCurrentProductId());
				parameters.put("key", keyField.getValue());
				parameters.put("type", rbg.getValue());
				return parameters;
			}

			@Override
			public Map<String, String> getColumns() {
				final Map<String, String> columns = new LinkedHashMap<String, String>();
				columns.put("keyName", "关键字");
				if (rbg.getValue().equals("1")) { // ID
					columns.put("adId", "广告ID");
				}
				columns.put("date", "日期");
				columns.put("click", "点击数");
				columns.put("reg", "注册数");
				columns.put("newReg", "新帐号注册数");
				columns.put("subNewReg", "次新帐号注册数");
				if (rbg.getValue().equals("0")) { // 文本
					columns.put("adIdSub", "广告ID字符串");
				}
				return columns;
			}

			@Override
			public String getExportBean() {
				String beanName = "";
				if (rbg.getValue().equals("0")) { // 文本
					beanName = "gm-p1-ad-export-trackTxtService";
				}
				if (rbg.getValue().equals("1")) { // ID
					beanName = "gm-p1-ad-export-trackIdService";
				}
				return beanName;
			}

			@Override
			public String getFilename() {
				final StringBuilder sb = new StringBuilder();
				sb.append(ApplicationContext.getCurrentProduct().getTitle()).append("_");
				sb.append("小时报表").append("_");
				sb.append(DateTimeFormat.getFormat("yyyyMMddHHmmss").format(new Date())).append("_");// 导出时间
				String begin = "begin";
				String now = "now";
				if ((null == time.getStartValue()) && (null == time.getEndValue())) {
					sb.append("alltime");
					return sb.toString();
				}
				if (null != time.getStartValue()) {
					begin = DateWrapper.format(time.getStartValue(), "yyyyMMdd");
				}
				if (null != time.getEndValue()) {
					now = DateWrapper.format(time.getEndValue(), "yyyyMMdd");
				}
				sb.append(begin + "to" + now);
				return sb.toString();
			}
		});

		getPanel().setColumnRenderer("keyName", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						final String keyName = object.get("keyName").toString();
						presenter.getRaceListByKeyName(ApplicationContext.getCurrentProductId(), keyName,
								new AsyncCallbackEx<List<AdvertisementInfo>>() {

									@Override
									public void onSuccess(final List<AdvertisementInfo> result) {
										if (!result.isEmpty()) {
											new InfoDialog(result).center();
										} else {
											MessageBox.alert("数据库中没有相应的数据");
										}
									}
								});
					}
				};
			}
		});

		// 渲染 adIdSub
		// getPanel().setColumnRenderer("adIdSub", new
		// ColumnRenderer<BaseModelData>() {
		// @Override
		// public void render(final int column, final int row, final
		// BaseModelData object, final ColumnConfig config,
		// final SafeHtmlBuilder sb) {
		// String adIdSub = object.get("adIdSub").toString();
		// adIdSub = adIdSub.replace("|", "<br/>");
		// sb.appendHtmlConstant(adIdSub);
		// }
		// });
	}

}
