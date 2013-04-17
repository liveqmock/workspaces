/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： CustomreportView.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.customreport.client.ui;

import java.util.Map;

import com.google.gwt.user.client.ui.IsWidget;
import com.iwgame.gm.p1.adcollect.shared.model.CustomReportParam;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.grid.client.view.SchemaGridViewPresenter;

/**
 * 类说明
 * @简述： 自动报表接口
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-11-12 下午02:44:45
 */
public interface CustomreportView extends IsWidget {
	// 定义View的一系列事件。。。.
	public interface Presenter extends SchemaGridViewPresenter {
		//删除任务
		public void delTask(String productId, Map<String, String> paramMap);
		//增加任务
		public void addTask(String productId, Map<String, Object> paramMap,CustomReportParam reportParam);
		
	}

	public void setPresenter(Presenter presenter);

	public Presenter getPresenter();

	public void onFailureQuery(String errorMsg);

	public void addResult(String result);

	public void delResult(String result);

	public SchemaGridView getSchemaGridView();

}
