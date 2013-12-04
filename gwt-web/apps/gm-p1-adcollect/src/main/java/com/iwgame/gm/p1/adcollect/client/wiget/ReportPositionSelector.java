/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ReportMediaListBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.client.wiget;

import java.util.List;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 报表广告位选择列表
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-10-19 上午09:09:50
 */
public class ReportPositionSelector extends Composite {

	private final String productId = ApplicationContext.getCurrentProductId();

	// 默认显示内容
	private final String defaultprops = "==请选择广告位类型==";

	final ListBox position = new ListBox();

	// final CheckBox positionCheckBox = new CheckBox("分类求和");

	public ReportPositionSelector() {

		FlowPanel selects = new FlowPanel();
		initWidget(selects);

		selects.add(position);

		position.addItem(defaultprops, "");

		AdminMgrService.Util.get().getPositionListDorp(productId, null, new AsyncCallbackEx<List<DropDownListData>>() {

			@Override
			public void onSuccess(final List<DropDownListData> result) {
				for (DropDownListData dropDownListData : result) {
					position.addItem(dropDownListData.getName(), dropDownListData.getId());
				}
			}

		});
		position.setWidth("200px");

	}

	/**
	 * 获取值
	 * 
	 * @return
	 */
	public String getValue() {
		String positionType = position.getValue(position.getSelectedIndex());

		return positionType;
	}

	public ListBox getListBox() {
		return position;
	}
}
