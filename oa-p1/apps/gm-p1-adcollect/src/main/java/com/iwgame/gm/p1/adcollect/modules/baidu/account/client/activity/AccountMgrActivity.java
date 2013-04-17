/**      
 * AccountMgrActivity.java Create on 2012-10-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.modules.baidu.account.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.iwgame.gm.p1.adcollect.modules.baidu.account.client.ui.AccountMgrView;
import com.iwgame.gm.p1.adcollect.modules.baidu.account.shared.rpc.BaiduAccountService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.column.renderer.ColumnRenderer;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;
import com.iwgame.ui.grid.shared.model.ColumnConfig;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: AccountMgrActivity
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-10-11 下午03:37:35
 * @Version 1.0
 * 
 */
public class AccountMgrActivity extends AbstractActivity implements SchemaGridViewPresenter {

	private final AccountMgrView view;

	public AccountMgrActivity(final AccountMgrView view) {
		super();
		this.view = view;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.activity.shared.Activity#start(com.google.gwt.user.client
	 * .ui.AcceptsOneWidget, com.google.gwt.event.shared.EventBus)
	 */
	@Override
	public void start(final AcceptsOneWidget panel, final EventBus eventBus) {
		view.setPresenter(this);
		view.setSchemaJson("{\"table\":{\"key\":\"id\",\"columns\":["
				+ "{\"index\":\"id\",\"header\":\"ID\",\"width\":50,\"visiable\":false},"
				+ "{\"index\":\"username\",\"header\":\"帐号名\",\"width\":250},"
				+ "{\"index\":\"password\",\"header\":\"密码\",\"visiable\":false},"
				+ "{\"index\":\"token\",\"header\":\"token\",\"width\":250},"
				+ "{\"index\":\"vingve\",\"header\":\"状态\",\"type\":\"number\",\"width\":60},"
				+ "{\"index\":\"modifyTime\",\"header\":\"修改时间\",\"type\":\"date\",\"format\":\"yyyy-MM-dd HH:mm:ss\",\"width\":150},"
				+ "{\"index\":\"modifier\",\"header\":\"修改人\",\"width\":100}]}}");
		view.getPanel().setColumnRenderer("vingve", new ColumnRenderer<BaseModelData>() {

			@Override
			public void render(final int column, final int row, final BaseModelData object, final ColumnConfig config,
					final SafeHtmlBuilder sb) {
				final int vingve = object.getInt(config.getIndex());
				if (vingve == 1) {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:red'>停用</span>"));
				} else {
					sb.append(SafeHtmlUtils.fromTrustedString("<span style='color:green'>正常</span>"));
				}
			}
		});
		panel.setWidget(view);
		view.load();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#goTo(com.google
	 * .gwt.place.shared.Place)
	 */
	@Override
	public void goTo(final Place place) {
		// do nothing
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.grid.client.view.SchemaGridViewPresenter#loadData(java.
	 * lang.Object, com.iwgame.ui.core.client.AsyncCallbackEx)
	 */
	@Override
	public void loadData(final Object loadConfig, final AsyncCallbackEx<String> callback) {
		BaiduAccountService.Util.get().getAccounts(ApplicationContext.getCurrentProductId(), callback);
	}

}
