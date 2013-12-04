/****************************************************************
 *  系统名称  ： 'P1项目-[gm-p1-analysis]'
 *  文件名    ： SourceListBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.analysis.client.widget;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.ListBox;
import com.iwgame.gm.p1.analysis.modules.report.shared.rpc.ReportService;
import com.iwgame.gm.p1.analysis.shared.model.Zone;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述：大区组件
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-3-15 上午10:50:39
 */
public class ZoneListBox extends ListBox {

	private String defaultHeadCaption = "查询全部";

	public ZoneListBox() {
		initListBox(defaultHeadCaption);
	}

	public ZoneListBox(String headCapation) {
		initListBox(headCapation);
	}
	
	/**
	 * 初始化ListBox
	 * @param headCapation
	 */
	private void initListBox(String headCapation) {
		// 添加行头
		super.addItem(headCapation, "");
		ReportService.Util.get().getZone(ApplicationContext.getCurrentProduct(), new AsyncCallback<List<Zone>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(List<Zone> result) {
					if (result != null && result.size() > 0) {
						for (int i = 0; i < result.size(); i++) {
							Zone zone= result.get(i);
							ZoneListBox.super.addItem(zone.getZonedesc(),zone.getZonename());
						}
					}
				}
				
		});

		
	}		
	

}
