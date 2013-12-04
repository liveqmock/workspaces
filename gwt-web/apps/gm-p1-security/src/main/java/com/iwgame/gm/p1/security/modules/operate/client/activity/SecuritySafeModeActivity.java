/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecuritySafeModeActivity.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.iwgame.gm.p1.security.modules.operate.client.ui.SecuritySafeModeView;
import com.iwgame.gm.p1.security.modules.operate.client.ui.SecuritySafeModeView.SecuritySafeModePresenter;
import com.iwgame.gm.p1.security.modules.operate.client.ui.SecuritySafeModeViewImpl;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.SafeModeBean;
import com.iwgame.ui.core.client.AsyncCallbackEx;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午10:03:37
 */
public class SecuritySafeModeActivity extends AbstractActivity implements SecuritySafeModePresenter{

	private SecuritySafeModeView securitySafeModeView;
	@Inject
	public SecuritySafeModeActivity(){
		
	}
	
	@Override
	public void goTo(Place place) {
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		securitySafeModeView = new SecuritySafeModeViewImpl(this);
		panel.setWidget(securitySafeModeView);
	}

	@Override
	public void onClickSubmit(SafeModeBean bean, String operator) {
		securitySafeModeView.doClickSubmit(bean, operator);
	}

}
