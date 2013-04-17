/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： SelectNumberField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui.widget;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.ListBox;
import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-6 上午09:36:00
 */
public abstract class SelectNumberField extends Field<Map<String, Object>, SelectNumber> {

	private final Map<String, Integer> indexLookup = new HashMap<String, Integer>();

	private final String selectorName;
	private final String numFieldName;

	public SelectNumberField(final String labelName, final String selectorName,
			final String numFieldName) {
		super(labelName, new SelectNumber());
		this.selectorName = selectorName;
		this.numFieldName = numFieldName;
		initItems();
	}

	/**
	 * 初始化选择项
	 */
	public abstract void initItems();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.ui.panel.client.form.field.Field#getValue()
	 */
	@Override
	public Map<String, Object> getValue() {
		ListBox listbox = widget.getListbox();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put(selectorName, listbox.getValue(listbox.getSelectedIndex()));
		result.put(numFieldName, widget.getNumbox().getValue());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.iwgame.ui.panel.client.form.field.Field#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(final Map<String, Object> value) {
		if (value.containsKey(selectorName)) {
			int index = indexLookup.get(value.get(selectorName));
			widget.getListbox().setSelectedIndex(index);
		}
		if (value.containsKey(numFieldName)) {
			widget.getNumbox().setValue(value.get(numFieldName).toString());
		}
	}

	public void setValue(String s, String n) {
		int index = indexLookup.get(s);
		widget.getListbox().setSelectedIndex(index);
		widget.getNumbox().setValue(n);
	}

	public void setValue(String s) {
		int index = indexLookup.get(s);
		widget.getListbox().setSelectedIndex(index);
	}
	
	public void addItem(final String item) {
		addItem(item, item);
	}

	public void addItem(final String item, final String value) {
		widget.getListbox().addItem(item, value);
		indexLookup.put(value, widget.getListbox().getItemCount() - 1);
	}

}
