/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： PositionMgrListView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.iwgame.gm.p1.adcollect.client.wiget.dialog.OperLogListDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.PositionMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.OperationPosDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedEvent;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget.DropDownField;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.SchemaGrid.SelectMode;
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
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FieldHeaderPlugin;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.AppUtils;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述：广告位列表视图
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-8 下午12:20:44
 */
public class PositionMgrListView extends SchemaGridView {

	private PositionMgrPresenter presenter;

	/**
	 * @return 获取 presenter
	 */
	public PositionMgrPresenter getPresenter() {
		return presenter;
	}

	/**
	 * @param presenter
	 *            设置 presenter
	 */
	public void setPresenter(final PositionMgrPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	private final PlainObjectSelector<DropDownListData> mediaType;
	private final DropDownField media;
	private DropDownField units;
	private TextField key;

	public PositionMgrListView() {
		super(25);
		getPanel().getGrid().setHasCheckBoxColumn(true);
		// 设置 Select 只能选一个
		setSelectMode(SelectMode.Single);
		formPanel = new XFormPanel("广告位列表");
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(4);
		formPanel.setLayout(layout);

		mediaType = new PlainObjectSelector<DropDownListData>("媒体分类") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		mediaType.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

			@Override
			public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
				presenter.getMediaType(1, callback);
			}
		});
		layout.add(1, mediaType);
		mediaType.getWidget().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(final ChangeEvent event) {
				final int temptype = Integer.parseInt(mediaType.getValue());
				AppUtils.EVENT_BUS.fireEvent(new MediaSelectedEvent(temptype));
			}
		});
		media = new DropDownField("媒体") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		media.addItem("---请先选择媒体分类---", "-1");
		media.setEnablePlugin(true);
		media.addMedisSelectedHandlers(new MediaSelectedHandler() {

			@Override
			public void selectMedia(final MediaSelectedEvent event) {
				presenter.getMedia(event.getType(), new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						media.getWidget().clear();
						media.addItem("--请选择--", "-1");
						for (final DropDownListData ddld : result) {
							media.addItem(ddld.getName(), ddld.getId());
						}
					}
				});
			}
		});
		layout.add(2, media);

		units = new DropDownField("售卖单位", "units") {

			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		units.addItem("---请先选择媒体分类---", "-1");
		units.setEnablePlugin(true);
		units.addMedisSelectedHandlers(new MediaSelectedHandler() {

			@Override
			public void selectMedia(final MediaSelectedEvent event) {
				if (event.getType() == 2) {
					presenter.getUnitsIsNetBar(new AsyncCallbackEx<List<DropDownListData>>() {

						@Override
						public void onSuccess(final List<DropDownListData> result) {
							units.getWidget().clear();
							units.addItem("--请选择--", "-1");
							for (final DropDownListData ddld : result) {
								units.addItem(ddld.getName(), ddld.getId());
							}
						}
					});
				} else {
					presenter.getMediaType(5, new AsyncCallbackEx<List<DropDownListData>>() {

						@Override
						public void onSuccess(final List<DropDownListData> result) {
							units.getWidget().clear();
							units.addItem("--请选择--", "-1");
							for (final DropDownListData ddld : result) {
								units.addItem(ddld.getName(), ddld.getId());
							}
						}
					});
				}
			}
		});
		layout.add(3, units);

		key = new TextField("key");
		key.setEnablePlugin(true);
		key.setHeaderPlugin(new FieldHeaderPlugin() {

			@Override
			public Widget getHeader(final Field<?, ?> field) {
				final ListBox listbox = new ListBox();
				listbox.addItem("广告位ID", "id");
				listbox.addItem("广告位名", "name");
				listbox.addItem("广告ID", "adid");
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
		layout.add(4, key);

		final ButtonToolItem querybtn = new ButtonToolItem("查询");
		querybtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (formPanel.validate()) {

					final BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
					if (key.getValue().equals("")) {
						loadConfig.set("key", null);
					} else {
						loadConfig.set("key", "notNull");
						if (key.getFieldName().equals("id")) {
							loadConfig.set("id", key.getValue());
							loadConfig.set("adId", null);
							loadConfig.set("name", null);
						} else if (key.getFieldName().equals("name")) {
							loadConfig.set("id", null);
							loadConfig.set("name", key.getValue());
							loadConfig.set("adId", null);
						} else if (key.getFieldName().equals("adid")) {
							loadConfig.set("id", null);
							loadConfig.set("name", null);
							loadConfig.set("adId", key.getValue());
						} else {
							loadConfig.set("id", null);
							loadConfig.set("name", null);
							loadConfig.set("adId", null);
						}
					}
					if (!units.getValue().equals("-1")) {
						loadConfig.set("units", Integer.parseInt(units.getValue()));
					} else {
						loadConfig.set("units", null);
					}
					if (null != mediaType.getValue() && !mediaType.getValue().equals("-1")) {
						loadConfig.set("mediaType", mediaType.getValue());
					} else {
						loadConfig.set("mediaType", null);
					}
					if ((null != media.getValue()) && !media.getValue().equals("-1")) {
						loadConfig.set("media", media.getValue());
					} else {
						loadConfig.set("media", null);
					}
					getGrid().load();
				}
			}
		});

		final ButtonToolItem addBtn = new ButtonToolItem("添加");
		addBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {

				presenter.chenkAddPosAuthority(new AsyncCallbackEx<Void>() {

					@Override
					public void onSuccess(Void result) {
						new OperationPosDialog(true, PositionMgrListView.this).center();
					}
				});
				// 调用添加的Dialog
			}
		});
		final ButtonToolItem updateBtn = new ButtonToolItem("修改");
		updateBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				presenter.chenkUpdatePosAuthority(new AsyncCallbackEx<Void>() {

					@Override
					public void onSuccess(Void result) {
						if ((getPanel().getGrid().getSelected() == null)
								|| getPanel().getGrid().getSelected().isEmpty()) {
							MessageBox.alert("请选择一个广告位！");
						} else if (getPanel().getGrid().getSelected().size() > 1) {
							MessageBox.alert("只能选择一个广告位修改信息！");
						} else {
							// 调用修改的 Dialog
							new OperationPosDialog(false, PositionMgrListView.this).center();
						}
					}
				});
			}
		});

		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(querybtn);
		topToolbar.addItem(addBtn);
		topToolbar.addItem(updateBtn);
		getPanel().setTopToolBar(topToolbar);

		getGrid().setColumnRenderer("logger", new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						new OperLogListDialog(object.get("id").toString().trim(), "POS", 60).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
			}

		});

		// typeShow
		getPanel().setColumnRenderer("typeShow", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				final Double d = object.get("type");
				if (d.intValue() == 1) {
					sb.appendHtmlConstant("客户端推送");
				} else if (d.intValue() == 3) {
					sb.appendHtmlConstant("推送相关位置");
				} else {
					sb.appendHtmlConstant("独立广告位");
				}
			}
		});

		// 导出数据
		getPanel().enableExport(true);
		getPanel().setExportButton(new ExportButton(getPanel()) {

			/** 导出条件 */
			@Override
			public Map<String, String> getParameters() {
				final Map<String, String> parameters = new HashMap<String, String>();
				if (formPanel.validate()) {
					parameters.put("productId", ApplicationContext.getCurrentProductId());
					if (key.getValue().equals("")) {
						parameters.put("key", null);
					} else {
						parameters.put("key", "notNull");
						if (key.getFieldName().equals("id")) {
							parameters.put("id", key.getValue());
							parameters.put("adId", null);
							parameters.put("name", null);
						} else if (key.getFieldName().equals("name")) {
							parameters.put("id", null);
							parameters.put("name", key.getValue());
							parameters.put("adId", null);
						} else if (key.getFieldName().equals("adid")) {
							parameters.put("id", null);
							parameters.put("name", null);
							parameters.put("adId", key.getValue());
						} else {
							parameters.put("id", null);
							parameters.put("name", null);
							parameters.put("adId", null);
						}
					}
					if (!units.getValue().equals("-1")) {
						parameters.put("units", units.getValue());
					} else {
						parameters.put("units", null);
					}
					if (null != mediaType.getValue() && !mediaType.getValue().equals("-1")) {
						parameters.put("mediaType", mediaType.getValue());
					} else {
						parameters.put("mediaType", null);
					}
					if ((null != media.getValue()) && !media.getValue().equals("-1")) {
						parameters.put("media", media.getValue());
					} else {
						parameters.put("media", null);
					}
				}
				return parameters;
			}

			/** 指定到出的setvice */
			@Override
			public String getExportBean() {
				return "gm-p1-ad-reports-posDataService";
			}

			/** 导出数据列名的映射 */
			@Override
			public Map<String, String> getColumns() {
				final Map<String, String> columns = new LinkedHashMap<String, String>();
				columns.put("mediaName", "所属媒体");
				columns.put("name", "广告位名");
				columns.put("id", "广告位ID");
				columns.put("type", "广告位类型");
				columns.put("adId", "对应广告ID");
				columns.put("link", "广告位图列链接");
				columns.put("unitsName", "售卖单位");
				columns.put("generalPrice", "单位原价一般日");
				columns.put("specialPrice", "单位原价特殊日");
				columns.put("format", "广告位规格");
				columns.put("explain", "广告位说明");
				columns.put("remark", "备注");
				columns.put("addTime", "添加日期");
				columns.put("updateTime", "修改日期");
				return columns;
			}

			/** 生成报名名成 */
			@Override
			public String getFilename() {
				final StringBuilder sb = new StringBuilder();
				String typeString = "所有媒体";
				if (mediaType.getValue().equals("-1")) {
					sb.append(typeString).append("_");
				} else {
					sb.append(mediaType.getWidget().getItemText(mediaType.getWidget().getSelectedIndex())).append("_");
				}
				if (media.getValue().equals("-1")) {
					sb.append(typeString).append("_");
				} else {
					sb.append(media.getWidget().getItemText(media.getWidget().getSelectedIndex())).append("_");
				}
				sb.append(DateTimeFormat.getFormat("yyyyMMddHHmmss").format(new Date()));// 导出时间
				return sb.toString();
			}
		});
	}
}
