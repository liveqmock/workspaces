/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： KeyTypeBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.keys.client.ui.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
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
public class KeywordTypeBox extends SimplePanel {

	private final XMVPLogger logger = new XMVPLogger(KeywordTypeBox.class);

	private final HorizontalPanel hp1;
	private final HorizontalPanel hp2;
	private final HorizontalPanel hp3;

	private String type = "0"; // 1= 按关键字 ，0=按广告ID
	private final TextBox textKeyword;
	private final TextBox idKeyword;
	private final RadioButton accurate;
	private final RadioButton blur;

	private final RadioButton btn1;
	private final RadioButton btn2;

	public KeywordTypeBox() {
		hp1 = new HorizontalPanel();
		btn1 = new RadioButton("a", "按广告ID");
		btn2 = new RadioButton("a", "按关键字");
		/*
		 * btn1.setEnabled(false);
		 * btn1.getElement().getStyle().setBackgroundColor("gray");
		 * btn1.getElement().getStyle().setColor("#aaa"); btn2.setEnabled(true);
		 * btn2.getElement().getStyle().setColor("black");
		 */
		btn1.setValue(true);
		btn1.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btn1Event();
			}
		});
		btn2.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				btn2Event();
			}
		});

		hp1.add(btn1);
		hp1.add(btn2);

		hp2 = new HorizontalPanel();
		textKeyword = new TextBox();
		textKeyword.setWidth("120px");
		idKeyword = new TextBox();
		idKeyword.setWidth("120px");
		accurate = new RadioButton("0", "精确");
		accurate.setValue(true);
		blur = new RadioButton("0", "模糊");

		hp2.add(textKeyword);
		hp2.add(accurate);
		hp2.add(blur);
		hp3 = new HorizontalPanel();
		hp3.add(idKeyword);
		hp2.setVisible(false);
		hp1.add(hp2);
		hp1.add(hp3);
		setWidget(hp1);
	}

	private void btn1Event() {
		type = "0";
		hp2.setVisible(false);
		hp3.setVisible(true);
		/*
		 * btn1.setEnabled(false);
		 * btn1.getElement().getStyle().setBackgroundColor("gray");
		 * btn1.getElement().getStyle().setColor("#aaa"); btn2.setEnabled(true);
		 * btn2.getElement().getStyle().setColor("black");
		 */
	}

	private void btn2Event() {

		type = "1";
		hp3.setVisible(false);
		hp2.setVisible(true);
		/*
		 * btn2.setEnabled(false);
		 * btn2.getElement().getStyle().setBackgroundColor("gray");
		 * btn2.getElement().getStyle().setColor("#aaa"); btn1.setEnabled(true);
		 * btn1.getElement().getStyle().setColor("black");
		 */
	}

	public String getType() {
		return type;
	}

	public String getTextKeyword() {
		return textKeyword.getValue();
	}

	public String getIdKeyword() {
		return idKeyword.getValue();
	}

	public String getChecked() {
		if (accurate.getValue()) {
			return "0";
		} else {
			return "1";
		}
	}
}
