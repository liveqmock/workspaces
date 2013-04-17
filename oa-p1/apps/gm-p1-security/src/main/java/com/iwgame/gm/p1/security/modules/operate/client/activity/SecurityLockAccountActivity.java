/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityKillAccountActivity.java
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
import com.iwgame.gm.p1.security.modules.operate.client.ui.SecurityLockAccountView;
import com.iwgame.gm.p1.security.modules.operate.client.ui.SecurityLockAccountViewImpl;
import com.iwgame.gm.p1.security.modules.operate.client.ui.SecurityLockAccountView.SecurityLockAccountPresenter;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.LockAccountBean;
import com.iwgame.ui.core.client.AsyncCallbackEx;

/**
 * 类说明
 * @简述： 账号封杀活动
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-15 上午09:39:40
 */
public class SecurityLockAccountActivity extends AbstractActivity implements SecurityLockAccountPresenter{

	private SecurityLockAccountView securityLoackAccountView;
	@Inject
	public SecurityLockAccountActivity(){
		
	}
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		securityLoackAccountView = new SecurityLockAccountViewImpl(this);
		panel.setWidget(securityLoackAccountView);
	}

	@Override
	public void goTo(Place place) {
	}

	@Override
	public void loadData(Object loadConfig, AsyncCallbackEx<String> callback) {
	}
	@Override
	public void onClickSubmit(LockAccountBean bean, String operator) {
		securityLoackAccountView.doClickSubmit(bean, operator);
	}

}
