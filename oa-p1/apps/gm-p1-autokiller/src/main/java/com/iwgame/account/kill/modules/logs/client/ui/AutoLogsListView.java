/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： AutoLogsListView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.logs.client.ui;

import com.google.gwt.user.client.ui.IsWidget;
import com.iwgame.account.kill.modules.logs.client.presenter.AutoLogsListPresenter;

/**
 * 类说明
 * 
 * @简述： 封停日志视图接口
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-4 下午02:12:34
 */
public interface AutoLogsListView extends IsWidget {
	public void setPresenter(AutoLogsListPresenter presenter);
	
	public void load();
	
	public void setSchemaJson(final String json);
}
