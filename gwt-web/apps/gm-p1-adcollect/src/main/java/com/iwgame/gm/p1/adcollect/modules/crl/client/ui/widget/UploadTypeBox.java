/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： KeyTypeBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.crl.client.ui.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.ui.panel.client.form.field.FileUploadField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.log.XMVPLogger;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-22 下午05:59:35
 */
public class UploadTypeBox extends SimplePanel {

	private final XMVPLogger logger = new XMVPLogger(UploadTypeBox.class);

	private final HorizontalPanel hp1;
	private final VerticalPanel hp2;
	private final VerticalPanel hp3;

	private String type = "0"; // 1= 批量导入 ，0=单个录入
	private final TextField textKeyword;
	private final FileUploadField batchUploadField;

	private final RadioButton btn1;
	private final RadioButton btn2;

	public UploadTypeBox() {
		hp1 = new HorizontalPanel();
		hp1.setWidth("100%");
		btn1 = new RadioButton("1", "单个录入");
		btn2 = new RadioButton("1", "批量导入");
		btn1.setValue(true);
		btn1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				btn1Event();
			}
		});
		btn2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				btn2Event();
			}
		});

		hp1.add(btn1);
		hp1.add(btn2);

		hp2 = new VerticalPanel();
		hp2.setWidth("100%");
		textKeyword = new TextField("请输入文本");
		textKeyword.setEmptyText("请输入文本");
		textKeyword.setWidth("100%");
		batchUploadField = new FileUploadField("批量上传", "batchUploadField");
		batchUploadField.setAccepter("csv");
		batchUploadField.setWidth("100%");
		batchUploadField.getWidget().getParent().getElement().appendChild(new HTML("批量上传文件格式为 CSV").getElement());

		hp2.add(textKeyword);
		hp3 = new VerticalPanel();
		hp3.add(batchUploadField);
		hp3.setVisible(false);
		hp2.setVisible(true);
		hp1.add(hp2);
		hp1.add(hp3);
		setWidget(hp1);
	}

	private void btn1Event() {
		type = "0";
		hp2.setVisible(true);
		hp3.setVisible(false);
	}

	private void btn2Event() {

		type = "1";
		hp3.setVisible(true);
		hp2.setVisible(false);
	}

	public String getType() {
		return type;
	}

	public String getTextKeyword() {
		return textKeyword.getValue();
	}

	public FileUploadField getUploadField() {
		return batchUploadField;
	}
}
