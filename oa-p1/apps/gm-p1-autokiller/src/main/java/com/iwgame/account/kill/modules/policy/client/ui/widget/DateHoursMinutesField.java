/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： HoursMinutesSecondsField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui.widget;

import java.util.Date;

import com.google.gwt.user.client.ui.ListBox;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.xmvp.client.utils.DateWrapper;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-3 下午03:51:50
 */
public class DateHoursMinutesField extends Field<Date, DateHoursMinutes> {

	public DateHoursMinutesField(final String labelName, final String hName, final String mName,
			final String sName) {
		super(labelName, new DateHoursMinutes());
		// date_hours_minutes_field 为空  覆盖原来
		widget.getDateBox().setStyleName("date_hours_minutes_field");
		widget.getHlistbox().setStyleName("date_hours_minutes_field");
		widget.getMlistbox().setStyleName("date_hours_minutes_field");
	}

	@Override
	public Date getValue() {
		Date date = widget.getDateBox().getValue();
		DateWrapper dw = new DateWrapper(date);
		ListBox hlb = widget.getHlistbox();
		ListBox mlb = widget.getMlistbox();
		dw = dw.clearTime().addHours(hlb.getSelectedIndex() - 1);
		dw = dw.addMinutes(mlb.getSelectedIndex() - 1);
		return dw.asDate();
	}

	@Override
	public void setValue(final Date value) {
		DateWrapper dw = new DateWrapper(value);
		widget.getHlistbox().setSelectedIndex(dw.getHours() + 1);
		widget.getMlistbox().setSelectedIndex(dw.getMinutes() + 1);
		this.getWidget().getDateBox().setValue(value);
	}
}
