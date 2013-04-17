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
import com.iwgame.gm.p1.security.common.shared.util.ConstantShared;
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
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xmvp.shared.data.BaseModelData;

/** 
 * @简述: 改密记录查询视图控制实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-20 下午18:46:45 
 * 
 */
public class SecurityLoginPassModifyRecordViewImpl extends Composite implements SecurityLoginPassModifyRecordView {

	private int initPageSize;
	private boolean isLoadOnAttach;
	
	private XFormPanel formpanel;
	private FormLayout layout;
	private SchemaGridView modifyRecordGrid;
	private ButtonToolItem loadDataBtn;
	private ExportButton exportButton;
	private DateRangeField modifyTime;
	private TextField account;
	private VerticalPanel vPanel;
	//au产品ID
	private String pid ;
	
	private LoginPassModifyRecordPresenter presenter;
	
	public SecurityLoginPassModifyRecordViewImpl(LoginPassModifyRecordPresenter presenter,int pageSize,boolean isLoadOnAttach,String productId){
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
		layout.setColumn(2);
		layout.setLabelAlign(HasHorizontalAlignment.ALIGN_LEFT);
		
		modifyTime = new DateRangeField("改密日期");
		modifyTime.getWidget().setEndDateChangeLinks(7,15,30);
		account = new TextField("账号");
		account.setEmptyText("多个账号用','(英文逗号)进行分隔");
		layout.add(account);
		layout.add(2,modifyTime);
		loadDataBtn = new ButtonToolItem("查询");
		
		initButtonEvent();
		
		//初始化grid
		initGridView();

		formpanel.setLayout(layout);
		vPanel.add(formpanel);
		vPanel.add(modifyRecordGrid);
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
				Date startDate = modifyTime.getStartValue();
				Date endDate = modifyTime.getEndValue();
				loadConfig.set("startDate", startDate);
				loadConfig.set("endDate", endDate);
				loadConfig.set("username", account.getValue());
				modifyRecordGrid.getPanel().getGrid().load(loadConfig);
			}
		});

	}



	private void initGridView() {
		modifyRecordGrid = new SchemaGridView(initPageSize);
		modifyRecordGrid.setPresenter(presenter);
		modifyRecordGrid.getGrid().setHasCheckBoxColumn(false);
		modifyRecordGrid.setSchemaJson(PathBuilder
				.joinLine(
						"{\"table\":{\"key\":\"id\",\"columns\":[",
						"{\"index\":\"id\",\"header\":\"序号\",\"type\":\"number\",\"width\":50,\"visiable\":false}",
						",{\"index\":\"userId\",\"header\":\"账号ID\",\"width\":80}",
						",{\"index\":\"username\",\"header\":\"账号\",\"width\":100,\"type\":\"button\"}",
						",{\"index\":\"modifyip\",\"header\":\"改密IP\",\"width\":100}",
						",{\"index\":\"modifyPlace\",\"header\":\"改密渠道\",\"width\":100}",
						",{\"index\":\"modifyTime\",\"header\":\"改密日期\",\"width\":100}",
						"]}}"));
		modifyRecordGrid.setColumnRenderer("modifyip", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
				String ip = object.get("modifyip","");
				if(!"".equals(ip)){
					String url = ConstantShared.getIpQueryUrl().replace("$ip", ip);
					sb.appendHtmlConstant("<a href='"+url+"' target='_blank'>"+ip+"</a>");
				}
			}
		});
		
		modifyRecordGrid.setColumnRenderer("username", 
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
		
		exportButton = new ExportButton(modifyRecordGrid.getPanel()) {
			@Override
			public Map<String, String> getParameters() {
				Date startDate = modifyTime.getStartValue();
				Date endDate = modifyTime.getEndValue();
				Map<String, String> parameter = new HashMap<String, String>();
				//初始化日期参数
				if (startDate!=null && endDate!=null) {
					parameter.put("username", account.getValue());
					parameter.put("pid",pid);

					parameter.put("startDate", startDate.getTime()+"");
					parameter.put("endDate", endDate.getTime()+"");
				}
				return parameter;
			}
			public String getFilename() {
				
				return "账号改密记录-"+DateWrapper.format(new Date(), "yyyyMMddHHmmss");
			};
		};
		modifyRecordGrid.getPanel().enableExport("loginPassModifyRecordExportQuery");
		modifyRecordGrid.getPanel().setExportButton(exportButton);
		modifyRecordGrid.getPanel().getToolbar().addItem(loadDataBtn);
		modifyRecordGrid.load(isLoadOnAttach);
	}

	@Override
	public LoginPassModifyRecordPresenter getPresenter() {
		// TODO Auto-generated method stub
		return presenter;
	}

	@Override
	public void setPresenter(LoginPassModifyRecordPresenter presenter) {
		// TODO Auto-generated method stub
		this.presenter = presenter;
	}

	@Override
	public SchemaGridView getsSchemaGridView() {
		// TODO Auto-generated method stub
		return modifyRecordGrid;
	}
}
