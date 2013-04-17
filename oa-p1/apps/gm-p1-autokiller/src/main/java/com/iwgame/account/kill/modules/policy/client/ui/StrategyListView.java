/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： IntelligentStrategyListView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.iwgame.account.kill.modules.policy.client.presenter.StrategyListPresenter;

/**
 * 类说明
 * 
 * @简述： 自动封杀策略视图接口
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-3 上午09:42:41
 */
public interface StrategyListView extends IsWidget{

	public void setPresenter(StrategyListPresenter presenter);
	
	public void load();
	
	public void setSchemaJson(final String json);
	
	public void reusltAddKillPolicy(Integer result);
}
