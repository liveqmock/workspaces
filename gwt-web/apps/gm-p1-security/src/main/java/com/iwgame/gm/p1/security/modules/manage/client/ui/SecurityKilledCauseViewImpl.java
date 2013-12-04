/**      
* KilledCause.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 
package com.iwgame.gm.p1.security.modules.manage.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.security.common.client.ui.OperLogListDialog;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.gm.p1.security.common.shared.util.ConstantShared;
import com.iwgame.gm.p1.security.common.shared.util.DataConverter;
import com.iwgame.gm.p1.security.common.shared.util.PathBuilder;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledCauseBox;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledCauseType;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledType;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledTypeBox;
import com.iwgame.gm.p1.security.modules.manage.client.ui.xdialog.ModifyKilledCauseDialog;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;
/** 
 * @简述: 封杀原因view实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 */
public class SecurityKilledCauseViewImpl extends Composite implements SecurityKilledCauseView {

	private int initPageSize;
	private boolean isLoadOnAttach;
	
	private XFormPanel formpanel;
	private FormLayout layout;
	private SchemaGridView killedCauseGrid;
	private ButtonToolItem loadDataBtn;
	private ButtonToolItem addCauseBtn;
	private ButtonToolItem updateCauseBtn;
	private ButtonToolItem delCauseBtn;
	
	private SecurityKilledCauseBox causeTypeBox;
	private SecurityKilledTypeBox killedTypeBox;
	private TextField number;
	private VerticalPanel vPanel;
	
	private KilledCausePresenter presenter;
	
	private ModifyKilledCauseDialog killedCauseDialog;
	
	private String productId;
	
	public SecurityKilledCauseViewImpl(KilledCausePresenter presenter,int pageSize,boolean isLoadOnAttach, String productId){
		this.initPageSize=pageSize;
		this.isLoadOnAttach=isLoadOnAttach;
		this.presenter = presenter;
		this.productId = productId;
		// 初始化页面
		initView();
	}
	
	
	
	private void initView() {
		//主面板
		 vPanel = new VerticalPanel();
		 
		// 装配
		formpanel = new XFormPanel();
		formpanel.setButtonAlign(HasHorizontalAlignment.ALIGN_LEFT);
		
		layout = new FormLayout();
		layout.setWidth(700);
		layout.setLabelWidth(100);
		layout.setColumn(3);
		layout.setLabelAlign(HasHorizontalAlignment.ALIGN_LEFT);
		
		
		causeTypeBox = new SecurityKilledCauseBox("原因分类");
		killedTypeBox = new SecurityKilledTypeBox("封杀类型");
		number = new TextField("方案编号");
		layout.add(causeTypeBox);
		layout.add(2, killedTypeBox);
		layout.add(3, number);
		addCauseBtn = new ButtonToolItem("新增");
		updateCauseBtn = new ButtonToolItem("修改");
		delCauseBtn = new ButtonToolItem("删除");
		loadDataBtn = new ButtonToolItem("查询");
		
		formpanel.setLayout(layout);
		
		//初始化grid
		initGridView();
		//初始化按钮事件
		initButtonEvent();
		vPanel.add(formpanel);
		vPanel.add(killedCauseGrid);
		initWidget(vPanel);
	}

	/**
	 * 
	 */
	private void initButtonEvent() {
		loadDataBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
				loadConfig.set("causeType", causeTypeBox.getValue());
				loadConfig.set("killedType",killedTypeBox.getValue());
				loadConfig.set("causeNumber", number.getValue());
				killedCauseGrid.getPanel().getGrid().load(loadConfig);
			}
		});
		addCauseBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				doOpenAddWin();
			}
		});
		
		updateCauseBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				List<BaseModelData> selected = killedCauseGrid.getGrid().getSelected();
				if (selected!=null&&selected.size()==1) {
						doOpenUpdateWin(DataConverter.double2Int(selected.get(0).<Double>get("id")));
				}else {
					MessageBox.alert("请选择一条记录.");
					return;
				}
			}
		});
		
		delCauseBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final List<BaseModelData> selected = killedCauseGrid.getGrid().getSelected();
				if (selected!=null&&selected.size()>0) {
					MessageBox.confirm("确定删除" + selected.size() + "个封杀原因吗?", new Command() {
						@Override
						public void execute() {
							// yes
							try {
								List<Integer> ids = new ArrayList<Integer>();
								for (BaseModelData model : selected) {
									ids.add(DataConverter.double2Int(model.<Double>get("id")));
								}
								presenter.onClickDel(ids);
							} catch (Exception e) {
								MessageBox.error("删除失败：" + e.getMessage());
							}
						}
					}, null);
				}else {
					MessageBox.alert("请选择一条记录.");
					return;
				}
			}
		});
	}



	private void initGridView() {
		killedCauseGrid = new SchemaGridView(initPageSize);
		killedCauseGrid.setPresenter(presenter);
		killedCauseGrid.getGrid().setHasCheckBoxColumn(true);
		killedCauseGrid.setSchemaJson(PathBuilder
				.joinLine(
						"{\"table\":{\"key\":\"id\",\"columns\":[",
						"{\"index\":\"id\",\"header\":\"序号\",\"type\":\"number\",\"width\":50,\"visiable\":false}",
						",{\"index\":\"causeNumber\",\"header\":\"方案编号\",\"width\":70}",
						",{\"index\":\"causeType\",\"header\":\"原因分类\",\"width\":70}",
						",{\"index\":\"killedType\",\"header\":\"封杀类型\",\"width\":70}",
						",{\"index\":\"causeNote\",\"header\":\"封杀理由\",\"width\":150}",
						",{\"index\":\"createTime\",\"header\":\"创建日期\",\"width\":100}",
						",{\"index\":\"creator\",\"header\":\"创建人\",\"width\":100}",
						",{\"index\":\"action\",\"header\":\"操作日志\",\"width\":100,\"type\":\"button\"}",
						"]}}"));
		// 初始化列渲染
		initColumnRenderer();
		killedCauseGrid.load(isLoadOnAttach);
		
		killedCauseGrid.getPanel().getToolbar().addItem(loadDataBtn);
		killedCauseGrid.getPanel().getToolbar().addItem(addCauseBtn);
		killedCauseGrid.getPanel().getToolbar().addItem(updateCauseBtn);
		killedCauseGrid.getPanel().getToolbar().addItem(delCauseBtn);
	}

	private void initColumnRenderer() {
		killedCauseGrid.setColumnRenderer("causeType", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
				Integer causeType = object.getInt("causeType");
				if (causeType.toString().equals(SecurityKilledCauseType.hackTool.getValue())) {
					sb.appendHtmlConstant("外挂");
				}else if (causeType.toString().equals(SecurityKilledCauseType.stealAccount.getValue())) {
					sb.appendHtmlConstant("盗号");
				}else if (causeType.toString().equals(SecurityKilledCauseType.accountSecurity.getValue())) {
					sb.appendHtmlConstant("账户安全");
				}else {
					sb.appendHtmlConstant("其他");
				}
			}
		});
		
		killedCauseGrid.setColumnRenderer("killedType", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
				Integer killedType = object.getInt("killedType");
				if (killedType.toString().equals(SecurityKilledType.kill.getValue())) {
					sb.appendHtmlConstant("封杀");
				}else if (killedType.toString().equals(SecurityKilledType.lock.getValue())) {
					sb.appendHtmlConstant("冻结");
				}
			}
		});
		killedCauseGrid.setColumnRenderer("action", new ButtonColumnRenderer<BaseModelData>() {

	    	@Override
			public boolean isPopupWindow() {
				return true;
			}
	    	
			@Override
			public String getText(int column, int row, BaseModelData rowData,
					ColumnConfig config) {
				return "查看";
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>() {

					@Override
					public void execute(BaseModelData object) {
						OperLogListDialog listDialog = new OperLogListDialog(String.valueOf(object.getInt("id")), ConstantShared.TABLE_KILLED_CAUSE_CONFIG, 50);
						listDialog.center();
					}
				};
			}

			@Override
			public boolean isAutoWidth() {
				return true;
			}
			
		});
	}

	@Override
	public void doOpenAddWin() {
		killedCauseDialog = new ModifyKilledCauseDialog(null,presenter);
		killedCauseDialog.setHeight(300);
		killedCauseDialog.setWidth(450);
		killedCauseDialog.center();
	}
	
	@Override
	public void doOpenUpdateWin(Integer id) {
		killedCauseDialog = new ModifyKilledCauseDialog(id,presenter);
		killedCauseDialog.setHeight(300);
		killedCauseDialog.setWidth(450);
		killedCauseDialog.center();
	}
	
	@Override
	public void doAttachUpdateBox(KilledCauseConfig killedCause) {
		killedCauseDialog.getKilledCauseBox().setSelected(killedCause.getCauseType().toString());
		killedCauseDialog.getKilledTypeBox().setSelected(killedCause.getKilledType().toString());
		killedCauseDialog.getCauseNum().setValue(killedCause.getCauseNumber().toString());
		killedCauseDialog.getCauseNote().setValue(killedCause.getCauseNote());
		killedCauseDialog.setId(killedCause.getId());
	}

	@Override
	public KilledCausePresenter getPresenter() {
		return this.presenter;
	}

	@Override
	public void setPresenter(KilledCausePresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public SchemaGridView getsSchemaGridView() {

		return killedCauseGrid;
	}



	public ModifyKilledCauseDialog getKilledCauseDialog() {
		return killedCauseDialog;
	}



	public void setKilledCauseDialog(ModifyKilledCauseDialog killedCauseDialog) {
		this.killedCauseDialog = killedCauseDialog;
	}
	
	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityKilledCauseView#getProductId()
	 */
	@Override
	public String getProductId() {
		
		return productId;
	}
}
