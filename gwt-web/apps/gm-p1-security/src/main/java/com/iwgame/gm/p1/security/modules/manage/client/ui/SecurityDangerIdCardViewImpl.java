/**      
* SecurityDangerIdCardViewImpl.java Create on 2012-11-19     
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
import com.iwgame.gm.p1.security.common.shared.model.DangerIdCard;
import com.iwgame.gm.p1.security.common.shared.util.ConstantShared;
import com.iwgame.gm.p1.security.common.shared.util.DataConverter;
import com.iwgame.gm.p1.security.common.shared.util.PathBuilder;
import com.iwgame.gm.p1.security.modules.manage.client.ui.xdialog.ModifyDangerIdCardDialog;
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
 * @简述: 高危身份证视图控制实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-19 下午01:46:45 
 * 
 */
public class SecurityDangerIdCardViewImpl extends Composite implements SecurityDangerIdCardView {

	private int initPageSize;
	private boolean isLoadOnAttach;
	
	private XFormPanel formpanel;
	private FormLayout layout;
	private SchemaGridView dangerIdCardGrid;
	private ButtonToolItem loadDataBtn;
	private ButtonToolItem addBtn;
	private ButtonToolItem updateBtn;
	private ButtonToolItem delBtn;
	
	private DateRangeField createTime;
	private TextField idCard;
	private TextField operator;
	private VerticalPanel vPanel;
	
	private DangerIdCardPresenter presenter;
	
	private ModifyDangerIdCardDialog idCardDialog;
	
	//au使用的productid
	private String productId;
	public SecurityDangerIdCardViewImpl(DangerIdCardPresenter presenter,int pageSize,boolean isLoadOnAttach, String productId){
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
		idCard = new TextField("身份证");
		operator = new TextField("操作人");
		layout.add(idCard);
		layout.add(2, operator);
		layout.add(3,createTime);
		addBtn = new ButtonToolItem("新增");
		updateBtn = new ButtonToolItem("修改");
		delBtn = new ButtonToolItem("删除");
		loadDataBtn = new ButtonToolItem("查询");
		
		formpanel.setLayout(layout);
		
		//初始化grid
		initGridView();
		//初始化按钮事件
		initButtonEvent();
		vPanel.add(formpanel);
		vPanel.add(dangerIdCardGrid);
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
				loadConfig.set("operator", operator.getValue());
				loadConfig.set("idCard", idCard.getValue());
				dangerIdCardGrid.getPanel().getGrid().load(loadConfig);
			}
		});
		addBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				doOpenAddWin();
			}
		});
		
		updateBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				List<BaseModelData> selected = dangerIdCardGrid.getGrid().getSelected();
				if (selected!=null&&selected.size()==1) {
						doOpenUpdateWin(DataConverter.double2Int(selected.get(0).<Double>get("id")));
				}else {
					MessageBox.alert("请选择一条记录.");
					return;
				}
			}
		});
		
		delBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final List<BaseModelData> selected = dangerIdCardGrid.getGrid().getSelected();
				if (selected!=null&&selected.size()>0) {
					MessageBox.confirm("确定删除" + selected.size() + "个高危身份证吗?", new Command() {
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
		dangerIdCardGrid = new SchemaGridView(initPageSize);
		dangerIdCardGrid.setPresenter(presenter);
		dangerIdCardGrid.getGrid().setHasCheckBoxColumn(true);
		dangerIdCardGrid.setSchemaJson(PathBuilder
				.joinLine(
						"{\"table\":{\"key\":\"id\",\"columns\":[",
						"{\"index\":\"id\",\"header\":\"序号\",\"type\":\"number\",\"width\":50,\"visiable\":false}",
						",{\"index\":\"idCard\",\"header\":\"身份证\",\"width\":150,\"sortable\":true}",
						",{\"index\":\"causeNote\",\"header\":\"备注内容\",\"width\":150}",
						",{\"index\":\"createTime\",\"header\":\"创建日期\",\"width\":100,\"sortable\":true}",
						",{\"index\":\"action\",\"header\":\"操作日志\",\"width\":100,\"type\":\"button\"}",
						"]}}"));
		// 初始化列渲染
		initColumnRenderer();
		dangerIdCardGrid.load(isLoadOnAttach);
		dangerIdCardGrid.getPanel().getToolbar().addItem(loadDataBtn);
		dangerIdCardGrid.getPanel().getToolbar().addItem(addBtn);
		dangerIdCardGrid.getPanel().getToolbar().addItem(updateBtn);
		dangerIdCardGrid.getPanel().getToolbar().addItem(delBtn);
	}

	private void initColumnRenderer() {
		dangerIdCardGrid.setColumnRenderer("action", new ButtonColumnRenderer<BaseModelData>() {
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
						int id = object.getInt("id");
						OperLogListDialog logListDialog = new OperLogListDialog(String.valueOf(id), ConstantShared.TABLE_DANGER_ID_CARD, 50);
						logListDialog.center();
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
	public DangerIdCardPresenter getPresenter() {
		// TODO Auto-generated method stub
		return presenter;
	}

	@Override
	public void setPresenter(DangerIdCardPresenter presenter) {
		// TODO Auto-generated method stub
		this.presenter = presenter;
	}

	@Override
	public SchemaGridView getsSchemaGridView() {
		// TODO Auto-generated method stub
		return dangerIdCardGrid;
	}

	@Override
	public void doOpenAddWin() {
		// TODO Auto-generated method stub
		idCardDialog = new ModifyDangerIdCardDialog(null,presenter,this,productId);
		idCardDialog.setHeight(300);
		idCardDialog.setWidth(600);
		idCardDialog.center();
	}

	@Override
	public void doOpenUpdateWin(Integer id) {
		// TODO Auto-generated method stub
		idCardDialog = new ModifyDangerIdCardDialog(id,presenter,productId);
		idCardDialog.setHeight(240);
		idCardDialog.setWidth(455);
		idCardDialog.center();
	}

	@Override
	public void doAttachUpdateBox(DangerIdCard idCard) {
		// TODO Auto-generated method stub
		idCardDialog.getIdCard().setValue(idCard.getIdCard());
		idCardDialog.getCauseNote().setValue(idCard.getCauseNote());
		idCardDialog.setId(idCard.getId());
	}


	/* (non-Javadoc)
	 * @see com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityDangerIdCardView#getProductId()
	 */
	@Override
	public String getProductId() {
		// TODO Auto-generated method stub
		return productId;
	}
}
