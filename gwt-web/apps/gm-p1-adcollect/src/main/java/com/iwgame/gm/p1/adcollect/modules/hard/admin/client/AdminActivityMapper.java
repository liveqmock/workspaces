/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： ForceoutMgrActivityMapper.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client;

import java.util.Map;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.ClientMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.ContractMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.GroupMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.IpMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.LogosMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.MaterialMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.MediaMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.PayableMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.PositionMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.activity.SheduleMgrActivity;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.ClientMgrView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.ContractMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.GroupMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.IpMarView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.LogosMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.MaterialMgrView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.MediaMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.PayableMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.PositionMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.SheduleMgrListView;
import com.iwgame.xmvp.client.activity.AbstractModuleActivityMapper;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-2 下午05:50:45
 */
public class AdminActivityMapper extends AbstractModuleActivityMapper {
	protected PlaceController placeController;
	protected EventBus eventBus;

	@Inject
	public AdminActivityMapper(final PlaceController placeController, final EventBus eventBus) {
		super();
		this.placeController = placeController;
		this.eventBus = eventBus;
	}

	@Override
	protected Activity onGetActivity(final String action, final String[] params, final Map<String, String> queryParams) {
		if ("material".equals(action)) {
			return new MaterialMgrActivity(new MaterialMgrView());
		} else if ("media".equals(action)) {// 媒体
			return new MediaMgrActivity(new MediaMgrListView());
		} else if ("postion".equals(action)) {// 广告位
			return new PositionMgrActivity(new PositionMgrListView());
		} else if ("shedule".equals(action)) {// 广告排期
			String sid = "";
			try {
				sid = queryParams.get("id");
			} catch (final Exception e) {
			}
			return new SheduleMgrActivity(new SheduleMgrListView(sid), sid);
		} else if ("group".equals(action)) {// 广告组
			return new GroupMgrActivity(new GroupMgrListView());
		} else if ("payable".equals(action)) {// 合同抬头
			return new PayableMgrActivity(new PayableMgrListView());
		} else if ("logos".equals(action)) {// LOGS
			return new LogosMgrActivity(new LogosMgrListView());
		} else if ("contract".equals(action)) {// 合同
			return new ContractMgrActivity(new ContractMgrListView());
		} else if ("netbarip".equals(action)) {// 媒体对应ip
			String mediaId = null;
			try {
				mediaId = queryParams.get("id");
			} catch (final Exception e) {
			}
			return new IpMgrActivity(new IpMarView(mediaId), mediaId);
		} else if ("netbarclient".equals(action)) { // 网吧对应的客户端
			return new ClientMgrActivity(new ClientMgrView());
		}
		return super.onGetActivity(action, params, queryParams);
	}
}
