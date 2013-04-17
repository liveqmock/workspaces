/**      
* AccountFuzzyQueryPanel.java Create on 2012-11-27     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.client.ui.panel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.security.modules.query.client.ui.SecurityRegisInfoQueryView.SecurityRegisInfoQueryPresenter;
import com.iwgame.gm.p1.security.modules.query.client.ui.grid.AccountDataGridView;
import com.iwgame.ui.core.client.data.BaseFilterPagingLoadConfig;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.grid.client.toolbar.ExportButton;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FieldPlugin;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;

/** 
 * @简述: 账户资料模糊查询
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-27 下午12:18:56 
 */
public class AccountFuzzyQueryPanel extends VerticalPanel{
	private int initPageSize;
	private boolean isLoadOnAttach;
	
	private XFormPanel formPanel;
	private FormLayout layout;
	private TextField username;
	private ButtonToolItem loadDataBtn;
	private DateRangeField registerTime;
	private AccountDataGridView accountDataGridView;
	private ExportButton exportButton;
	private SecurityRegisInfoQueryPresenter presenter;
	private String pid;
	public AccountFuzzyQueryPanel(SecurityRegisInfoQueryPresenter presenter,
			String pid,int pageSize,boolean isLoadOnAttach){
		this.presenter = presenter;
		this.initPageSize=pageSize;
		this.isLoadOnAttach=isLoadOnAttach;
		this.pid = pid;
		initView();
	}
	/**
	 * 加载查询条件
	 */
	private void initView() {
		formPanel = new XFormPanel();
		
		layout = new FormLayout();
		layout.setColumn(5);
		layout.setLabelAlign(HasHorizontalAlignment.ALIGN_RIGHT);
		layout.setWidth(700);
		layout.setLabelWidth(80);
		
		username = new TextField("账号");
		username.setColSpan(2);
		username.setEnablePlugin(true);
		username.setPlugin(new FieldPlugin<CheckBox>() {
			private CheckBox checkBox;
			
			@Override
			public CheckBox getWidget(Field<?, ?> field) {
				if(checkBox==null){
					checkBox=new CheckBox("精确查询");
				}
				return checkBox;
			}
			
		});
		registerTime = new DateRangeField(false,"注册时间段");
		registerTime.setColSpan(3);
		registerTime.getWidget().setEndDateChangeLinks(7,15,30);
		loadDataBtn = new ButtonToolItem("查询");
		layout.add(username);
		layout.add(3,registerTime);
		
		initButtonEvent();
		
		//初始化grid
		initGridView();
		
		formPanel.setLayout(layout);		
		add(formPanel);
		add(accountDataGridView);
	}
	/**
	 * 加载数据结果
	 */
	private void initGridView() {
		accountDataGridView = new AccountDataGridView(initPageSize, presenter);
		exportButton = new ExportButton(accountDataGridView.getPanel()) {
			
			@Override
			public Map<String, String> getParameters() {
				Map<String, String> parameter = new HashMap<String, String>();
				parameter.put("options","username");
				parameter.put("username", username.getValue());
				CheckBox cBox=(CheckBox) username.getPlugin().getWidget(username);
				parameter.put("isfuzzy", cBox.getValue()?"1":"0");
				parameter.put("pid", pid);
				BaseFilterPagingLoadConfig currentLoadConfig = accountDataGridView.getPanel().getGrid().getLoadConfig();
				String sortColumn = currentLoadConfig.getSortField();
				String sortType = currentLoadConfig.getSortDir().name();
				//排序
				parameter.put("sortColumn", sortColumn);
				parameter.put("sortType", sortType);
			
				//初始化日期参数
				Date startDate = registerTime.getStartValue();
				Date endDate = registerTime.getEndValue();
				if (startDate!=null && endDate!=null) {
					parameter.put("startDate", startDate.getTime()+"");
					parameter.put("endDate", endDate.getTime()+"");
				}
				return parameter;
			}

			@Override
			public String getFilename() {
				return "账号查询-"+DateWrapper.format(new Date(), "yyyyMMddHHmmss");
			}
		};
		accountDataGridView.getPanel().getToolbar().addItem(loadDataBtn);
		accountDataGridView.getPanel().setExportButton(exportButton);
		accountDataGridView.load(isLoadOnAttach);
	}
	/**
	 * 加载button事件
	 */
	private void initButtonEvent() {
		loadDataBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				BaseFilterPagingLoadConfig loadConfig = new BaseFilterPagingLoadConfig();
				loadConfig.set("pid", pid);
				Date startDate = registerTime.getStartValue();
				Date endDate = registerTime.getEndValue();
				loadConfig.set("startDate", startDate);
				loadConfig.set("endDate", endDate);
				loadConfig.set("username", username.getValue());
				CheckBox cBox=(CheckBox) username.getPlugin().getWidget(username);
				loadConfig.set("isfuzzy", cBox.getValue()?"1":"0");
				loadConfig.set("options","username");
				accountDataGridView.getPanel().getGrid().load(loadConfig);
			}
		});
	}
}
