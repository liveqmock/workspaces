/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： GroupMgrListView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui;

import java.util.List;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.GroupMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.AddAdGroupDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.InfoDialog;
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

/**
 * 类说明
 * 
 * @简述： 广告组列表视图
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-29 下午03:11:49
 */
public class GroupMgrListView extends SchemaGridView {

	private GroupMgrPresenter presenter;

	/**
	 * @return 获取 presenter
	 */
	public GroupMgrPresenter getPresenter() {
		return presenter;
	}

	/**
	 * @param presenter
	 *            设置 presenter
	 */
	public void setPresenter(final GroupMgrPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	private final PlainObjectSelector<DropDownListData> mediaType;
	private final DropDownField media;
	private TextField key;

	public GroupMgrListView() {
		super(25);
		getPanel().getGrid().setHasCheckBoxColumn(false);
		//  设置 Select 只能选一个
		setSelectMode(SelectMode.Single);

		formPanel = new XFormPanel("广告组列表");
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
				presenter.getMediaType(1, callback);
			}
		});
		layout.add(1, mediaType);
		mediaType.getWidget().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(final ChangeEvent event) {
				int temptype = Integer.parseInt(mediaType.getValue());
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
						for (DropDownListData ddld : result) {
							media.addItem(ddld.getName(), ddld.getId());
						}
					}
				});
			}
		});
		layout.add(2, media);

		key = new TextField("key");
		key.setEnablePlugin(true);
		key.setHeaderPlugin(new FieldHeaderPlugin() {

			@Override
			public Widget getHeader(final Field<?, ?> field) {
				final ListBox listbox = new ListBox();
				listbox.addItem("广告组ID", "id");
				listbox.addItem("广告组名", "name");
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

		ButtonToolItem querybtn = new ButtonToolItem("查询");
		querybtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (formPanel.validate()) {
					BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
					if (key.getFieldName().equals("id") && !key.getValue().equals("")) {
						loadConfig.set("id", key.getValue());
						loadConfig.set("name", "");
					} else if (key.getFieldName().equals("name") && !key.getValue().equals("")) {
						loadConfig.set("id", "");
						loadConfig.set("name", key.getValue());
					} else {
						loadConfig.set("name", "");
						loadConfig.set("id", "");
					}
					
					if(null != mediaType.getValue() && !mediaType.getValue().equals("-1")){
						loadConfig.set("mediaType", mediaType.getValue());
					}else{
						loadConfig.set("mediaType", null);
					}
					
					if (null != media.getValue()) {
						loadConfig.set("media",
								media.getValue().equals("-1") ? "" : media.getValue());
					} else {
						loadConfig.set("media", "");
					}

					getGrid().load();
				}
			}
		});
		ButtonToolItem addBtn = new ButtonToolItem("添加");
		addBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				new AddAdGroupDialog(GroupMgrListView.this).center();
			}
		});

		Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(querybtn);
		topToolbar.addItem(addBtn);
		getPanel().setTopToolBar(topToolbar);

		getPanel().setColumnRenderer("usedTypeShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				String type = object.get("usedType").toString();
				if (type.equals("1")) {
					sb.appendHtmlConstant("购买");
				} else if (type.equals("2")) {
					sb.appendHtmlConstant("配送");
				} else {
					sb.appendHtmlConstant(" ");
				}
			}
		});

		getGrid().setColumnRenderer("adUrlShow", new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						String adUrl = object.get("adUrl").toString();
						if (adUrl.trim().length() == 0) {
							MessageBox.alert("没有链接");
							return;
						}
						new InfoDialog(true,object.get("name") + "的广告链接信息", adUrl).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				String adUrl = rowData.get("adUrl").toString();
				if (adUrl.trim().length() > 0) {
					return "查看";
				} else {
					return null;
				}
			}
		});

		// getGrid().setColumnRenderer("logger", new
		// ButtonColumnRenderer<BaseModelData>() {
		// @Override
		// public boolean isPopupWindow() {
		// return true;
		// }
		//
		// @Override
		// public Delegate<BaseModelData> getDelegate() {
		// return new Delegate<BaseModelData>() {
		// @Override
		// public void execute(final BaseModelData object) {
		// new OperLogListDialog(((Double) object.get("id")).intValue() +
		// "".trim(), "GROUP", 60).center();
		// }
		// };
		// }
		//
		// @Override
		// public String getText(final int column, final int row, final
		// BaseModelData rowData,
		// final ColumnConfig config) {
		// return "查看";
		// }
		//
		// });

		getPanel().setColumnRenderer("materialShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				String materialName = object.get("materialId").toString();
				if (materialName.trim().length() > 0) {
					sb.appendHtmlConstant(materialName);
				} else {
					sb.appendHtmlConstant(" ");
				}
			}
		});
		getPanel().setColumnRenderer("idShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object,
					final ColumnConfig config, final SafeHtmlBuilder sb) {
				Double id = object.get("id");
				sb.appendHtmlConstant(id.intValue() + "");
			}
		});

	}
}
