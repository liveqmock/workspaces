/**      
* SecurityDangerIdCardViewImpl.java Create on 2012-11-19     
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
 * @简述: 安全模式操作日志查询视图控制实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-23 下午17:53:45 
 * 
 */
@SuppressWarnings("rawtypes")
public class SecuritySafeModeRecordViewImpl extends Composite implements SecuritySafeModeRecordView {

	private int initPageSize;
	private boolean isLoadOnAttach;
	
	private XFormPanel formpanel;
	private FormLayout layout;
	private SchemaGridView safeModeRecordGrid;

	private ComboBoxField modeType;
	private TextField dbid;
	private TextField operator;
	private TextField rolename;
	private ButtonToolItem loadDataBtn;
	private ExportButton exportButton;
	private DateRangeField optime;
	private VerticalPanel vPanel;
	
	private String pid;
	private SafeModeRecordPresenter presenter;
	
	public SecuritySafeModeRecordViewImpl(SafeModeRecordPresenter presenter,int pageSize,boolean isLoadOnAttach,String productId){
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
		layout.setColumn(3);
		layout.setLabelAlign(HasHorizontalAlignment.ALIGN_LEFT);
		
		modeType = new ComboBoxField("操作模式");
		modeType.addItem("--不限--", "");
		modeType.addItem("增加安全模式", "1");
		modeType.addItem("解除安全模式", "2");
		dbid = new TextField("DBID");
		operator = new TextField("操作员");
		optime = new DateRangeField("操作日期");
		optime.getWidget().setEndDateChangeLinks(7,15,30);
		rolename = new TextField("角色名");
		layout.add(modeType);
		layout.add(2,operator);
		layout.add(3, dbid);
		
		layout.add(rolename);
		layout.add(2,optime);
		loadDataBtn = new ButtonToolItem("查询");
		
		initButtonEvent();
		
		//初始化grid
		initGridView();

		formpanel.setLayout(layout);
		vPanel.add(formpanel);
		vPanel.add(safeModeRecordGrid);
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
				loadConfig.set("dbid", dbid.getValue());
				loadConfig.set("operator", operator.getValue());
				loadConfig.set("modeType", modeType.getValue());
				loadConfig.set("rolename", rolename.getValue());
				safeModeRecordGrid.getPanel().getGrid().load(loadConfig);
			}
		});

	}



	private void initGridView() {
		safeModeRecordGrid = new SchemaGridView(initPageSize);
		safeModeRecordGrid.setPresenter(presenter);
		safeModeRecordGrid.getGrid().setHasCheckBoxColumn(false);
		safeModeRecordGrid.setSchemaJson(PathBuilder
				.joinLine(
						"{\"table\":{\"key\":\"id\",\"columns\":[",
						"{\"index\":\"id\",\"header\":\"序号\",\"type\":\"number\",\"width\":50,\"visiable\":false}",
						",{\"index\":\"optime\",\"header\":\"操作日期\",\"width\":130,\"sortable\":true}",
						",{\"index\":\"rolename\",\"header\":\"角色名\",\"width\":120}",
						",{\"index\":\"username\",\"header\":\"账号\",\"width\":150,\"type\":\"button\"}",
						",{\"index\":\"dbid\",\"header\":\"DBID\",\"width\":200}",
						",{\"index\":\"modeType\",\"header\":\"模式\",\"width\":100,\"type\":\"number\"}",
						",{\"index\":\"causeNote\",\"header\":\"原因备注\",\"width\":200}",
						",{\"index\":\"operator\",\"header\":\"操作员\",\"width\":100}",
						"]}}"));
		exportButton = new ExportButton(safeModeRecordGrid.getPanel()) {
			@Override
			public Map<String, String> getParameters() {
				Map<String, String> parameter = new HashMap<String, String>();
				parameter.put("pid", pid);
				parameter.put("dbid", dbid.getValue());
				parameter.put("operator", operator.getValue());
				parameter.put("modeType", modeType.getValue());
				parameter.put("rolename", rolename.getValue());
				BaseFilterPagingLoadConfig currentLoadConfig = safeModeRecordGrid.getPanel().getGrid().getLoadConfig();
				String sortColumn = currentLoadConfig.getSortField();
				String sortType = currentLoadConfig.getSortDir().name();
				if (sortColumn==null) {
					sortColumn="id";
				}
				if (sortType.equals("NONE")) {
					sortType="desc";
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
			public String getFilename() {
				
				return "安全模式操作日志-"+DateWrapper.format(new Date(), "yyyyMMddHHmmss");
			};
		};
		/*
		 * 列渲染
		 */
		initColumnRender();
		
		safeModeRecordGrid.getPanel().enableExport("safeModeRecordExportQuery");
		safeModeRecordGrid.getPanel().setExportButton(exportButton);
		safeModeRecordGrid.getPanel().getToolbar().addItem(loadDataBtn);
		safeModeRecordGrid.load(isLoadOnAttach);
	}

	private void initColumnRender() {
		//	1:增加安全模式,2:解除安全模式
		safeModeRecordGrid.setColumnRenderer("modeType", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
					int type = object.getInt("modeType");
					if (type==1) {
						sb.appendHtmlConstant("增加安全模式");
					}else if (type==2) {
						sb.appendHtmlConstant("解除安全模式");
					}
			}
		});
		safeModeRecordGrid.setColumnRenderer("username", 
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
	public SafeModeRecordPresenter getPresenter() {
		// TODO Auto-generated method stub
		return presenter;
	}

	@Override
	public void setPresenter(SafeModeRecordPresenter presenter) {
		// TODO Auto-generated method stub
		this.presenter = presenter;
	}

	@Override
	public SchemaGridView getsSchemaGridView() {
		// TODO Auto-generated method stub
		return safeModeRecordGrid;
	}


}
