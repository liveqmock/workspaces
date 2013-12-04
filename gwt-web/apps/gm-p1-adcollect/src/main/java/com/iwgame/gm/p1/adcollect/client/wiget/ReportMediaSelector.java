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

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ListBox;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.shared.rpc.AdminMgrService;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 报表媒体选择列表
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-10-19 上午09:09:50
 */
public class ReportMediaSelector extends Composite {

	private final String productId = ApplicationContext.getCurrentProductId();

	// 默认显示内容
	private final String defaultprops = "==请选择媒体类型==";
	private final String defaulttype = "==请选择媒体==";
	private final String defaultposition = "==请选择广告位类型==";

	final ListBox mediaTypeBox = new ListBox();
	final CheckBox mediaTypeCheckBox = new CheckBox("分组统计");
	final HTML widget = new HTML("分组统计");
	final ListBox mediaBox = new ListBox();
	final CheckBox mediaCheckBox = new CheckBox("分组统计");
	final ListBox position = new ListBox();

	public ReportMediaSelector() {

		FlowPanel selects = new FlowPanel();
		initWidget(selects);

		mediaTypeBox.setName("mediaType");
		selects.add(mediaTypeBox);
		selects.add(mediaTypeCheckBox);

		mediaTypeBox.addItem(defaultprops, "");

		AdminMgrService.Util.get().getType(productId, 1, new AsyncCallbackEx<List<DropDownListData>>() {

			@Override
			public void onSuccess(final List<DropDownListData> result) {
				for (DropDownListData dropDownListData : result) {
					mediaTypeBox.addItem(dropDownListData.getName(), dropDownListData.getGenerate());
				}
			}

		});
		mediaTypeBox.setWidth("200px");

		mediaBox.getElement().setId("mediaName");
		selects.add(mediaBox);
		selects.add(mediaCheckBox);
		position.setName("mediaPosition");
		selects.add(position);
		mediaBox.setWidth("200px");

		mediaTypeBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {

				mediaBox.clear();
				mediaBox.addItem(defaulttype, "");
				position.clear();
				position.addItem(defaultposition, "");

				AdminMgrService.Util.get().getMedia(productId, mediaTypeBox.getSelectedIndex(),
						new AsyncCallbackEx<List<DropDownListData>>() {

							@Override
							public void onSuccess(final List<DropDownListData> result) {
								for (DropDownListData dropDownListData : result) {
									mediaBox.addItem(dropDownListData.getName(), dropDownListData.getId());
								}
							}
						});

			}
		});

		mediaBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {

				position.clear();
				position.addItem(defaultposition, "");

				AdminMgrService.Util.get().getPositionListDorp(productId,
						mediaBox.getValue(mediaBox.getSelectedIndex()), new AsyncCallbackEx<List<DropDownListData>>() {

							@Override
							public void onSuccess(final List<DropDownListData> result) {
								for (DropDownListData dropDownListData : result) {
									position.addItem(dropDownListData.getName(), dropDownListData.getGenerate());
								}
							}
						});
				position.setWidth("200px");

			}
		});

		mediaBox.addItem(defaulttype, "");
		position.addItem(defaultposition, "");

	}

	public ListBox getListBox() {
		return mediaTypeBox;
	}

	/**
	 * 获取值
	 * 
	 * @return
	 */
	public String getValue() {
		StringBuffer result = new StringBuffer();
		result.append(mediaTypeBox.getValue(mediaTypeBox.getSelectedIndex())).append('*');
		result.append(mediaTypeCheckBox.getValue().toString()).append('*');
		result.append(mediaBox.getValue(mediaBox.getSelectedIndex())).append('*');
		result.append(mediaCheckBox.getValue().toString()).append('*');
		result.append(position.getValue(position.getSelectedIndex()));

		// String mediaType =
		// mediaTypeBox.getValue(mediaTypeBox.getSelectedIndex());
		// String mediaTypeCheck = mediaTypeCheckBox.getValue().toString();
		//
		// String media = mediaBox.getValue(mediaBox.getSelectedIndex());
		// String mediaCheck = mediaCheckBox.getValue().toString();
		// String positionValue =
		// position.getValue(position.getSelectedIndex());
		// String s = "*";
		// // if(!("").equals(positionValue)&& positionValue!=null){
		// result = mediaType + s + mediaTypeCheck + s + media + s + mediaCheck
		// + s + positionValue;
		// // }

		return result.toString();
	}

}
