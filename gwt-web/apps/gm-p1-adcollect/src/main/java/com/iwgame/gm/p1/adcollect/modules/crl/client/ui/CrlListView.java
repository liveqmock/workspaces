/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： CrlListView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.crl.client.ui;

import java.util.Map;

import com.google.gwt.user.client.ui.IsWidget;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * 类说明
 * 
 * @简述： 黑名单列表视图接口
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-9-24 上午11:57:39
 */
public interface CrlListView extends IsWidget {
	// 定义View的一系列事件。。。.
	public interface Presenter extends SchemaGridViewPresenter {
		public void insertBlackManage(String productId, Map<String, Object> paramMap);
	}

	public void setPresenter(Presenter presenter);

	public void onFailureQuery(String errorMsg);

	public void insertResult(String result);

	public SchemaGridView getSchemaGridView();

}
