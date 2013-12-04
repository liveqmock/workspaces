/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ccc.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.client.wiget;

import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述： 媒体组件
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-2-8 下午06:22:51
 */
public class MediaField extends Field<String, MediaListBox> {

	public MediaField(final String label) {
		super(label, new MediaListBox());
		widget.setWidth("200px");
	}

	@Override
	public String getValue() {
		return widget.getValue(widget.getSelectedIndex());
	}

	public String getName() {
		final String result = widget.getItemText(widget.getSelectedIndex());
		return result;
	}

	@Override
	public void setValue(final String value) {
	}

	@Override
	public void setLabel(final String label) {
		super.setLabel(label);
	}

}