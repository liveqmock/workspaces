/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： DateTimePickerField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.client.ui.widget;

import java.util.Date;

import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-13 上午10:05:36
 */
public class DateTimePickerField extends Field<String, DateTimePicker> {

	public DateTimePickerField(String label, String fieldName) {
		this(label);
		setFieldName(fieldName);
	}

	public DateTimePickerField(String label) {
		super(label, new DateTimePicker());
		addValidator(new Validator() {

			@Override
			public String validate(Field<?, ?> field) {
				final int beganHour = Integer.parseInt(getWidget().getBeganHourBox().getValue());
				final int endHour = Integer.parseInt(getWidget().getEndHourBox().getValue());
				if ((beganHour > 23) || (beganHour < 0) || (endHour > 23) || (endHour < 0)) {
					return "时间格式不正确";
				}
				final int beganMinute = Integer.parseInt(getWidget().getBeganMinuteBox().getValue());
				final int endHoMinute = Integer.parseInt(getWidget().getEndMinuteBox().getValue());
				if ((beganMinute > 59) || (beganMinute < 0) || (endHoMinute > 59) || (endHoMinute < 0)) {
					return "时间格式不正确";
				}
				return null;
			}
		});
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setValue(String value) {

	}

	/**
	 * 获取 开始时间
	 * 
	 * @return
	 */
	public Date getBeganValue() {
		return getWidget().getBeganValue();
	}

	/**
	 * 获取结束时间
	 */
	public Date getEndValue() {
		return getWidget().getEndValue();
	}

	/**
	 * 设置开始时间
	 * 
	 * @param date
	 */
	public void setBeganValue(Date date) {
		getWidget().setBeganValue(date);
	}

	/**
	 * 设置结束时间
	 * 
	 * @param date
	 */
	public void setEndValue(Date date) {
		getWidget().setEndValue(date);
	}

	public void setDateValue(Date date) {
		getWidget().setDateValue(date);
	}
}
