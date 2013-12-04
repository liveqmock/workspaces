/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： SheduleMgrListView.java
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
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.SheduleMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.InfoDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.OperationSheduleDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.UpdateSheduleDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedEvent;
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
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestField;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestOracle;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FieldHeaderPlugin;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.ui.panel.client.form.field.events.CascadingListEvent;
import com.iwgame.ui.panel.client.form.field.events.CascadingListEvent.CascadingListHandler;
import com.iwgame.xmvp.client.utils.AppUtils;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述：广告排期列表视图
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 上午09:56:24
 */
public class SheduleMgrListView extends SchemaGridView {

	private SheduleMgrPresenter presenter;

	/**
	 * @return 获取 presenter
	 */
	public SheduleMgrPresenter getPresenter() {
		return presenter;
	}

	/**
	 * @param presenter
	 *            设置 presenter
	 */
	public void setPresenter(final SheduleMgrPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;
	private XLinkageSelector mediaType;
	private XLinkageSelector media;
	private XLinkageSelector position;// 广告位
	private AsyncSuggestField<DropDownListData> contractId;
	private TextField group;
	private TextField ad;
	private DateRangeField time;

	private Map<String, Object> posMap;

	public SheduleMgrListView(final String sid) {
		super(25);
		getPanel().getGrid().setHasCheckBoxColumn(true);
		// 设置 Select 只能选一个
		setSelectMode(SelectMode.Single);
		formPanel = new XFormPanel("广告排期列表");
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(4);
		formPanel.setLayout(layout);

		mediaType = new XLinkageSelector("媒体分类", "mediaType");

		mediaType.getWidget().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(final ChangeEvent event) {
				final int temptype = Integer.parseInt(mediaType.getValue());
				AppUtils.EVENT_BUS.fireEvent(new MediaSelectedEvent(temptype));
			}
		});
		media = new XLinkageSelector("媒体", "mediaId") {

			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		media.addItem("---请先选择媒体类别---", "-1");
		media.addCascadingListHandler(new CascadingListHandler() {

			@Override
			public void onCascading(final CascadingListEvent event) {
				String typeId = event.getValue().toString();
				Integer iTypeId = Integer.parseInt(typeId);
				presenter.getMedia(iTypeId, new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						media.getWidget().clear();
						media.addItem("---选择媒体---", "-1");
						media.addItems(result);
					}
				});
			}
		});
		media.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (media.getValue().equals("-1")) {
					return "前选择媒体";
				}
				return null;
			}
		});

		position = new XLinkageSelector("广告位", "position") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		position.addItem("---请先选择媒体---", "-1");
		position.addCascadingListHandler(new CascadingListHandler() {

			@Override
			public void onCascading(final CascadingListEvent event) {
				String mediaId = event.getValue().toString();
				presenter.getPosition(mediaId, new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						position.getWidget().clear();
						position.addItem("---选择广告位---", "-1");
						position.addItems(result);
						posMap = new HashMap<String, Object>();
						for (DropDownListData d : result) {
							posMap.put(d.getId(), d.getGenerate());
						}
					}
				});
			}
		});
		position.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (position.getValue().equals("-1")) {
					return "请选择广告位";
				}
				return null;
			}
		});
		mediaType.addCascadeSelector(media);
		media.addCascadeSelector(position);
		layout.add(1, mediaType);
		layout.add(2, media);
		layout.add(3, position);

		contractId = new AsyncSuggestField<DropDownListData>("合同", new AsyncSuggestOracle<DropDownListData>() {

			@Override
			public void getCandidatesFromQuery(final String query, final AsyncCallback<List<DropDownListData>> callback) {
				presenter.autoContractId(query, callback);
			}

			@Override
			public String buildDisplayString(final DropDownListData candidate) {
				return "<span><strong>ID：</strong>" + candidate.getId() + "<span> " + "<span><strong>编码：</strong>"
						+ candidate.getGenerate() + "<span> ";
			}

			@Override
			public String buildReplaceString(final DropDownListData candidate) {
				return candidate.getId();
			}
		});
		layout.add(4, contractId);

		group = new TextField("group");
		group.setEnablePlugin(true);
		group.setHeaderPlugin(new FieldHeaderPlugin() {

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
						group.setValue("");
					}
				});
				return listbox;
			}
		});
		layout.add(1, group);

		ad = new TextField("ad");
		ad.setEnablePlugin(true);
		ad.setHeaderPlugin(new FieldHeaderPlugin() {

			@Override
			public Widget getHeader(final Field<?, ?> field) {
				final ListBox listbox = new ListBox();
				listbox.addItem("广告ID", "id");
				listbox.addItem("广告名", "name");
				field.setFieldName("id");
				listbox.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(final ChangeEvent event) {
						field.setFieldName(listbox.getValue(listbox.getSelectedIndex()));
						ad.setValue("");
					}
				});
				return listbox;
			}
		});
		layout.add(2, ad);

		time = new DateRangeField("开始日期");
		// time.getWidget().setEndDateChangeLinks();
		time.setEndValue(new Date());
		time.setColSpan(2);
		layout.add(3, time);

		// 、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、
		final ButtonToolItem querybtn = new ButtonToolItem("查询");
		querybtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				final BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();

				if (null != mediaType.getValue() && !mediaType.getValue().equals("-1")) {
					loadConfig.set("mediaType", mediaType.getValue());
				} else {
					loadConfig.set("mediaType", null);
				}

				if ((null != media.getValue()) && !media.getValue().equals("-1")) {
					loadConfig.set("mediaId", media.getValue());
				} else {
					loadConfig.set("mediaId", null);
				}

				if (!position.getValue().equals("-1")) {
					loadConfig.set("positionId", position.getValue());
				} else {
					loadConfig.set("positionId", null);
				}
				if ((null != contractId.getValue()) && (contractId.getValue().trim().length() > 0)) {
					loadConfig.set("contractId", contractId.getValue());
				} else {
					loadConfig.set("contractId", null);
				}

				if (group.getFieldName().equals("id") && (group.getValue().length() > 0)) {
					loadConfig.set("groupId", group.getValue());
					loadConfig.set("groupName", null);
				} else if (group.getFieldName().equals("name") && (group.getValue().length() > 0)) {
					loadConfig.set("groupId", null);
					loadConfig.set("groupName", group.getValue());
				} else {
					loadConfig.set("groupId", null);
					loadConfig.set("groupName", null);
				}
				if (ad.getFieldName().equals("id") && (ad.getValue().length() > 0)) {
					loadConfig.set("adId", ad.getValue());
					loadConfig.set("adName", null);
				} else if (ad.getFieldName().equals("name") && (ad.getValue().length() > 0)) {
					loadConfig.set("adId", null);
					loadConfig.set("adName", ad.getValue());
				} else {
					loadConfig.set("adId", null);
					loadConfig.set("adName", null);
				}
				if (null != time.getStartValue()) {
					loadConfig.set("startTime", DateWrapper.format(time.getStartValue(), "yyyy-MM-dd"));
				} else {
					loadConfig.set("startTime", null);
				}
				if (null != time.getEndValue()) {
					final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
					loadConfig.set("endTime",
							DateWrapper.format(dateWrapper.clearTime().addDays(1).asDate(), "yyyy-MM-dd"));
				} else {
					loadConfig.set("endTime", null);
				}

				getGrid().load();
			}
		});

		final ButtonToolItem addBtn = new ButtonToolItem("添加");
		addBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				// 调用添加的Dialog
				new OperationSheduleDialog(SheduleMgrListView.this).center();
			}
		});
		final ButtonToolItem updateBtn = new ButtonToolItem("修改");
		updateBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if ((getPanel().getGrid().getSelected() == null) || getPanel().getGrid().getSelected().isEmpty()) {
					MessageBox.alert("请选择一个广告排期！");
				} else if (getPanel().getGrid().getSelected().size() > 1) {
					MessageBox.alert("只能选择一个广告排期修改信息！");
				} else {
					// 调用修改的 Dialog
					new UpdateSheduleDialog(SheduleMgrListView.this).center();
				}
			}
		});

		final Toolbar topToolbar = new Toolbar();
		topToolbar.addItem(formPanel);
		topToolbar.addItem(querybtn);
		topToolbar.addItem(addBtn);
		topToolbar.addItem(updateBtn);
		getPanel().setTopToolBar(topToolbar);

		// ////////
		// 渲染
		// ////////
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
						new OperLogListDialog(object.get("id").toString(), "SHEDULE", 160).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
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
						final String adUrl = object.get("adUrl").toString();
						new InfoDialog(true, object.get("name") + "的广告链接信息", adUrl).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
			}
		});
		getGrid().setColumnRenderer("sourceShow", new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {
					@Override
					public void execute(final BaseModelData object) {
						final String source = object.get("source").toString();
						new InfoDialog(false, object.get("name") + "的配送来源信息", source).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				if (rowData.get("source").toString().trim().length() > 0) {
					return "查看";
				} else {
					return null;
				}
			}
		});

		getPanel().setColumnRenderer("usedTypeShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				final String type = object.get("usedType").toString();
				if (type.equals("1")) {
					sb.appendHtmlConstant("购买");
				} else if (type.equals("2")) {
					sb.appendHtmlConstant("配送");
				} else {
					sb.appendHtmlConstant("------");
				}
			}
		});

		getPanel().setColumnRenderer("typesShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				final Double type = object.get("types");
				if (type.intValue() == 1) {
					sb.appendHtmlConstant("正式");
				} else if (type.intValue() == 2) {
					sb.appendHtmlConstant("赠送");
				} else if (type.intValue() == 3) {
					sb.appendHtmlConstant("补量");
				} else if (type.intValue() == 4) {
					sb.appendHtmlConstant("测试");
				} else {
					sb.appendHtmlConstant("------");
				}
			}
		});
		getPanel().setColumnRenderer("materialShow", new ColumnRenderer<BaseModelData>() {
			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				final String materialName = object.get("materialName").toString();
				if (materialName.trim().length() == 0) {
					sb.appendHtmlConstant("<font color='red'>未关联素材</font>");
				} else {
					sb.appendHtmlConstant(materialName);
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
				// 跳转到页面的默认参数
				parameters.put("sid", sid);
				parameters.put("productId", ApplicationContext.getCurrentProductId());
				if (null != mediaType.getValue() && !mediaType.getValue().equals("-1")) {
					parameters.put("mediaType", mediaType.getValue());
				} else {
					parameters.put("mediaType", null);
				}
				
				if ((null != media.getValue()) && !media.getValue().equals("-1")) {
					parameters.put("mediaId", media.getValue());
				} else {
					parameters.put("mediaId", null);
				}

				if (!position.getValue().equals("-1")) {
					parameters.put("positionId", position.getValue());
				} else {
					parameters.put("positionId", null);
				}
				if ((null != contractId.getValue()) && (contractId.getValue().trim().length() > 0)) {
					parameters.put("contractId", contractId.getValue());
				} else {
					parameters.put("contractId", null);
				}

				if (group.getFieldName().equals("id") && (group.getValue().length() > 0)) {
					parameters.put("groupId", group.getValue());
					parameters.put("groupName", null);
				} else if (group.getFieldName().equals("name") && (group.getValue().length() > 0)) {
					parameters.put("groupId", null);
					parameters.put("groupName", group.getValue());
				} else {
					parameters.put("groupId", null);
					parameters.put("groupName", null);
				}
				if (ad.getFieldName().equals("id") && (ad.getValue().length() > 0)) {
					parameters.put("adId", ad.getValue());
					parameters.put("adName", null);
				} else if (ad.getFieldName().equals("name") && (ad.getValue().length() > 0)) {
					parameters.put("adId", null);
					parameters.put("adName", ad.getValue());
				} else {
					parameters.put("adId", null);
					parameters.put("adName", null);
				}
				if (null != time.getStartValue()) {
					parameters.put("startTime", DateWrapper.format(time.getStartValue(), "yyyy-MM-dd"));
				} else {
					parameters.put("startTime", null);
				}
				if (null != time.getEndValue()) {
					final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
					parameters.put("endTime",
							DateWrapper.format(dateWrapper.clearTime().addDays(1).asDate(), "yyyy-MM-dd"));
				} else {
					parameters.put("endTime", null);
				}
				return parameters;
			}

			/** 指定到出的setvice */
			@Override
			public String getExportBean() {
				return "gm-p1-ad-reports-sheduleDataService";
			}

			/** 导出数据列名的映射 */
			@Override
			public Map<String, String> getColumns() {
				final Map<String, String> columns = new LinkedHashMap<String, String>();
				columns.put("mediaName", "所属媒体");
				columns.put("posName", "广告位名");
				columns.put("adId", "广告ID");
				columns.put("materialShow", "素材名称");
				columns.put("contractItemno", "所属合同");
				columns.put("name", "排期名称");
				columns.put("startTime", "开始时间");
				columns.put("endTime", "结束时间");
				columns.put("id", "排期码");
				columns.put("groupName", "所属广告组");
				columns.put("rebate", "折扣");
				columns.put("unitsName", "实际售卖单位");
				columns.put("generalPrice", "普通日价格");
				columns.put("specialPrice", "特殊日价格");
				columns.put("usedTypeShow", "消费类型");
				columns.put("distribuRatio", "配送比");
				columns.put("typesShow", "类型");
				columns.put("adUrlShow", "广告链接");
				columns.put("sourceShow", "配送来源");
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

	@Override
	protected void onAttach() {
		presenter.getMediaType(1, new AsyncCallbackEx<List<DropDownListData>>() {

			@Override
			public void onSuccess(final List<DropDownListData> result) {
				mediaType.getWidget().clear();
				mediaType.addItem("---选择媒体分类---", "-1");
				mediaType.addItems(result);
			}
		});
		super.onAttach();
	}

	public class XLinkageSelector extends PlainObjectSelector<DropDownListData> {

		public XLinkageSelector(final String label, final String fieldName) {
			super(label);
			setFieldName(fieldName);
		}

		@Override
		protected String getValue(final DropDownListData t) {
			return t.getId();
		}

		@Override
		protected String getLabel(final DropDownListData t) {
			return t.getName();
		}

	}
}
