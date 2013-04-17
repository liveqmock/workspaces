/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： ContractMgrListView.java
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
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.iwgame.gm.p1.adcollect.client.wiget.dialog.OperLogListDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.ContractMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.ContractInfoDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog.OperationContractDialog;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedEvent;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget.DropDownField;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget.NumBerRangeField;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.SchemaGrid.SelectMode;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.Toolbar;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.AppUtils;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * 类说明
 * 
 * @简述： 合同管理
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午05:15:44
 */
public class ContractMgrListView extends SchemaGridView {

	private ContractMgrPresenter presenter;

	/**
	 * @return 获取 presenter
	 */
	public ContractMgrPresenter getPresenter() {
		return presenter;
	}

	/**
	 * @param presenter
	 *            设置 presenter
	 */
	public void setPresenter(final ContractMgrPresenter presenter) {
		super.setPresenter(presenter);
		this.presenter = presenter;
	}

	private XFormPanel formPanel;
	private FormLayout layout;

	private TextField itemno; // 合同编号
	private TextField name; // 合同名
	private TextField applyman; // 申请人
	private PlainObjectSelector<DropDownListData> mediaType;
	private DropDownField media;
	private NumBerRangeField total;
	private DateRangeField time;
	private DateRangeField payTime;

	public ContractMgrListView() {
		super(25);

		getPanel().getGrid().setHasCheckBoxColumn(true);
		//  设置 Select 只能选一个
		setSelectMode(SelectMode.Single);
		formPanel = new XFormPanel("合同列表");
		formPanel.setWidth("100%");
		layout = new FormLayout();
		layout.setColumn(4);
		formPanel.setLayout(layout);

		itemno = new TextField("合同编号");
		layout.add(1, itemno);

		name = new TextField("合同名称");
		layout.add(2, name);

		applyman = new TextField("申请人");
		layout.add(3, applyman);

		total = new NumBerRangeField("合同金额");
		layout.add(4, total);

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

		time = new DateRangeField("合同日期");
		// time.getWidget().setEndDateChangeLinks();
		layout.add(3, time);

		payTime = new DateRangeField("付款日期");
		// payTime.getWidget().setEndDateChangeLinks(5,15);
		layout.add(4, payTime);

		final ButtonToolItem querybtn = new ButtonToolItem("查询");
		querybtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				if (formPanel.validate()) {
					final BaseFilterPagingLoadConfig loadConfig = getGrid().getLoadConfig();
					if (null != itemno.getValue()) {
						loadConfig.set("itemno", itemno.getValue());
					} else {
						loadConfig.set("itemno", null);
					}
					if (null != name.getValue()) {
						loadConfig.set("name", name.getValue());
					} else {
						loadConfig.set("name", null);
					}
					if (null != applyman.getValue()) {
						loadConfig.set("applyman", applyman.getValue());
					} else {
						loadConfig.set("applyman", null);
					}
					if ((null != total.getBeganValue()) && (total.getBeganValue().length() > 0)) {
						loadConfig.set("totalMin", Integer.parseInt(total.getBeganValue()));
					} else {
						loadConfig.set("totalMin", null);
					}
					if ((null != total.getEndValue()) && (total.getEndValue().length() > 0)) {
						loadConfig.set("totalMax", Integer.parseInt(total.getEndValue()));
					} else {
						loadConfig.set("totalMax", null);
					}
					if (null != media.getValue()) {
						loadConfig.set("media", Integer.parseInt(media.getValue()));
					} else {
						loadConfig.set("media", null);
					}
					if (null != time.getStartValue()) {
						loadConfig.set("time",
								DateWrapper.format(time.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						loadConfig.set("time", null);
					}
					if (null != time.getEndValue()) {
						final DateWrapper dateWrapper = new DateWrapper(time.getEndValue());
						loadConfig.set("endTime", DateWrapper.format(dateWrapper.clearTime()
								.addDays(1).asDate(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						loadConfig.set("endTime", null);
					}
					if (null != payTime.getStartValue()) {
						loadConfig.set("payTime",
								DateWrapper.format(payTime.getStartValue(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						loadConfig.set("payTime", null);
					}
					if (null != payTime.getEndValue()) {
						final DateWrapper dateWrapper = new DateWrapper(payTime.getEndValue());
						loadConfig.set("endPayTime", DateWrapper.format(dateWrapper.clearTime()
								.addDays(1).asDate(), "yyyy-MM-dd HH:mm:ss"));
					} else {
						loadConfig.set("endPayTime", null);
					}
					getGrid().load();
				}
			}
		});

		final ButtonToolItem addBtn = new ButtonToolItem("添加");
		addBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				presenter.chenkAddContractAuthority(new AsyncCallbackEx<Void>() {

					@Override
					public void onSuccess(Void result) {
						new OperationContractDialog(true, ContractMgrListView.this).center();
					}
				});
				// 调用添加的Dialog
			}
		});
		final ButtonToolItem updateBtn = new ButtonToolItem("修改");
		updateBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				presenter.chenkUpdateContractAuthority(new AsyncCallbackEx<Void>() {

					@Override
					public void onSuccess(Void result) {
						if ((getPanel().getGrid().getSelected() == null)
								|| getPanel().getGrid().getSelected().isEmpty()) {
							MessageBox.alert("请选择一个合同！");
						} else if (getPanel().getGrid().getSelected().size() > 1) {
							MessageBox.alert("只能选择一个合同修改信息！");
						} else {
							// 调用修改的 Dialog
							new OperationContractDialog(false, ContractMgrListView.this).center();
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
						final Double d = object.get("id");
						new OperLogListDialog(d.intValue() + "", "CONTRACT", 60).center();
					}
				};
			}

			@Override
			public String getText(final int column, final int row, final BaseModelData rowData,
					final ColumnConfig config) {
				return "查看";
			}

		});

		getPanel().setColumnRenderer("info", new ButtonColumnRenderer<BaseModelData>() {

			@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(final BaseModelData object) {
						new ContractInfoDialog(object).center();
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
