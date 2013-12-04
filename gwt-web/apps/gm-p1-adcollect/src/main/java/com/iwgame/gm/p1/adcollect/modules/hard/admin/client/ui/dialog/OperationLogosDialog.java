/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： OperationLogosDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.LogosMgrListView;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.FileUploadField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 添加修改 LOGO
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午03:30:21
 */
public class OperationLogosDialog extends XDialogBox {

	private final XUploadFormPanel formpanel;
	private final FormLayout layout;

	private final TextField name;
	private final FileUploadField logos;
	private final HiddenField productIdField;
	private final HiddenField addOperationField;
	private final HiddenField idField;

	public OperationLogosDialog(final boolean type, final LogosMgrListView view) {

		formpanel = new XUploadFormPanel(null, "logosloadprocessor");
		formpanel.setWidth("600px");
		layout = new FormLayout();
		formpanel.setLayout(layout);

		name = new TextField("Logo 名称", "name");
		name.setMaxLength(50);
		name.setAllowBlank(false);
		name.setValidateOnBlur(true);
		layout.add(name);

		logos = new FileUploadField("Logo", "logos");
		if(type){
			logos.setAllowBlank(false);
			logos.setValidateOnBlur(true);
		}
		logos.setAccepter("jpg", "png", "gif");
		layout.add(logos);

		productIdField = new HiddenField("productId", "productId");
		productIdField.setValue(ApplicationContext.getCurrentProductId());
		layout.add(productIdField);

		addOperationField = new HiddenField("operation", "operation");
		addOperationField.setValue(type ? "add" : "update");
		layout.add(addOperationField);

		idField = new HiddenField("id", null);
		layout.add(idField);

		if (type) {
			initBox("添加 Logo", formpanel);
		} else {
			final Double d = view.getGrid().getSelected().get(0).get("id");
			idField.setValue(d.intValue() + "");
			name.setValue(view.getGrid().getSelected().get(0).get("name").toString());
			initBox("修改 Logo", formpanel);
		}
		setButtons(XDialogBox.OKCANCEL);

		formpanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(final SubmitCompleteEvent event) {
				hide();
				view.getGrid().load();
			}
		});
	}

	@Override
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(XDialogBox.OK)) {
			// 调用添加方法
			if (formpanel.validate()) {
				formpanel.submit();
			}
		}
		if (button == getButtonByItemId(XDialogBox.CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
	}
}
