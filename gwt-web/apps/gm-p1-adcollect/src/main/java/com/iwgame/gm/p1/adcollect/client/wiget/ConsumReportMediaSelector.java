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
 * @简述：自动 报表媒体选择列表
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-10-19 上午09:09:50
 */
public class ConsumReportMediaSelector extends Composite {

	private final String productId = ApplicationContext.getCurrentProductId();

	// 默认显示内容
	private final String defaultprops = "==请选择媒体类型==";
	private final String defaulttype = "==请选择媒体==";
	private final String defaultcontact = "==请选择合同编号 ==";

	final ListBox mediaTypeBox = new ListBox();
	final ListBox mediaBox = new ListBox();
	final ListBox contact  = new ListBox();

	public ConsumReportMediaSelector() {

		FlowPanel selects = new FlowPanel();
		initWidget(selects);

		mediaTypeBox.setName("mediaType");
		selects.add(mediaTypeBox);

		mediaTypeBox.addItem(defaultprops, "");

		AdminMgrService.Util.get().getType(productId, 1, new AsyncCallbackEx<List<DropDownListData>>() {

			@Override
			public void onSuccess(final List<DropDownListData> result) {
				for (DropDownListData dropDownListData : result) {
					mediaTypeBox.addItem(dropDownListData.getName(), dropDownListData.getId());
				}
			}

		});
		mediaTypeBox.setWidth("160px");

		mediaBox.getElement().setId("mediaName");
		selects.add(mediaBox);
		contact.setName("contact");
		contact.setName("contact");
		selects.add(contact);
		mediaBox.setWidth("160px");
		contact.setWidth("160px");
		mediaTypeBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {

				mediaBox.clear();
				mediaBox.addItem(defaulttype, "");
				contact.clear();
				contact.addItem(defaultcontact, "");

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

				contact.clear();
				contact.addItem(defaultcontact, "");

				AdminMgrService.Util.get().getContactListDorp(productId,
						mediaBox.getValue(mediaBox.getSelectedIndex()), new AsyncCallbackEx<List<DropDownListData>>() {

							@Override
							public void onSuccess(final List<DropDownListData> result) {
								for (DropDownListData dropDownListData : result) {
									contact.addItem(dropDownListData.getGenerate(), dropDownListData.getId());
								}
							}
						});
				contact.setWidth("160px");

			}
		});

		mediaBox.addItem(defaulttype, "");
		contact.addItem(defaultcontact, "");

	}

	public ListBox getListBox() {
		return mediaTypeBox;
	}

	/**
	 * 获取值
	 * 
	 * @return
	 */
	public String getMediaTypeId() {
		return  mediaTypeBox.getValue(mediaTypeBox.getSelectedIndex());
	}
	
	public String getMediaId() {
		return  mediaBox.getValue(mediaBox.getSelectedIndex());
	}

	public String getContractId() {
		return  contact.getValue(contact.getSelectedIndex());
	}
   //获取三个列表
	public String getMediaTypeName() {
		return  mediaTypeBox.getItemText(mediaTypeBox.getSelectedIndex());
	}
	
	public String getMediaName() {
		return  mediaBox.getItemText(mediaBox.getSelectedIndex());
	}

	public String getContractName() {
		return  contact.getItemText(contact.getSelectedIndex());
	}
}
