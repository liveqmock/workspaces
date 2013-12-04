/**      
* SecuritySafeModeCauseViewImpl.java Create on 2012-11-16     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.client.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.security.common.client.ui.OperLogListDialog;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;
import com.iwgame.gm.p1.security.common.shared.util.ConstantShared;
import com.iwgame.gm.p1.security.common.shared.util.DataConverter;
import com.iwgame.gm.p1.security.common.shared.util.PathBuilder;
import com.iwgame.gm.p1.security.modules.manage.client.ui.xdialog.ModifySafeModeCauseDialog;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;

/** 
 * @简述: 安全模式备注界面实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-16 下午04:23:20 
 */
public class SecuritySafeModeCauseViewImpl extends Composite implements
		SecuritySafeModeCauseView {
	private int initPageSize;
	private boolean isLoadOnAttach;
	
	private XFormPanel formpanel;
	private FormLayout layout;
	private SchemaGridView safeModeCauseGrid;
	private ButtonToolItem loadDataBtn;
	private ButtonToolItem addCauseBtn;
	private ButtonToolItem updateCauseBtn;
	private ButtonToolItem delCauseBtn;
	
	private DateRangeField createTime;
	
	private TextField creator;
	private VerticalPanel vPanel;
	
	private SafeModeCausePresenter presenter;
	
	private ModifySafeModeCauseDialog killedCauseDialog;
	
	private String productId;
	public SecuritySafeModeCauseViewImpl(SafeModeCausePresenter presenter,int pageSize,boolean isLoadOnAttach, String productId){
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
		
		createTime = new DateRangeField("创建日期");
		createTime.getWidget().setEndDateChangeLinks(7,15,30);
		creator = new TextField("操作人");
		layout.add(createTime);
		layout.add(2, creator);
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
		vPanel.add(safeModeCauseGrid);
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
				Date startDate = createTime.getStartValue();
				Date endDate = createTime.getEndValue();
				loadConfig.set("startDate", startDate);
				loadConfig.set("endDate", endDate);
				loadConfig.set("creator", creator.getValue());
				safeModeCauseGrid.getPanel().getGrid().load(loadConfig);
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
				List<BaseModelData> selected = safeModeCauseGrid.getGrid().getSelected();
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
				final List<BaseModelData> selected = safeModeCauseGrid.getGrid().getSelected();
				if (selected!=null&&selected.size()>0) {
					MessageBox.confirm("确定删除" + selected.size() + "个安全模式备注吗?", new Command() {
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
		safeModeCauseGrid = new SchemaGridView(initPageSize);
		safeModeCauseGrid.setPresenter(presenter);
		safeModeCauseGrid.getGrid().setHasCheckBoxColumn(true);
		safeModeCauseGrid.setSchemaJson(PathBuilder
				.joinLine(
						"{\"table\":{\"key\":\"id\",\"columns\":[",
						"{\"index\":\"id\",\"header\":\"序号\",\"type\":\"number\",\"width\":50,\"visiable\":false}",
						",{\"index\":\"causeNote\",\"header\":\"原因备注\",\"width\":100}",
						",{\"index\":\"createTime\",\"header\":\"创建日期\",\"width\":100}",
						",{\"index\":\"creator\",\"header\":\"创建人\",\"width\":100}",
						",{\"index\":\"action\",\"header\":\"操作日志\",\"width\":100,\"type\":\"button\"}",
						"]}}"));
		// 初始化列渲染
		initColumnRenderer();
		safeModeCauseGrid.load(isLoadOnAttach);
		safeModeCauseGrid.getPanel().getToolbar().addItem(loadDataBtn);
		safeModeCauseGrid.getPanel().getToolbar().addItem(addCauseBtn);
		safeModeCauseGrid.getPanel().getToolbar().addItem(updateCauseBtn);
		safeModeCauseGrid.getPanel().getToolbar().addItem(delCauseBtn);
	}

	private void initColumnRenderer() {
		safeModeCauseGrid.setColumnRenderer("action", new ButtonColumnRenderer<BaseModelData>() {

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
						OperLogListDialog listDialog = new OperLogListDialog(String.valueOf(object.getInt("id")), ConstantShared.TABLE_SAFE_MODE_CAUSE_CONFIG, 50);
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
	public SafeModeCausePresenter getPresenter() {
		// TODO Auto-generated method stub
		return presenter;
	}

	@Override
	public void setPresenter(SafeModeCausePresenter presenter) {
		// TODO Auto-generated method stub
		this.presenter = presenter;
	}

	@Override
	public SchemaGridView getsSchemaGridView() {
		// TODO Auto-generated method stub
		return safeModeCauseGrid;
	}

	@Override
	public void doOpenAddWin() {
		// TODO Auto-generated method stub
		killedCauseDialog = new ModifySafeModeCauseDialog(null,presenter);
		killedCauseDialog.setHeight(220);
		killedCauseDialog.setWidth(450);
		killedCauseDialog.center();
	}

	@Override
	public void doOpenUpdateWin(Integer id) {
		// TODO Auto-generated method stub
		killedCauseDialog = new ModifySafeModeCauseDialog(id,presenter);
		killedCauseDialog.setHeight(240);
		killedCauseDialog.setWidth(455);
		killedCauseDialog.center();
	}

	@Override
	public void doAttachUpdateBox(SafeModeCauseConfig safeModeCause) {
		// TODO Auto-generated method stub
		killedCauseDialog.getCauseNote().setValue(safeModeCause.getCauseNote());
		killedCauseDialog.setId(safeModeCause.getId());
	}



	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.security.modules.manage.client.ui.SecuritySafeModeCauseView#getProductId()
	 */
	@Override
	public String getProductId() {
		// TODO Auto-generated method stub
		return productId;
	}

}
