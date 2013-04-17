package com.iwgame.gm.p1.security.modules.query.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabPanel;
import com.iwgame.gm.p1.security.modules.query.client.ui.panel.AccountFuzzyQueryPanel;
import com.iwgame.gm.p1.security.modules.query.client.ui.panel.AccountRegisInfoQueryPanel;
import com.iwgame.gm.p1.security.modules.query.client.ui.panel.AccountRegisIpQueryPanel;

/**
 * 类说明
 * @简述：注册资料查询主页面
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-27 上午09:45:19
 */
public class SecurityRegisInfoQueryViewImpl extends Composite implements
		SecurityRegisInfoQueryView {
	private final int initPageSize;
	private final boolean isLoadOnAttach;
	private  String productId;
	private TabPanel tabPanel;
	private SecurityRegisInfoQueryPresenter presenter;

	//账号模糊查询
	private AccountFuzzyQueryPanel fuzzyQueryPanel;
	//注册ip查询账号
	private AccountRegisIpQueryPanel regisIpQueryPanel;
	//注册资料查询账号
	private AccountRegisInfoQueryPanel regisInfoQueryPanel;
	
	//int tab1ClickCount=0;
	
	//int tab2ClickCount=0;
	/**
	 * @param initPageSize
	 * @param isLoadOnAttach
	 * @param productId
	 * @param presenter
	 */
	public SecurityRegisInfoQueryViewImpl(int initPageSize,
			boolean isLoadOnAttach, String productId,
			SecurityRegisInfoQueryPresenter presenter) {
		super();
		this.initPageSize = initPageSize;
		this.isLoadOnAttach = isLoadOnAttach;
		this.productId = productId;
		this.presenter = presenter;
		
		initView();
	}

	/**
	 * 初始化页面组件
	 */
	private void initView() {
		//tab
		tabPanel = new TabPanel();
		
		fuzzyQueryPanel = new AccountFuzzyQueryPanel(presenter, productId, initPageSize, isLoadOnAttach);
		regisIpQueryPanel = new AccountRegisIpQueryPanel(presenter, productId, initPageSize, false);
		regisInfoQueryPanel = new AccountRegisInfoQueryPanel(presenter, productId, initPageSize, false);
		
		tabPanel.add(fuzzyQueryPanel, "账号查询");
		tabPanel.add(regisIpQueryPanel,"注册ip查询账号");
		tabPanel.add(regisInfoQueryPanel,"注册资料查询账号");
		
		tabPanel.selectTab(0);
		/*tabPanel.addBeforeSelectionHandler(new BeforeSelectionHandler<Integer>() {
			
			@Override
			public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
				int tabIndex = event.getItem();
				//初次点击,触发加载数据处理
				if (tabIndex!=0) {
				if (tab1ClickCount==0 && tabIndex==1) {
					regisIpQueryPanel.getAccountDataGridView().load(false);
					tab1ClickCount++;
				}
				if (tab2ClickCount==0 && tabIndex==2) {
					regisInfoQueryPanel.getAccountDataGridView().load(false);
					tab2ClickCount++;
				}
			  }
			}
		});*/
		initWidget(tabPanel);
	}

}
