/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LandingView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui;

import java.util.Date;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.MaterialMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.MaterialAddDialog;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.client.util.Format;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.LinkColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FieldHeaderPlugin;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * 类说明
 * 
 * @简述： 素材列表视图
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-21 下午04:18:29
 */
public class MaterialMgrView extends SchemaGridView {

	private MaterialMgrPresenter presenter;

	/**
	 * @return the presenter
	 */
	public MaterialMgrPresenter getPresenter() {
		return presenter;
	}

	/**
	 * @param presenter
	 *            the presenter to set
	 */
	public void setPresenter(final MaterialMgrPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	private PlainObjectSelector<DropDownListData> materialType = null;
	private TextField key;
	private DateRangeField time;

	public MaterialMgrView() {
		super(25);

		formPanel = new XFormPanel("素材列表");
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(3);
		formPanel.setLayout(layout);

		materialType = new PlainObjectSelector<DropDownListData>("素材类型") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};

		materialType.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

			@Override
			public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
				presenter.getMaterialType(3, callback);
			}
		});

		layout.add(1, materialType);

		key = new TextField("关键字");
		// key.setAllowBlank(false);
		// key.setValidateOnBlur(true);
		key.setEnablePlugin(true);
		key.setHeaderPlugin(new FieldHeaderPlugin() {

			@Override
			public Widget getHeader(final Field<?, ?> field) {
				final ListBox listbox = new ListBox();
				listbox.addItem("素材名称", "name");
				listbox.addItem("素材ID", "id");
				field.setFieldName("name");
				listbox.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(final ChangeEvent event) {
						field.setFieldName(listbox.getValue(listbox.getSelectedIndex()));
					}
				});
				return listbox;
			}
		});
		layout.add(2, key);

		time = new DateRangeField("上传时间");
		time.getWidget().setEndDateChangeLinks();
		time.setEndValue(new Date());
		layout.add(3, time);

		final ButtonToolItem query = new ButtonToolItem("查看");

		query.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {

				if (formPanel.validate()) {
					final BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
					if (key.getFieldName().equals("id") && !key.getValue().equals("")) {
						loadConfig.set("id", key.getValue());
						loadConfig.set("name", null);
					} else if (key.getFieldName().equals("name") && !key.getValue().equals("")) {
						loadConfig.set("id", null);
						loadConfig.set("name", key.getValue());
					} else {
						loadConfig.set("name", null);
						loadConfig.set("id", null);
						loadConfig.set("type", Integer.parseInt(materialType.getValue()));
					}
					if (null != time.getStartValue()) {
						loadConfig.set("startTime",
								DateWrapper.format(time.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						loadConfig.set("startTime", null);
					}
					if (null != time.getEndValue()) {
						final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
						loadConfig.set("endTime", DateWrapper.format(dateWrapper.clearTime()
								.addDays(1).asDate(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						loadConfig.set("endTime", null);
					}
					getGrid().load();
				}
			}
		});
		final ButtonToolItem addBtn = new ButtonToolItem("新加");

		addBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(final ClickEvent event) {
				presenter.chenkAddMaterialAuthority(new AsyncCallbackEx<Void>() {

					@Override
					public void onSuccess(Void result) {
						new MaterialAddDialog(MaterialMgrView.this).center();
					}
				});
			}
		});

		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(query);
		topToolbar.addItem(addBtn);
		getPanel().setTopToolBar(topToolbar);

		getPanel().setColumnRenderer("app", new LinkColumnRenderer<BaseModelData>() {

			@Override
			public String getHref(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "#adcollect/admin/shedule?id=" + rowData.get("id").toString();
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
			}

		});

		getPanel().setColumnRenderer("capacity", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				final long size = object.<Double> get(config.getIndex()).longValue();
				sb.append(SafeHtmlUtils.fromString(Format.fileSize(size)));
			}
		});
		getPanel().setColumnRenderer("width", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				final Double d = object.get("width");
				if (d.intValue() == 0) {
					sb.appendHtmlConstant("&minus;");
				} else {
					sb.appendHtmlConstant(d.intValue() + "");
				}
			}
		});
		getPanel().setColumnRenderer("height", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				final Double d = object.get("height");
				if (d.intValue() == 0) {
					sb.appendHtmlConstant("&minus;");
				} else {
					sb.appendHtmlConstant(d.intValue() + "");
				}
			}
		});
	}
}
