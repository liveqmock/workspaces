/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： UseMgrView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.frame.client.ui;

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
import com.iwgame.gm.p1.adcollect.modules.frame.client.presenter.UseMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.frame.client.ui.dialog.OperationUseDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.InfoDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedEvent;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget.DropDownField;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.ExportButton;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.MessageBoxEvent;
import com.iwgame.ui.panel.client.box.MessageBoxHandler;
import com.iwgame.ui.panel.client.box.XButton;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.AppUtils;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @Description: 使用框架视图类
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-17 下午12:04:14
 */
public class UseMgrView extends SchemaGridView {

	private UseMgrPresenter presenter;

	public UseMgrPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(UseMgrPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	/** // 媒体分类 */
	private final PlainObjectSelector<DropDownListData> mediaType;
	/** // 媒体 */
	private final DropDownField media;
	/** // 框架名 */
	private final TextField nameField;

	public UseMgrView(final String name) {
		// 加载选择框
		getPanel().getGrid().setHasCheckBoxColumn(true);

		formPanel = new XFormPanel("使用管理");
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(2);
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
				presenter.getMediaType(1, new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(List<DropDownListData> result) {
						callback.onSuccess(result);
					}
				});
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

		nameField = new TextField("框架名称");
		layout.add(1, nameField);

		if(null != name)
			nameField.setValue(name);
		
		ButtonToolItem queryBtn = new ButtonToolItem("查询");
		queryBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
				if(!nameField.getValue().equals("")){
					loadConfig.set("name", nameField.getValue());
				}else{
					loadConfig.set("name", null);
				}
				loadConfig.set("media", media.getValue());
				loadConfig.set("mediaType", mediaType.getValue());
				
				getGrid().load(loadConfig);
			}
		});
		
		ButtonToolItem addBtn = new ButtonToolItem("使用框架");
		addBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				new OperationUseDialog(UseMgrView.this).center();
			}
		});
		ButtonToolItem delBtn = new ButtonToolItem("删除");
		delBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (!getGrid().getSelected().isEmpty()) {
					MessageBox.confirm("您确定需要删除所选的用户吗？", new MessageBoxHandler() {

						@Override
						public void onClose(MessageBoxEvent event) {
							XButton btn = event.getButton();
							if (btn.getItemId().equals(XDialogBox.YES)) {
								StringBuilder sb = new StringBuilder();
								for (BaseModelData data : getGrid().getSelected()) {
									Double id = data.get("id");
									sb.append(id.intValue()).append(",");
								}

								sb.deleteCharAt(sb.length() - 1);
								presenter.delUseFrameByIds(sb.toString(), new AsyncCallbackEx<Integer>() {

									@Override
									public void onSuccess(Integer result) {
										if (result == getGrid().getSelected().size()) {
											getGrid().load();
										} else {
											MessageBox.alert("删除出现错误");
										}
									}
								});
							}
						}
					});
				} else {
					MessageBox.alert("请选择需要删除的用户！");
				}
			}
		});
		
		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(queryBtn);
		topToolbar.addItem(addBtn);
		topToolbar.addItem(delBtn);
		getPanel().setTopToolBar(topToolbar);

		// 导出数据
		getPanel().enableExport(true);
		getPanel().setExportButton(new ExportButton(getPanel()) {

			/** 导出条件 */
			@Override
			public Map<String, String> getParameters() {
				final Map<String, String> parameters = new HashMap<String, String>();
				parameters.put("productId", ApplicationContext.getCurrentProductId());
				if(!nameField.getValue().equals("")){
					parameters.put("name", nameField.getValue());
				}else{
					parameters.put("name", null);
				}
				parameters.put("media", media.getValue());
				parameters.put("mediaType", mediaType.getValue());

				return parameters;
			}

			/** 指定到出的setvice */
			@Override
			public String getExportBean() {
				return "gm-p1-ad-useframemgrservice";
			}

			/** 导出数据列名的映射 */
			@Override
			public Map<String, String> getColumns() {
				final Map<String, String> columns = new LinkedHashMap<String, String>();
				columns.put("mediaName","媒体");
				columns.put("frameName","框架名称");
				columns.put("updateTime","变更日期");
				columns.put("changes","变更情况");
				columns.put("changeAmount","变更金额");
				columns.put("balance","框架余额");
				columns.put("contractItmo","关联合同");
				columns.put("effectTracking","效果追踪");
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
		
		getPanel().setColumnRenderer("infoBtn", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						new  InfoDialog(false, "使用详情", object.get("usedDetails").toString()).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "详细";
			}
		});
		
		getPanel().setColumnRenderer("sheduleBtn", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						History.newItem("report/viewer/rpt_hardads_day.1.0?key = "+object.get("effectTracking"));
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "详细";
			}
		});
		
	}
	

}
