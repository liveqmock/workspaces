/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： KeyTypeBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.client.wiget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * 类说明
 * 
 * @简述： 关键字Box
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-22 下午05:59:35
 */
public class KeyTypeBox extends SimplePanel {

	private final HorizontalPanel hp1;
	private final HorizontalPanel hp2;
	private final HorizontalPanel hp3;

	private String keyType = "key";
	private final CheckBox[] type;
	private TextBox key;

	private final RadioButton btn1;
	private final RadioButton btn2;

	public KeyTypeBox(String radioGroupName) {
		hp1 = new HorizontalPanel();
		btn1 = new RadioButton(radioGroupName, "输入关键字");
		btn2 = new RadioButton(radioGroupName, "请选择类别");
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

		type = new CheckBox[4];
		type[0] = new CheckBox("A品牌类型");
		type[0].setFormValue("A");
		type[1] = new CheckBox("B竞品词");
		type[1].setFormValue("B");
		type[2] = new CheckBox("C通用词");
		type[2].setFormValue("C");
		type[3] = new CheckBox("D无关词");
		type[3].setFormValue("D");
		key = new TextBox();
		hp1.add(btn1);
		hp1.add(btn2);

		hp2 = new HorizontalPanel();
		hp2.add(type[0]);
		hp2.add(type[1]);
		hp2.add(type[2]);
		hp2.add(type[3]);
		hp3 = new HorizontalPanel();
		hp3.add(key);
		hp2.setVisible(false);
		hp1.add(hp2);
		hp1.add(hp3);
		setWidget(hp1);
	}

	private void btn1Event() {
		keyType = "key";
		hp2.setVisible(false);
		hp3.setVisible(true);
		setType(false);
	}

	private void btn2Event() {

		keyType = "type";
		hp3.setVisible(false);
		hp2.setVisible(true);
		key.setValue("");
	}

	public void setTypeValue(String appointType) {
		btn2Event();
		for (final CheckBox checkBox : type) {
			if (appointType.equals(checkBox.getFormValue())) {
				checkBox.setValue(true);
			}
		}
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}

	public CheckBox[] getType() {
		return type;
	}

	public void setType(boolean type) {
		for (final CheckBox checkBox : this.type) {
			checkBox.setValue(type);
		}
	}

	public TextBox getKey() {
		return key;
	}

	public void setKey(TextBox key) {
		this.key = key;
	}

	public List<String> getTypeValue() {
		final List<String> typeList = new ArrayList<String>();
		for (final CheckBox checkBox : type) {
			if (checkBox.getValue()) {
				typeList.add(checkBox.getFormValue());
			}
		}
		return typeList;
	}

	/**
	 * key 1 keyType 2
	 * 
	 * @return
	 */
	public Map<String, Object> getValue() {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyType", keyType);
		if (keyType.equals("key")) {// 关键字
			map.put("key", key.getValue());
		} else if (keyType.equals("type")) {
			final List<String> typeList = new ArrayList<String>();
			for (final CheckBox checkBox : type) {
				if (checkBox.getValue()) {
					typeList.add(checkBox.getText().substring(0, 1));
				}
			}
			map.put("type", typeList);
		}
		return map;
	}
}
