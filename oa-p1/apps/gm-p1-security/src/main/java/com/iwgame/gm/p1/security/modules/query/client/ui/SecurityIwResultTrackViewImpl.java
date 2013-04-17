/**      
* SecurityDangerIdCardViewImpl.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.client.ui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.security.common.shared.util.PathBuilder;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;

/** 
 * @简述: 操作结果追踪查询视图控制实现
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-27 上午11:15:50  
 * 
 */
public class SecurityIwResultTrackViewImpl extends Composite implements SecurityIwResultTrackView {

	private int initPageSize;
	private boolean isLoadOnAttach;
	private XFormPanel formpanel;
	private FormLayout layout;
	private SchemaGridView resultTrackGrid;

	private TextField operator;
	private TextField batchid;
	private ButtonToolItem loadDataBtn;
	private DateRangeField optime;
	private VerticalPanel vPanel;
	
	private String pid;
	
	private IwResultTrackPresenter presenter;
	
	public SecurityIwResultTrackViewImpl(IwResultTrackPresenter presenter,int pageSize,boolean isLoadOnAttach,String productId){
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
		operator = new TextField("操作人");
		optime = new DateRangeField("提交日期");
		optime.getWidget().setEndDateChangeLinks(7,15,30);
		batchid = new TextField("批次号");
		layout.add(batchid);
		layout.add(2,operator);
		layout.add(3,optime);
		loadDataBtn = new ButtonToolItem("查询");
		
		initButtonEvent();
		
		//初始化grid
		initGridView();

		formpanel.setLayout(layout);
		vPanel.add(formpanel);
		vPanel.add(resultTrackGrid);
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
				loadConfig.set("operator", operator.getValue());
				loadConfig.set("batchid", batchid.getValue());
				resultTrackGrid.getPanel().getGrid().load(loadConfig);
			}
		});

	}



	private void initGridView() {
		resultTrackGrid = new SchemaGridView(initPageSize);
		resultTrackGrid.setPresenter(presenter);
		resultTrackGrid.getGrid().setHasCheckBoxColumn(false);
		resultTrackGrid.setSchemaJson(PathBuilder
				.joinLine(
						"{\"table\":{\"key\":\"id\",\"columns\":[",
						"{\"index\":\"id\",\"header\":\"序号\",\"type\":\"number\",\"width\":50,\"visiable\":false}",
						",{\"index\":\"batchid\",\"header\":\"批次号\",\"width\":150}",
						",{\"index\":\"trackType\",\"header\":\"操作类型\",\"width\":60,\"type\":\"number\"}",
						",{\"index\":\"submitNum\",\"header\":\"提交数\",\"width\":55,\"type\":\"number\"}",
						",{\"index\":\"successNum\",\"header\":\"完成数\",\"width\":55,\"type\":\"number\"}",
						",{\"index\":\"failedNum\",\"header\":\"失败数\",\"width\":55,\"type\":\"number\"}",
						",{\"index\":\"submitTime\",\"header\":\"提交时间\",\"width\":100,\"sortable\":true}",
						",{\"index\":\"finishTime\",\"header\":\"完成时间\",\"width\":100,\"sortable\":true}",
						",{\"index\":\"operator\",\"header\":\"操作人\",\"width\":100}",
						"]}}"));
		
		/*
		 * 列渲染
		 */
		initColumnRender();
		
		resultTrackGrid.getPanel().getToolbar().addItem(loadDataBtn);
		resultTrackGrid.load(isLoadOnAttach);
	}

	private void initColumnRender() {
		//类型  1:封杀,2:冻结,3:解封,4:安全模式,5:解除安全模式
		resultTrackGrid.setColumnRenderer("trackType", new ColumnRenderer<BaseModelData>() {
			
			@Override
			public void render(int column, int row, BaseModelData object,
					ColumnConfig config, SafeHtmlBuilder sb) {
					int type = object.getInt("trackType");
					if (type==1) {
						sb.appendHtmlConstant("封杀");
					}else if (type==2) {
						sb.appendHtmlConstant("冻结");
					}else if (type==3) {
						sb.appendHtmlConstant("解封");
					}else if (type==4) {
						sb.appendHtmlConstant("安全模式");
					}else if (type==5) {
						sb.appendHtmlConstant("解除安全模式");
					}
			}
		});
		
		
	}


	@Override
	public IwResultTrackPresenter getPresenter() {
		// TODO Auto-generated method stub
		return presenter;
	}

	@Override
	public void setPresenter(IwResultTrackPresenter presenter) {
		// TODO Auto-generated method stub
		this.presenter = presenter;
	}

	@Override
	public SchemaGridView getsSchemaGridView() {
		// TODO Auto-generated method stub
		return resultTrackGrid;
	}


}
