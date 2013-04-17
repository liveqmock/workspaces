/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： AccountRegisInfoQueryPanel.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.query.client.ui.panel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;

/**
 * 类说明
 * @简述：账号注册ip查询
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-28 上午09:29:21
 */
public class AccountRegisInfoQueryPanel extends VerticalPanel{

	private int initPageSize;
	private boolean isLoadOnAttach;
	
	private XFormPanel formPanel;
	private FormLayout layout;
	private TextField realName;
	private TextField idcard;
	private TextField userEmail;
	private ButtonToolItem loadDataBtn;
	private DateRangeField registerTime;
	private AccountDataGridView accountDataGridView;
	private ExportButton exportButton;
	private SecurityRegisInfoQueryPresenter presenter;
	private String pid;
	public AccountRegisInfoQueryPanel(SecurityRegisInfoQueryPresenter presenter,
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
		layout.setColumn(3);
		layout.setLabelAlign(HasHorizontalAlignment.ALIGN_RIGHT);
		layout.setWidth(700);
		layout.setLabelWidth(80);
		
		realName = new TextField("注册姓名");
		idcard = new TextField("注册身份证");
		userEmail = new TextField("注册邮箱");
		registerTime = new DateRangeField(false, "注册时间段");
		registerTime.getWidget().setEndDateChangeLinks(7,15,30);
		loadDataBtn = new ButtonToolItem("查询");
		layout.add(registerTime);
		layout.add(2,realName);
		layout.add(idcard);
		layout.add(2, userEmail);
		
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
		accountDataGridView = new AccountDataGridView(initPageSize,presenter);
		exportButton = new ExportButton(accountDataGridView.getPanel()) {
			
			@Override
			public Map<String, String> getParameters() {
				Map<String, String> parameter = new HashMap<String, String>();
				parameter.put("options","registerinfo");
				parameter.put("realName", realName.getValue());
				parameter.put("idcard", idcard.getValue());
				parameter.put("userEmail", userEmail.getValue());
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
				return "注册资料查询账号-"+DateWrapper.format(new Date(), "yyyyMMddHHmmss");
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
				Date startDate = registerTime.getStartValue();
				Date endDate = registerTime.getEndValue();

				loadConfig.set("startDate", startDate);
				loadConfig.set("endDate", endDate);
				loadConfig.set("realName", realName.getValue());
				loadConfig.set("idcard", idcard.getValue());
				loadConfig.set("userEmail", userEmail.getValue());
				loadConfig.set("options","registerinfo");
				accountDataGridView.getGrid().load(loadConfig);
			}
		});
	}
	public AccountDataGridView getAccountDataGridView() {
		return accountDataGridView;
	}
	
}
