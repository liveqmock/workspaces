/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： UploadDictionary.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.explain.client.ui.dialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.iwgame.gm.p1.adcollect.modules.explain.client.ui.DictionaryMgrView;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.FileUploadField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @Version 2.1
 * @email wangpengfei@iwgame.com
 * @date 2012-12-20 上午11:11:06
 */
public class UploadDictionary extends XDialogBox {

	private XUploadFormPanel formPanel;
	private FormLayout layout;

	private FileUploadField uploadDictionary;
	private TextField name;
	private TextField function;
	private HiddenField productIdField;
	
	public UploadDictionary(final DictionaryMgrView mgrview) {

		formPanel = new XUploadFormPanel(null, "dataDictionaryUploadProcessor") {
			@Override
			public void onUploadCompleted(String results) {
				if(results.equals("1")){
					mgrview.getGrid().load();
					hide();
				}else{
					MessageBox.alert("上传数据字典失败");
				}
				super.onUploadCompleted(results);
			}
		};
		formPanel.setWidth("600px");
		layout = new FormLayout();
		formPanel.setLayout(layout);

		uploadDictionary = new FileUploadField("数据字典", "dictionary");
		uploadDictionary.setAccepter("csv");
		uploadDictionary.setAllowBlank(false);
		uploadDictionary.setValidateOnBlur(true);
		uploadDictionary.setColSpan(2);
		layout.add(uploadDictionary);

		name = new TextField("文件名", "name");
		name.setAllowBlank(false);
		name.setValidateOnBlur(true);
		name.getWidget().setMaxLength(50);
		layout.add(name);

		function = new TextField("主要功能", "function");
		function.getWidget().setMaxLength(100);
		layout.add(function);

		productIdField = new HiddenField("productId", "productId");
		productIdField.setValue(ApplicationContext.getCurrentProductId());
		layout.add(productIdField);
		
		String title = "上传数据字典";
		initBox(title, formPanel);
		setButtons(OKCANCEL);

		formPanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			
			@Override
			public void onSubmitComplete(final SubmitCompleteEvent event) {
				
			}
		});
	}

	@Override
	protected void onButtonPressed(final Button button) {
		super.onButtonPressed(button);
		if (button == getButtonByItemId(OK)) {
			if (formPanel.validate()) {
				formPanel.submit();
			}
		}
		if (button == getButtonByItemId(CANCEL)) {
			hide();
		}
	}
}
