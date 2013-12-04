/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： OperateActivityMapper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.security.modules.operate.client.activity.SecurityLockAccountActivity;
import com.iwgame.gm.p1.security.modules.operate.client.activity.SecuritySafeModeActivity;
import com.iwgame.gm.p1.security.modules.operate.client.activity.SecurityUnlockAccountActivity;
import com.iwgame.gm.p1.security.modules.operate.client.activity.SecurityUnsafeModeActivity;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/**
 * 
 * @简述： 
 * @作者： Maven自动生成
 * @版本： 1.0
 */
public class OperateActivityMapper extends AbstractModuleActivityMapper {
	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public OperateActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> OperateParams) {
		if("securityLockAccount".equals(action)){
			return new SecurityLockAccountActivity();
		}else if("securityUnlockAccount".equals(action)){
			return new SecurityUnlockAccountActivity();
		}else if("securitySafeMode".equals(action)){
			return new SecuritySafeModeActivity();
		}else if("securityUnsafeMode".equals(action)){
			return new SecurityUnsafeModeActivity();
		}else {
			return super.onGetActivity(action, params, OperateParams);
		}
	}
}
