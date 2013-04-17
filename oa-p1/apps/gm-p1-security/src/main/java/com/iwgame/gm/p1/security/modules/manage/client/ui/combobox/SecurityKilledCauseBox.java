/**      
* KilledCause.java Create on 2012-11-15     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 
package com.iwgame.gm.p1.security.modules.manage.client.ui.combobox;


import com.google.gwt.user.client.ui.ListBox;
import com.iwgame.ui.panel.client.form.field.ComboBoxField;
/** 
 * @简述: 封杀原因分类列表
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 */
@SuppressWarnings("rawtypes")
public class SecurityKilledCauseBox extends ComboBoxField {
	private String defaultHeadCaption = "--不限--";
	private boolean isAddDefaultHeadCaption=false;
	/**
	 * 构造默认标题
	 * @param label
	 */
	public SecurityKilledCauseBox(String label) {
		super(label);
		this.initBoxData(defaultHeadCaption);
	}
	
	public SecurityKilledCauseBox(String label,String fieldName) {
		super(label,fieldName);
		this.initBoxData(defaultHeadCaption);
	}

	/**
	 * 指定是否添加默认标题
	 * @param label
	 * @param isAddDefaultHeadCaption 是否要添加默认标题 
	 * 								  true 添加  false 不添加
	 */
	public SecurityKilledCauseBox(String label,boolean isAddDefaultHeadCaption) {
		super(label);
		this.isAddDefaultHeadCaption = isAddDefaultHeadCaption;
		initBoxData(null);
	}
	
	private void initBoxData(String headCaption) {
		ListBox realbox = (ListBox) getWidget();
		realbox.clear();
		// 设置标题
		if (headCaption != null&& !"".equals(headCaption.trim())) {
			addItem(headCaption, "");
		}
		
		if (isAddDefaultHeadCaption){
			addItem(defaultHeadCaption, "");
		}
		for (SecurityKilledCauseType c : SecurityKilledCauseType.values()) {
			addItem(c.getTitle(), c.getValue());
		}
	}
	
	public void setSelected(String selectedValue)
	{
		ListBox realbox = (ListBox) getWidget();
		if (selectedValue != null&& !"".equals(selectedValue.trim())) {
			int itemCount = realbox.getItemCount();
			String catalogValue;
			for (int i = 0; i < itemCount; i++) {
				catalogValue = realbox.getValue(i);
				if (catalogValue.equals(selectedValue)) {
					realbox.setSelectedIndex(i);
					break;
				}
			}
		}
	}
}
