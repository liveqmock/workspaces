/****************************************************************
 *  系统名称  ： '[gm-demo]'
 *  文件名    ： DateTimePicker.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.reports.client.ui.widget;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.iwgame.ui.client.widgets.spinner.Spinner;
import com.iwgame.ui.client.widgets.spinner.SpinnerListener;
import com.iwgame.xmvp.client.utils.DateWrapper;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-12 下午06:01:38
 */
public class DateTimePicker extends Composite {

	private final DateBox beganDateBox, endDateBox;
	private final TextBox beganHourBox, beganMinuteBox, endHourBox, endMinuteBox;
	private final Spinner beganHourSpinner, beganMinuteSpinner, endHourSpinner, endMinuteSpinner;

	private static final NumberFormat numFormat = NumberFormat.getFormat("00");

	/**
	 * 
	 */
	public DateTimePicker() {
		this(new Date());
	}

	/**
	 * 
	 */
	public DateTimePicker(final Date date) {
		beganDateBox = new DateBox();
		beganDateBox.setFormat(new DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		beganDateBox.setWidth("80px");
		beganDateBox.setValue(date);
		final DateWrapper dw = new DateWrapper(date);
		beganHourBox = new TextBox();
		beganHourBox.setWidth("20px");
		beganHourBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				final int beganHour = Integer.parseInt(beganHourBox.getValue());
				if ((beganHour > 23) || (beganHour < 0)) {
					beganHourBox.setValue("00");
				}
			}
		});
		beganMinuteBox = new TextBox();
		beganMinuteBox.setWidth("20px");
		beganMinuteBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				final int time = Integer.parseInt(beganMinuteBox.getValue());
				if ((time > 59) || (time < 0)) {
					beganMinuteBox.setValue("00");
				}
			}
		});
		beganHourSpinner = new Spinner(new SpinnerListener() {

			@Override
			public void onSpinning(final long value) {
				beganHourBox.setValue(numFormat.format(value));
			}
		}, dw.getHours(), 0, 23);

		beganMinuteSpinner = new Spinner(new SpinnerListener() {

			@Override
			public void onSpinning(final long value) {
				beganMinuteBox.setValue(numFormat.format(value));
			}
		}, dw.getMinutes(), 0, 59);
		endDateBox = new DateBox();
		endDateBox.setFormat(new DefaultFormat(DateTimeFormat.getFormat("yyyy-MM-dd")));
		endDateBox.setWidth("80px");
		endDateBox.setValue(date);
		endHourBox = new TextBox();
		endHourBox.setWidth("20px");
		endHourBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				final int time = Integer.parseInt(endHourBox.getValue());
				if ((time > 23) || (time < 0)) {
					endHourBox.setValue("00");
				}
			}
		});
		endMinuteBox = new TextBox();
		endMinuteBox.setWidth("20px");
		endMinuteBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				final int time = Integer.parseInt(endMinuteBox.getValue());
				if ((time > 59) || (time < 0)) {
					endMinuteBox.setValue("00");
				}
			}
		});
		endHourSpinner = new Spinner(new SpinnerListener() {

			@Override
			public void onSpinning(final long value) {
				endHourBox.setValue(numFormat.format(value));
			}
		}, dw.getHours(), 0, 23);

		endMinuteSpinner = new Spinner(new SpinnerListener() {

			@Override
			public void onSpinning(final long value) {
				endMinuteBox.setValue(numFormat.format(value));
			}
		}, dw.getMinutes(), 0, 59);
		final HorizontalPanel panel = new HorizontalPanel();
		panel.add(beganDateBox);
		panel.add(beganHourBox);
		panel.add(buildSpinner(beganHourSpinner));
		panel.add(beganMinuteBox);
		panel.add(buildSpinner(beganMinuteSpinner));
		panel.add(new HTML(" 至 "));
		panel.add(endDateBox);
		panel.add(endHourBox);
		panel.add(buildSpinner(endHourSpinner));
		panel.add(endMinuteBox);
		panel.add(buildSpinner(endMinuteSpinner));

		initWidget(panel);
	}

	private VerticalPanel buildSpinner(final Spinner spinner) {
		final VerticalPanel p = new VerticalPanel();
		p.add(spinner.getIncrementArrow());
		p.add(spinner.getDecrementArrow());
		return p;
	}

	public static class DefaultFormat extends DateBox.DefaultFormat {

		public DefaultFormat(DateTimeFormat dateTimeFormat) {
			super(DateTimeFormat.getFormat("yyyy-MM-dd"));
		}
	}

	/**
	 * 获取 开始时间
	 * 
	 * @return
	 */
	public Date getBeganValue() {
		DateWrapper dw = null;
		try {
			dw = new DateWrapper(beganDateBox.getValue());
			dw = dw.addHours(Integer.parseInt(beganHourBox.getValue()));
			dw = dw.addMinutes(Integer.parseInt(beganMinuteBox.getValue()));
		} catch (final Exception e) {
			return null;
		}
		return dw.asDate();
	}

	/**
	 * 获取结束时间
	 */
	public Date getEndValue() {
		DateWrapper dw = null;
		try {
			dw = new DateWrapper(endDateBox.getValue()).clearTime();
			dw = dw.addHours(Integer.parseInt(endHourBox.getValue()));
			dw = dw.addMinutes(Integer.parseInt(endMinuteBox.getValue()) + 1);
		} catch (final Exception e) {
			return null;
		}
		return dw.asDate();
	}

	/**
	 * @return the beganDateBox
	 */
	public DateBox getBeganDateBox() {
		return beganDateBox;
	}

	/**
	 * @return the endDateBox
	 */
	public DateBox getEndDateBox() {
		return endDateBox;
	}

	/**
	 * @return the beganHourBox
	 */
	public TextBox getBeganHourBox() {
		return beganHourBox;
	}

	/**
	 * @return the beganMinuteBox
	 */
	public TextBox getBeganMinuteBox() {
		return beganMinuteBox;
	}

	/**
	 * @return the endHourBox
	 */
	public TextBox getEndHourBox() {
		return endHourBox;
	}

	/**
	 * @return the endMinuteBox
	 */
	public TextBox getEndMinuteBox() {
		return endMinuteBox;
	}

	/**
	 * 赋值 给时间组件赋值 开始时间 比结束时间 玩一个小时
	 * 
	 * @param date
	 */
	public void setDateValue(Date date) {
		beganDateBox.setValue(date);
		endDateBox.setValue(date);

		final DateWrapper dateWrapper = new DateWrapper(date);

		beganHourBox.setValue(DateWrapper.format(dateWrapper.addHours(-1).asDate(), "HH"));
		beganMinuteBox.setValue(DateWrapper.format(dateWrapper.addHours(-1).asDate(), "mm"));

		endHourBox.setValue(DateWrapper.format(dateWrapper.asDate(), "HH"));
		endMinuteBox.setValue(DateWrapper.format(dateWrapper.asDate(), "mm"));
	}

	/**
	 * 设置 开始时间
	 * 
	 * @param date
	 */
	public void setBeganValue(Date date) {
		beganDateBox.setValue(date);
		final DateWrapper dateWrapper = new DateWrapper(date);
		beganHourBox.setValue(DateWrapper.format(dateWrapper.asDate(), "HH"));
		beganMinuteBox.setValue(DateWrapper.format(dateWrapper.asDate(), "mm"));
	}

	/**
	 * 设置结束时间
	 * 
	 * @param date
	 */
	public void setEndValue(Date date) {
		endDateBox.setValue(date);
		final DateWrapper dateWrapper = new DateWrapper(date);
		endHourBox.setValue(DateWrapper.format(dateWrapper.asDate(), "HH"));
		endMinuteBox.setValue(DateWrapper.format(dateWrapper.asDate(), "mm"));
	}
}
