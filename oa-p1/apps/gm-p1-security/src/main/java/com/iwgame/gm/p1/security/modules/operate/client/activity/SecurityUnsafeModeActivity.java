/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityUnsafeModeActivity.java
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
import com.iwgame.gm.p1.security.modules.operate.client.ui.SecurityUnsafeModeView;
import com.iwgame.gm.p1.security.modules.operate.client.ui.SecurityUnsafeModeView.SecurityUnsafeModePresenter;
import com.iwgame.gm.p1.security.modules.operate.client.ui.SecurityUnsafeModeViewImpl;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.SafeModeBean;
import com.iwgame.ui.core.client.AsyncCallbackEx;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午10:04:04
 */
public class SecurityUnsafeModeActivity extends AbstractActivity implements SecurityUnsafeModePresenter{

	private SecurityUnsafeModeView securityUnsafeModeView;
	@Inject
	public SecurityUnsafeModeActivity(){
		
	}
	@Override
	public void goTo(Place place) {
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		securityUnsafeModeView = new SecurityUnsafeModeViewImpl(this);
		panel.setWidget(securityUnsafeModeView);
	}
	@Override
	public void onClickSubmit(SafeModeBean bean, String operator) {
		securityUnsafeModeView.doClickSubmit(bean, operator);
	}

}
