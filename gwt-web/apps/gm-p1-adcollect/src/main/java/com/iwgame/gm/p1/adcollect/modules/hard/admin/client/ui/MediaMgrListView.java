/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MediaMgrListView.java
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
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.iwgame.gm.p1.adcollect.client.wiget.dialog.OperLogListDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.MediaMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.InfoDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.OperationMediaDialog;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.SchemaGrid.SelectMode;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
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
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 媒体列表视图
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-26 下午05:02:22
 */
public class MediaMgrListView extends SchemaGridView {

	private MediaMgrPresenter presenter;

	/**
	 * @return 获取 presenter
	 */
	public MediaMgrPresenter getPresenter() {
		return presenter;
	}

	/**
	 * @param presenter
	 *            设置 presenter
	 */
	public void setPresenter(final MediaMgrPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	private PlainObjectSelector<DropDownListData> mediaType;
	private PlainObjectSelector<DropDownListData> mediaSort;
	private TextField key;

	public MediaMgrListView() {
		super(25);
		getPanel().getGrid().setHasCheckBoxColumn(true);
		// 设置 Select 只能选一个
		setSelectMode(SelectMode.Single);
		formPanel = new XFormPanel("媒体列表");
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(3);
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
				presenter.getType(1, callback);
			}
		});
		layout.add(1, mediaType);

		mediaSort = new PlainObjectSelector<DropDownListData>("媒体类型") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		mediaSort.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

			@Override
			public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
				presenter.getType(2, callback);
			}
		});
		layout.add(2, mediaSort);

		key = new TextField("key");
		key.setEnablePlugin(true);
		key.setHeaderPlugin(new FieldHeaderPlugin() {

			@Override
			public Widget getHeader(final Field<?, ?> field) {
				final ListBox listbox = new ListBox();
				listbox.addItem("媒体ID", "id");
				listbox.addItem("媒体名称", "name");
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
		layout.add(3, key);

		final ButtonToolItem querybtn = new ButtonToolItem("查询");
		querybtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (formPanel.validate()) {
					final BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
					if (key.getFieldName().equals("id") && !key.getValue().equals("")) {
						loadConfig.set("id", key.getValue());
						loadConfig.set("name", null);
						loadConfig.set("type", null);
						loadConfig.set("sort", null);
					} else if (key.getFieldName().equals("name") && !key.getValue().equals("")) {
						loadConfig.set("id", null);
						loadConfig.set("name", key.getValue());
						loadConfig.set("type", null);
						loadConfig.set("sort", null);
					} else {
						loadConfig.set("name", null);
						loadConfig.set("id", null);
						loadConfig.set(
								"type",
								Integer.parseInt(mediaType.getValue()) == -1 ? null : Integer.parseInt(mediaType
										.getValue()));
						loadConfig.set(
								"sort",
								Integer.parseInt(mediaSort.getValue()) == -1 ? null : Integer.parseInt(mediaSort
										.getValue()));
					}
					getGrid().load();
				}
			}
		});
		final ButtonToolItem addBtn = new ButtonToolItem("添加");
		addBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				getGrid().getLoadConfig().set("type", null);
				getGrid().getLoadConfig().set("sort", null);
				getGrid().getLoadConfig().set("name", null);
				getGrid().getLoadConfig().set("id", null);
				new OperationMediaDialog(true, MediaMgrListView.this).center();
			}
		});
		final ButtonToolItem updateBtn = new ButtonToolItem("修改");
		updateBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if ((getPanel().getGrid().getSelected() == null) || getPanel().getGrid().getSelected().isEmpty()) {
					MessageBox.alert("请选择一个媒体！");
				} else if (getPanel().getGrid().getSelected().size() > 1) {
					MessageBox.alert("只能选择一个媒体修改信息！");
				} else {
					new OperationMediaDialog(false, MediaMgrListView.this).center();
				}
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
						new OperLogListDialog(object.get("id").toString().trim(), "MEDIA", 60).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
			}

		});

		getGrid().setColumnRenderer("addsShow", new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						new InfoDialog(true, "媒体" + object.get("name") + "的地址", object.get("adds").toString()).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
			}

		});

		getPanel().setColumnRenderer("data2Btn", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						History.newItem("adcollect/admin/netbarip?id=" + object.get("id"));
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return ((Double) rowData.get("data2")).intValue() + "";
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer
			 * #isEnable(int, int, java.lang.Object,
			 * com.iwgame.ui.grid.shared.model.ColumnConfig)
			 */
			@Override
			public boolean isEnable(int column, int row, BaseModelData rowData, ColumnConfig config) {
				return rowData.get("typeStr").equals("网吧类");
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
					if (key.getFieldName().equals("id") && !key.getValue().equals("")) {
						parameters.put("id", key.getValue());
						parameters.put("name", null);
						parameters.put("type", null);
						parameters.put("sort", null);
					} else if (key.getFieldName().equals("name") && !key.getValue().equals("")) {
						parameters.put("id", null);
						parameters.put("name", key.getValue());
						parameters.put("type", null);
						parameters.put("sort", null);
					} else {
						parameters.put("name", null);
						parameters.put("id", null);
						parameters.put("type", mediaType.getValue().equals("-1") ? null : mediaType.getValue());
						parameters.put("sort", mediaSort.getValue().equals("-1") ? null : mediaSort.getValue());
					}
				}
				return parameters;
			}

			/** 指定到出的setvice */
			@Override
			public String getExportBean() {
				return "gm-p1-ad-reports-MediaDataService";
			}

			/** 导出数据列名的映射 */
			@Override
			public Map<String, String> getColumns() {
				final Map<String, String> columns = new LinkedHashMap<String, String>();
				columns.put("typeStr", "媒体分类");
				columns.put("name", "媒体名称");
				columns.put("id", "媒体ID");
				columns.put("sortStr", "媒体类型");
				columns.put("subclass", "媒体子类");
				columns.put("adds", "地址");
				columns.put("data1", "数据1");
				columns.put("data2", "数据2");
				columns.put("data3", "数据3");
				columns.put("data4", "数据4");
				columns.put("count", "广告位数量");
				columns.put("contractCount", "合同数量");
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
				if (mediaSort.getValue().equals("-1")) {
					sb.append(typeString).append("_");
				} else {
					sb.append(mediaSort.getWidget().getItemText(mediaSort.getWidget().getSelectedIndex())).append("_");
				}
				sb.append(DateTimeFormat.getFormat("yyyyMMddHHmmss").format(new Date()));// 导出时间
				return sb.toString();
			}
		});
	}
}
