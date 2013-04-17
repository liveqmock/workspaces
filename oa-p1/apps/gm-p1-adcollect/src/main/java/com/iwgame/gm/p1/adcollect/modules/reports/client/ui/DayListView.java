/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DayListView.java
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
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.iwgame.gm.p1.adcollect.client.wiget.KeyTypeField;
import com.iwgame.gm.p1.adcollect.modules.reports.client.presenter.DayListPresenter;
import com.iwgame.gm.p1.adcollect.modules.reports.client.ui.dialog.InfoDialog;
import com.iwgame.gm.p1.adcollect.shared.model.AdvertisementInfo;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.ExportButton;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.xmvp.client.log.XMVPLogger;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 百度关键字日汇总视图
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-20 上午11:35:40
 */
public class DayListView extends SchemaGridView {
	private DayListPresenter presenter;

	public DayListPresenter getPresenter() {
		return presenter;
	}

	private final XMVPLogger logger = new XMVPLogger(DayListView.class);

	public void setPresenter(final DayListPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	private KeyTypeField keyField;
	private DateRangeField time;
	private RadioButtonGroup showWay;
	private RadioButtonGroup statisticsWay;

	private boolean loadingDefault = false;
	private final String dayType;

	private int aggregateType;

	public int getAggregateType() {
		return aggregateType;
	}

	public DayListView(final String beginDate, final String endDate, final String appointType) {
		super(25);
		dayType = appointType;
		logger.fine("最精细的消息");
		logger.info("消息");
		// logger.severe("错误信息");

		getPanel().setHeader("百度关键字日汇总表");

		formPanel = new XFormPanel();
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(3);
		formPanel.setLayout(layout);

		logger.info("关键字类型" + appointType);
		keyField = new KeyTypeField(null, "keyTypeRadio");
		keyField.setAllowBlank(true);
		keyField.setValidateOnBlur(true);
		keyField.setColSpan(3);
		layout.add(keyField);
		if (!"".equals(appointType)) {
			keyField.setTypeValue(appointType);
			loadingDefault = true;
		}
		time = new DateRangeField("统计时间");
		time.getWidget().setEndDateChangeLinks();
		time.setEndValue(new Date());
		layout.add(time);
		if (!"".equals(beginDate)) {
			final Date begin = DateTimeFormat.getFormat("yyyy-MM-dd").parse(beginDate);
			logger.info(begin.toString());
			time.setStartValue(begin);
		}
		if (!"".equals(endDate)) {
			final Date end = DateTimeFormat.getFormat("yyyy-MM-dd").parse(endDate);
			logger.info(end.toString());
			time.setEndValue(end);
		}
		showWay = new RadioButtonGroup("显示方式", "show", Direction.Horizontal);
		showWay.addRadioButton("明细", "detail", true);
		showWay.addRadioButton("汇总", "summary", false);
		layout.add(2, showWay);

		statisticsWay = new RadioButtonGroup("汇总方式", "Statistical", Direction.Horizontal);
		statisticsWay.addRadioButton("文本", "0", true);
		statisticsWay.addRadioButton("广告ID", "1", false);
		layout.add(3, statisticsWay);

		final ButtonToolItem query = new ButtonToolItem("检索");

		query.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {

				if (!formPanel.validate()) {
					return;
				}
				if (showWay.getValue().equals("summary")) {
					if ((null == time.getStartValue()) || (null == time.getEndValue())) {
						MessageBox.alert("请选择时间");
						return;
					}
				}
				// if ("type".equals(keyField.getKeyType())) {
				// if (keyField.getTypeList() == null ||
				// keyField.getTypeList().size() == 0) {
				// MessageBox.alert("请选择指定类型");
				// return;
				// }
				// }

				String josnView = null;
				if (showWay.getValue().equals("detail") && statisticsWay.getValue().equals("0")) {
					// 明细按文本
					josnView = initDetailTxt();
					aggregateType = 4;
				}
				if (showWay.getValue().equals("detail") && statisticsWay.getValue().equals("1")) {
					// 明细按广告ID
					josnView = initDetailId();
					aggregateType = 3;
				}
				if (showWay.getValue().equals("summary") && statisticsWay.getValue().equals("0")) {
					// 汇总按文本
					josnView = initSummaryTxt();
					aggregateType = 4;
				}
				if (showWay.getValue().equals("summary") && statisticsWay.getValue().equals("1")) {
					// 汇总按广告ID
					josnView = initSummaryId();
					aggregateType = 3;
				}

				setSchemaJson(josnView);
				getGrid().initialGridSchema(false);
				getPanel().onResize();

				final BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
				if (null != time.getStartValue()) {
					loadConfig.set("startTime",
							DateWrapper.format(time.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					loadConfig.set("startTime", null);
				}
				if (null != time.getEndValue()) {
					final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
					loadConfig.set("endTime", DateWrapper.format(dateWrapper.clearTime().addDays(1)
							.asDate(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					loadConfig.set("endTime", null);
				}

				loadConfig.set("keyType", keyField.getKeyType()); // key 关键字
																	// type 关键字
																	// 类型
				if (keyField.getKeyType().equals("key")) {// 关键字
					loadConfig.set("key", keyField.getkey());
					loadConfig.set("type", null);
				} else if (keyField.getKeyType().equals("type")) {// 关键字 类型
					final List<String> list = keyField.getTypeList();
					loadConfig.set("type", listToString(list));
					loadConfig.set("key", null);
				}
				loadConfig.set("show", showWay.getValue());
				loadConfig.set("statistics", statisticsWay.getValue());

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
					parameters.put("startTime",
							DateWrapper.format(time.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					parameters.put("startTime", null);
				}
				if (null != time.getEndValue()) {
					final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
					parameters.put("endTime", DateWrapper.format(dateWrapper.clearTime().addDays(1)
							.asDate(), "yyyy-MM-dd HH:mm:ss"));
				} else {
					parameters.put("endTime", null);
				}
				parameters.put("keyType", keyField.getKeyType()); // key 关键字
																	// type 关键字
																	// 类型
				if (keyField.getKeyType().equals("key")) {// 关键字
					parameters.put("key", keyField.getkey());
				} else if (keyField.getKeyType().equals("type")) {// 关键字 类型
					final List<String> list = keyField.getTypeList();
					parameters.put("type", listToString(list));
				}
				parameters.put("show", showWay.getValue());
				parameters.put("statistics", statisticsWay.getValue());
				parameters.put("productId", ApplicationContext.getCurrentProductId());
				return parameters;
			}

			@Override
			public String getExportBean() {
				String beanName = "";
				if (statisticsWay.getValue().equals("0")) {// 文本
					beanName = "gm-p1-ad-export-dayTxtService";
				}
				if (statisticsWay.getValue().equals("1")) {// id
					beanName = "gm-p1-ad-export-dayIdService";
				}
				return beanName;
			}

			@Override
			public Map<String, String> getColumns() {
				final Map<String, String> columns = new LinkedHashMap<String, String>();
				columns.put("date", "日期");
				columns.put("type", "类型");
				columns.put("keyName", "关键字");

				// 文本
				if (statisticsWay.getValue().equals("0")) {

					// 汇总按文本
					columns.put("used", "总消费");
					columns.put("show", "总展现");
					columns.put("click", "总点击");
					columns.put("clickPrice", "CPC");
					columns.put("rank", "排行");
					columns.put("clickRatio", "点击率");
					columns.put("cpm", "CPM");
					columns.put("reg", "总注册");
					columns.put("regLogin", "当日登录");
					columns.put("cpa", "CPA");
					columns.put("regRatio", "注册率");
					columns.put("loginPrice", "登录成本");
					columns.put("newReg", "新帐号");
					columns.put("newRegRatio", "新帐号比例");
					columns.put("subNewReg", "次新帐号");
					columns.put("subNewRegRatio", "次新帐号比例");
					columns.put("oldReg", "老账号");
					columns.put("oldRegRatio", "老账号比例");
					columns.put("newLogin", "新帐号登陆");
					columns.put("subNewLogin", "次新帐号登陆");
					columns.put("oldLogin", "老账号登录");
					columns.put("adIdSub", "广告ID字符串");
				}
				// ID
				if (statisticsWay.getValue().equals("1")) {

					columns.put("adId", "广告ID");
					columns.put("reg", "总注册");
					columns.put("regLogin", "当日登录");
					columns.put("newReg", "新帐号");
					columns.put("newRegRatio", "新帐号比例");
					columns.put("subNewReg", "次新帐号");
					columns.put("subNewRegRatio", "次新帐号比例");
					columns.put("oldReg", "老账号");
					columns.put("oldRegRatio", "老账号比例");
					columns.put("newLogin", "新帐号登陆");
					columns.put("subNewLogin", "次新帐号登陆");
					columns.put("oldLogin", "老账号登录");

					// if (showWay.getValue().equals("detail")) {// OK
					// / 明细按广告ID
					// }
					//
					// if (showWay.getValue().equals("summary")) {//
					// // 汇总按广告ID
					//
					// }
				}
				return columns;
			}

			@Override
			public String getFilename() {
				final StringBuilder sb = new StringBuilder();
				sb.append(ApplicationContext.getCurrentProduct().getTitle()).append("_");
				sb.append("日报表").append("_");
				sb.append(DateTimeFormat.getFormat("yyyyMMddHHmmss").format(new Date()))
						.append("_");// 到处时间
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
						presenter.getRaceListByKeyName(ApplicationContext.getCurrentProductId(),
								keyName, new AsyncCallbackEx<List<AdvertisementInfo>>() {

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

		// 渲染状态
		getPanel().setColumnRenderer("summarydate", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {

				if (showWay.getValue().equals("summary")) {
					String s = "";
					String e = "";
					if (null != time.getStartValue()) {
						s = DateTimeFormat.getFormat("yyyy-MM-dd").format(time.getStartValue());
					}
					if (null != time.getEndValue()) {
						e = DateTimeFormat.getFormat("yyyy-MM-dd").format(time.getEndValue());
					}
					sb.appendHtmlConstant(s + "--" + e);
				}
			}
		});

		// // 渲染 adIdSub
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
		// 渲染 adId
		getPanel().setColumnRenderer("adIdShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				final Double adIdSub = object.get("adId");
				sb.appendHtmlConstant(adIdSub.intValue() + "");
			}
		});
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		logger.info(loadingDefault + "状态");
		// 如果有链接来源 默认加载数据
		if (loadingDefault) {
			setSchemaJson(initDetailTxt());
			getGrid().initialGridSchema(false);
			getPanel().onResize();

			final BaseFilterPagingLoadConfig loadConfigDefaul = getGrid().getLoadConfig();
			if (null != time.getStartValue()) {
				loadConfigDefaul.set("startTime",
						DateWrapper.format(time.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
			} else {
				loadConfigDefaul.set("startTime", null);
			}
			if (null != time.getEndValue()) {
				final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
				loadConfigDefaul.set("endTime", DateWrapper.format(
						dateWrapper.clearTime().addDays(1).asDate(), "yyyy-MM-dd HH:mm:ss"));
			} else {
				loadConfigDefaul.set("endTime", null);
			}

			loadConfigDefaul.set("keyType", "type"); // key 关键字 type 关键字 类型
			loadConfigDefaul.set("type", "('" + dayType + "')");
			// 默认值
			loadConfigDefaul.set("show", "detail");
			loadConfigDefaul.set("statistics", "0");

			// 加载 数据
			getGrid().load();
		}
	}

	/** 汇总ID */
	private String initSummaryId() {
		final String josnView = "{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"summarydate\",\"header\":\"日期\",\"width\":200},"
				+ "{\"index\":\"type\",\"header\":\"类型\",\"width\":50,\"type\":\"string\"},"
				+ "{\"index\":\"keyName\",\"header\":\"关键字\",\"width\":200,\"type\":\"button\"},"
				+ "{\"index\":\"adIdShow\",\"header\":\"广告ID\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"reg\",\"header\":\"总注册\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内所属所有广告链接的注册总数\"},"
				+ "{\"index\":\"regLogin\",\"header\":\"当日登录\",\"width\":90,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内注册并且在注册当日第一次登陆的账号数\"},"
				+ "{\"index\":\"newReg\",\"header\":\"新帐号\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内新账号注册数\"},"
				+ "{\"index\":\"newRegRatio\",\"header\":\"新帐号比例\",\"width\":90,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"subNewReg\",\"header\":\"次新账号\",\"width\":90,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内次新账号注册数\"},"
				+ "{\"index\":\"subNewRegRatio\",\"header\":\"次新账号比例\",\"width\":100,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"oldReg\",\"header\":\"老账号\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内老账号注册\"},"
				+ "{\"index\":\"oldRegRatio\",\"header\":\"老账号比例\",\"width\":90,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"newLogin\",\"header\":\"新账号当日登陆\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内新账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"subNewLogin\",\"header\":\"次新账号当日登陆\",\"width\":145,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内次新账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"oldLogin\",\"header\":\"老账号当日登陆\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内老账号注册并在注册当日登陆数\"}"
				+ "]}}";
		return josnView;
	}

	/** 汇总Txt */
	private String initSummaryTxt() {
		final String josnView = "{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"summarydate\",\"header\":\"日期\",\"width\":200},"
				+ "{\"index\":\"type\",\"header\":\"类型\",\"width\":50,\"type\":\"string\"},"
				+ "{\"index\":\"keyName\",\"header\":\"关键字\",\"width\":200,\"type\":\"button\"},"
				+ "{\"index\":\"used\",\"header\":\"总消费\",\"width\":130,\"type\":\"currency\",\"format\":\"￥#.##\",\"sortable\":true,\"tips\":\"统计时间段内总计消费的百度金\"},"
				+ "{\"index\":\"show\",\"header\":\"总展现\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内总计展现次数\"},"
				+ "{\"index\":\"click\",\"header\":\"总点击\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内所属所有广告链接的点击总数\"},"//
				+ "{\"index\":\"clickPrice\",\"header\":\"CPC\",\"width\":90,\"type\":\"currency\",\"format\":\"￥#.##\",\"sortable\":true,\"tips\":\"单点击价格=总消费/总点击\"},"
				+ "{\"index\":\"rank\",\"header\":\"排名\",\"width\":70,\"type\":\"number\",\"format\":\"#.##\",\"sortable\":true,\"tips\":\"这个关键词中展现最高的ID对应的排名\"},"
				+ "{\"index\":\"clickRatio\",\"header\":\"点击率\",\"width\":80,\"type\":\"percent\",\"format\":\"#.##%\",\"sortable\":true,\"tips\":\"点击率=总点击/总展现，百分比显示\"},"
				+ "{\"index\":\"cpm\",\"header\":\"CPM\",\"width\":80,\"type\":\"number\",\"format\":\"#.##\",\"sortable\":true,\"tips\":\"CPM=千次展现价格，公式为：总消费/总展现*1000\"},"
				+ "{\"index\":\"reg\",\"header\":\"总注册\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内所属所有广告链接的注册总数\"},"
				+ "{\"index\":\"regLogin\",\"header\":\"当日登录\",\"width\":90,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内注册并且在注册当日第一次登陆的账号数\"},"
				+ "{\"index\":\"cpa\",\"header\":\"CPA\",\"width\":80,\"type\":\"number\",\"format\":\"#.##\",\"sortable\":true,\"tips\":\"CPA=注册成本公式为：总消费/总注册\"},"
				+ "{\"index\":\"regRatio\",\"header\":\"注册率\",\"width\":90,\"type\":\"percent\",\"format\":\"#.##%\",\"sortable\":true,\"tips\":\"注册率公式为：总注册数/点击数\"},"
				+ "{\"index\":\"loginPrice\",\"header\":\"登录成本\",\"width\":90,\"type\":\"currency\",\"format\":\"￥#.##\",\"sortable\":true,\"tips\":\"一个玩家当日登陆成本数公式为：总消费/当日登陆\"},"
				+ "{\"index\":\"newReg\",\"header\":\"新帐号\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内新账号注册数\"},"
				+ "{\"index\":\"newRegRatio\",\"header\":\"新帐号比例\",\"width\":90,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"subNewReg\",\"header\":\"次新帐号\",\"width\":90,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内次新账号注册数\"},"
				+ "{\"index\":\"subNewRegRatio\",\"header\":\"次新帐号比例\",\"width\":100,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"oldReg\",\"header\":\"老帐号\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内老账号注册\"},"
				+ "{\"index\":\"oldRegRatio\",\"header\":\"老帐号比例\",\"width\":90,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"newLogin\",\"header\":\"新账号当日登陆\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内新账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"subNewLogin\",\"header\":\"次新账号当日登陆\",\"width\":145,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内次新账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"oldLogin\",\"header\":\"老账号当日登陆\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内老账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"adIdSub\",\"header\":\"广告ID字符串\",\"type\":\"string\"}" + "]}}";
		return josnView;
	}

	/** 明细ID */
	private String initDetailId() {
		final String josnView = "{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"date\",\"header\":\"日期\",\"width\":200,\"type\":\"date\",\"format\":\"yyyy-MM-dd\"},"
				+ "{\"index\":\"type\",\"header\":\"类型\",\"width\":50,\"type\":\"string\"},"
				+ "{\"index\":\"keyName\",\"header\":\"关键字\",\"width\":200,\"type\":\"button\"},"
				+ "{\"index\":\"adIdShow\",\"header\":\"广告ID\",\"width\":80,\"type\":\"number\"},"
				+ "{\"index\":\"reg\",\"header\":\"总注册\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内所属所有广告链接的注册总数\"},"
				+ "{\"index\":\"regLogin\",\"header\":\"当日登录\",\"width\":90,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内注册并且在注册当日第一次登陆的账号数\"},"
				+ "{\"index\":\"newReg\",\"header\":\"新帐号\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内新账号注册数\"},"
				+ "{\"index\":\"newRegRatio\",\"header\":\"新帐号比例\",\"width\":90,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"subNewReg\",\"header\":\"次新账号\",\"width\":90,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内次新账号注册数\"},"
				+ "{\"index\":\"subNewRegRatio\",\"header\":\"次新账号比例\",\"width\":100,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"oldReg\",\"header\":\"老账号\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内老账号注册\"},"
				+ "{\"index\":\"oldRegRatio\",\"header\":\"老账号比例\",\"width\":90,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"newLogin\",\"header\":\"新账号当日登陆\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内新账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"subNewLogin\",\"header\":\"次新账号当日登陆\",\"width\":145,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内次新账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"oldLogin\",\"header\":\"老账号当日登陆\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内老账号注册并在注册当日登陆数\"}"
				+ "]}}";
		return josnView;
	}

	/** 明细Txt */
	private String initDetailTxt() {
		final String josnView = "{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"date\",\"header\":\"日期\",\"width\":200,\"type\":\"date\",\"format\":\"yyyy-MM-dd\",\"tips\":\"日期\"}," // ,\"tips\":\"鼠标悬浮显示\"
				+ "{\"index\":\"type\",\"header\":\"类型\",\"width\":50,\"type\":\"string\"},"
				+ "{\"index\":\"keyName\",\"header\":\"关键字\",\"width\":200,\"type\":\"button\"},"
				+ "{\"index\":\"used\",\"header\":\"总消费\",\"width\":130,\"type\":\"currency\",\"format\":\"￥#.##\",\"sortable\":true,\"tips\":\"统计时间段内总计消费的百度金\"},"
				+ "{\"index\":\"show\",\"header\":\"总展现\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内总计展现次数\"},"
				+ "{\"index\":\"click\",\"header\":\"总点击\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内所属所有广告链接的点击总数\"},"//
				+ "{\"index\":\"clickPrice\",\"header\":\"CPC\",\"width\":90,\"type\":\"currency\",\"format\":\"￥#.##\",\"sortable\":true,\"tips\":\"单点击价格=总消费/总点击\"},"
				+ "{\"index\":\"rank\",\"header\":\"排名\",\"width\":70,\"type\":\"number\",\"format\":\"#.##\",\"sortable\":true,\"tips\":\"这个关键词中展现最高的ID对应的排名\"},"
				+ "{\"index\":\"clickRatio\",\"header\":\"点击率\",\"width\":80,\"type\":\"percent\",\"format\":\"#.##%\",\"sortable\":true,\"tips\":\"点击率=总点击/总展现，百分比显示\"},"
				+ "{\"index\":\"cpm\",\"header\":\"CPM\",\"width\":80,\"type\":\"number\",\"format\":\"#.##\",\"sortable\":true,\"tips\":\"CPM=千次展现价格，公式为：总消费/总展现*1000\"},"
				+ "{\"index\":\"reg\",\"header\":\"总注册\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内所属所有广告链接的注册总数\"},"
				+ "{\"index\":\"regLogin\",\"header\":\"当日登录\",\"width\":90,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内注册并且在注册当日第一次登陆的账号数\"},"
				+ "{\"index\":\"cpa\",\"header\":\"CPA\",\"width\":80,\"type\":\"number\",\"format\":\"#.##\",\"sortable\":true,\"tips\":\"CPA=注册成本公式为：总消费/总注册\"},"
				+ "{\"index\":\"regRatio\",\"header\":\"注册率\",\"width\":90,\"type\":\"percent\",\"sortable\":true,\"format\":\"#.##%\",\"tips\":\"注册率公式为：总注册数/点击数\"},"
				+ "{\"index\":\"loginPrice\",\"header\":\"登录成本\",\"width\":90,\"type\":\"currency\",\"format\":\"￥#.##\",\"sortable\":true,\"tips\":\"一个玩家当日登陆成本数公式为：总消费/当日登陆\"},"
				+ "{\"index\":\"newReg\",\"header\":\"新帐号\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内新账号注册数\"},"
				+ "{\"index\":\"newRegRatio\",\"header\":\"新帐号比例\",\"width\":90,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"subNewReg\",\"header\":\"次新帐号\",\"width\":90,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内次新账号注册数\"},"
				+ "{\"index\":\"subNewRegRatio\",\"header\":\"次新帐号比例\",\"width\":100,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"oldReg\",\"header\":\"老帐号\",\"width\":80,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内老账号注册\"},"
				+ "{\"index\":\"oldRegRatio\",\"header\":\"老帐号比例\",\"width\":90,\"type\":\"percent\",\"format\":\"#.##%\"},"
				+ "{\"index\":\"newLogin\",\"header\":\"新账号当日登陆\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内新账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"subNewLogin\",\"header\":\"次新账号当日登陆\",\"width\":145,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内次新账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"oldLogin\",\"header\":\"老账号当日登陆\",\"width\":130,\"type\":\"number\",\"sortable\":true,\"tips\":\"统计时间段内老账号注册并在注册当日登陆数\"},"
				+ "{\"index\":\"adIdSub\",\"header\":\"广告ID字符串\",\"type\":\"string\"}" + "]}}";
		return josnView;
	}

	/**
	 * 将List<String> 转为 {'str1','str1',~,'strz'}格式
	 * 
	 * @return
	 */
	private String listToString(final List<String> list) {
		String listString = null;
		if (list.size() > 0) {
			if (list.size() == 1) {
				listString = "('" + list.get(0) + "')";
			} else {
				for (int i = 0; i < list.size(); i++) {
					if (i == 0) {
						listString = "('" + list.get(i);
					} else if (i == list.size() - 1) {
						listString += "','" + list.get(i) + "')";
					} else {
						listString += "','" + list.get(i);
					}
				}
			}
		}
		return listString;
	}
}
