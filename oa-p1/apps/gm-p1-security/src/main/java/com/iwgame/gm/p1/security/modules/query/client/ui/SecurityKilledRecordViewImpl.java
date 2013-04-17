/**      
k* SecurityDangerIdCardViewImpl.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.client.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.security.common.shared.util.PathBuilder;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledCauseBox;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledCauseType;
import com.iwgame.gm.p1.security.modules.query.client.ui.xdialog.AccountDetailsDialogBox;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ButtonColumnRenderer;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.ExportButton;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.ComboBoxField;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;

/** 
 * @简述: 账号封杀记录查询视图控制实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-20 下午18:46:45 
 * 
 */
@SuppressWarnings("rawtypes")
public class SecurityKilledRecordViewImpl extends Composite implements SecurityKilledRecordView {

	private int initPageSize;
	private boolean isLoadOnAttach;
	private XFormPanel formpanel;
	private FormLayout layout;
	private SchemaGridView killedRecordGrid;
	private SecurityKilledCauseBox killedCauseBox;

	private ComboBoxField accountStatus;
	private TextField days;
	private TextField operator;
	private TextField batchid;
	private ComboBoxField handlerStatus;
	private ButtonToolItem loadDataBtn;
	private ExportButton exportButton;
	private DateRangeField optime;
	private TextField account;
	private VerticalPanel vPanel;
	
	private String pid;
	private KilledRecordPresenter presenter;
	
	public SecurityKilledRecordViewImpl(KilledRecordPresenter presenter,int pageSize,boolean isLoadOnAttach,String productId){
		this.initPageSize=pageSize;
		this.isLoadOnAttach=isLoadOnAttach;
		this.presenter = presenter;
		this.pid = productId;
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
		layout.setLabelWidth(80);
		layout.setColumn(4);
		layout.setLabelAlign(HasHorizontalAlignment.ALIGN_LEFT);
		
		killedCauseBox = new SecurityKilledCauseBox("封杀原因");
		accountStatus = new ComboBoxField("账号状态");
		accountStatus.addItem("--不限--", "");
		accountStatus.addItem("KILL", "1");
		accountStatus.addItem("LOCK", "2");
		accountStatus.addItem("ACTIVE", "3");
		handlerStatus = new ComboBoxField("处理状态");
		handlerStatus.addItem("--不限--", "");
		handlerStatus.addItem("成功", "0");
		handlerStatus.addItem("处理中", "1");
		handlerStatus.addItem("失败", "2");
		days = new TextField("封杀天数");
		operator = new TextField("操作员");
		optime = new DateRangeField("封杀日期");
		optime.getWidget().setEndDateChangeLinks(7,15,30);
		account = new TextField("账号");
		batchid = new TextField("批次号");
		layout.add(killedCauseBox);
		layout.add(2,days);
		layout.add(3,operator);
		layout.add(4, batchid);
		layout.add(account);
		layout.add(2, accountStatus);
		layout.add(3,handlerStatus);
		layout.add(4,optime);
		loadDataBtn = new ButtonToolItem("查询");
		
		initButtonEvent();
		
		//初始化grid
		initGridView();

		formpanel.setLayout(layout);
		vPanel.add(formpanel);
		vPanel.add(killedRecordGrid);
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
				Date startDate = optime.getStartValue();
				Date endDate = optime.getEndValue();
				loadConfig.set("startDate", startDate);
				loadConfig.set("endDate", endDate);
				loadConfig.set("username", account.getValue());
				loadConfig.set("causeType", killedCauseBox.getValue());
				loadConfig.set("days", days.getValue());
				loadConfig.set("operator", operator.getValue());
				loadConfig.set("type", accountStatus.getValue());
				loadConfig.set("batchid", batchid.getValue());
				loadConfig.set("handlerStatus", handlerStatus.getValue());
				killedRecordGrid.getPanel().getGrid().load(loadConfig);
			}
		});

	}



	private void initGridView() {
		killedRecordGrid = new SchemaGridView(initPageSize);
		killedRecordGrid.setPresenter(presenter);
		killedRecordGrid.getGrid().setHasCheckBoxColumn(false);
		killedRecordGrid.setSchemaJson(PathBuilder
				.joinLine(
						"{\"table\":{\"key\":\"id\",\"columns\":[",
						"{\"index\":\"id\",\"header\":\"序号\",\"type\":\"number\",\"width\":50,\"visiable\":false}",
						",{\"index\":\"batchid\",\"header\":\"批次号\",\"width\":200}",
						",{\"index\":\"username\",\"header\":\"账号\",\"type\":\"button\",\"width\":150}",
						",{\"index\":\"type\",\"header\":\"状态\",\"width\":70,\"sortable\":true}",
						",{\"index\":\"days\",\"header\":\"封停天数\",\"width\":70,\"type\":\"number\"}",
						",{\"index\":\"causeType\",\"header\":\"原因\",\"width\":70,\"sortable\":true}",
						",{\"index\":\"causeNote\",\"header\":\"原因说明\",\"width\":250}",
						",{\"index\":\"handleStatus\",\"header\":\"处理状态\",\"width\":70}",
						",{\"index\":\"optime\",\"header\":\"操作时间\",\"width\":150,\"sortable\":true}",
						",{\"index\":\"operator\",\"header\":\"操作员\",\"width\":100}",
						"]}}"));
		
		exportButton = new ExportButton(killedRecordGrid.getPanel()) {
			@Override
			public Map<String, String> getParameters() {
				Map<String, String> parameter = new HashMap<String, String>();
				parameter.put("username", account.getValue());
				parameter.put("pid", pid);
				parameter.put("causeType", killedCauseBox.getValue());
				parameter.put("days", days.getValue());
				parameter.put("operator", operator.getValue());
				parameter.put("type", accountStatus.getValue());
				parameter.put("batchid", batchid.getValue());
				parameter.put("handlerStatus", handlerStatus.getValue());
				BaseFilterPagingLoadConfig currentLoadConfig = killedRecordGrid.getPanel().getGrid().getLoadConfig();
				String sortColumn = currentLoadConfig.getSortField();
				String sortType = currentLoadConfig.getSortDir().name();
				if ("causeType".equals(sortColumn)) {
						sortColumn = "c.cause_type";
				}
				//排序
				parameter.put("sortColumn", sortColumn);
				parameter.put("sortType", sortType);
			
				//初始化日期参数
				Date startDate = optime.getStartValue();
				Date endDate = optime.getEndValue();
				
				if (startDate!=null && endDate!=null) {
					parameter.put("startDate", startDate.getTime()+"");
					parameter.put("endDate", endDate.getTime()+"");
				}
				
				return parameter;
			}

			@Override
			public String getFilename() {
				
				return "账号封杀记录-"+DateWrapper.format(new Date(), "yyyyMMddHHmmss");
			}
			
		};
		/*
		 * 列渲染
		 */
		initColumnRender();
		
		killedRecordGrid.getPanel().enableExport("killedRecordExportQuery");
		killedRecordGrid.getPanel().setExportButton(exportButton);
		killedRecordGrid.getPanel().getToolbar().addItem(loadDataBtn);
		killedRecordGrid.load(isLoadOnAttach);
	}

	private void initColumnRender() {
		//	1.封杀,2.冻结,3.正常
		killedRecordGrid.setColumnRenderer("type", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
					int type = object.getInt("type");
					if (type==1) {
						sb.appendHtmlConstant("KILL");
					}else if (type==2) {
						sb.appendHtmlConstant("LOCK");
					}else if (type==3) {
						sb.appendHtmlConstant("ACTIVE");
					}
			}
		});
		
		killedRecordGrid.setColumnRenderer("causeType", new ColumnRenderer<BaseModelData>() {

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
		
		killedRecordGrid.setColumnRenderer("handleStatus", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
				String handleStatus = object.<String>get("handleStatus");
				//处理状态 0:处理成功 1:处理中 2:失败
				if ("0".equals(handleStatus)) {
					sb.appendHtmlConstant("<span style='color:green'>成功</span>");
				}else if ("1".equals(handleStatus)) {
					sb.appendHtmlConstant("<span style='color:blue'>处理中</span>");
				}else if ("2".equals(handleStatus)) {
					sb.appendHtmlConstant("<span style='color:red'>失败</span>");
				}
			}
		});
		
		killedRecordGrid.setColumnRenderer("username", 
				new ButtonColumnRenderer<BaseModelData>() {
			@Override
			public boolean isAutoWidth() {
				return false;
			}
	    	@Override
			public boolean isPopupWindow() {
				return true;
			}

			@Override
			public Delegate<BaseModelData> getDelegate() {
				return new Delegate<BaseModelData>(){
					@Override
					public void execute(BaseModelData object) {
						String userName = object.get("username", "");
						AccountDetailsDialogBox box = new AccountDetailsDialogBox(userName, pid);
		                box.setWidth(520);
						box.setHeight(320);
						box.center();
					}					
				};
			}
		});
		
	}


	@Override
	public KilledRecordPresenter getPresenter() {
		// TODO Auto-generated method stub
		return presenter;
	}

	@Override
	public void setPresenter(KilledRecordPresenter presenter) {
		// TODO Auto-generated method stub
		this.presenter = presenter;
	}

	@Override
	public SchemaGridView getsSchemaGridView() {
		// TODO Auto-generated method stub
		return killedRecordGrid;
	}


}
