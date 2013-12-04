/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： HoursMinutesSeconds.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui.widget;

import java.util.Date;

import com.google.gwt.event.logical.shared.ShowRangeEvent;
import com.google.gwt.event.logical.shared.ShowRangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-3 下午03:43:26
 */
public class DateHoursMinutes extends Composite {

	private final DateBox dateBox = new DateBox();
	private ListBox hlistbox;
	private ListBox mlistbox;
	private final FlowPanel boxes = new FlowPanel();

	public DateHoursMinutes() {
		initWidget(boxes);
		dateBox.setWidth("65px");
		hlistbox = new ListBox();
		hlistbox.setWidth("40px");
		hlistbox.addItem("时","-1");
		for (int h = 0; h < 24; h++) {
			hlistbox.addItem(h+"",h+"");
		}
		mlistbox = new ListBox();
		mlistbox.setWidth("40px");
		mlistbox.addItem("分","-1");
		for (int m = 0; m < 60; m++) {
			mlistbox.addItem(m+"",m+"");
		}
		
		DefaultFormat format = new DateBox.DefaultFormat(
				DateTimeFormat.getFormat("y-M-d"));
		dateBox.setFormat(format);
		dateBox.setHeight("14px");
		
		boxes.add(dateBox);
		boxes.add(new InlineHTML("  "));
		boxes.add(hlistbox);
		boxes.add(new InlineHTML("："));
		boxes.add(mlistbox);
	}


	public void disableAfterToday() {
		class DisableAfterHandler implements ShowRangeHandler<Date> {
			public DisableAfterHandler(final DateBox box) {
				this.box = box;
			}

			private final DateBox box;

			@Override
			public void onShowRange(
					final ShowRangeEvent<Date> dateShowRangeEvent) {
				final Date netAllDay = today();
				nextDay(netAllDay);
				while (netAllDay.before(dateShowRangeEvent.getEnd())) {
					box.getDatePicker().setTransientEnabledOnDates(false,
							netAllDay);
					nextDay(netAllDay);
				}
			}
		}
		;
		dateBox.getDatePicker().addShowRangeHandler(
				new DisableAfterHandler(dateBox));
	}

	public DateBox getDateBox() {
		return this.dateBox;
	}

	private static Date today() {
		return zeroTime(new Date());
	}
	
	public ListBox getHlistbox() {
		return hlistbox;
	}
	public void setHlistbox(ListBox hlistbox) {
		this.hlistbox = hlistbox;
	}

	public ListBox getMlistbox() {
		return mlistbox;
	}
	public void setMlistbox(ListBox mlistbox) {
		this.mlistbox = mlistbox;
	}

	private static Date zeroTime(final Date date) {
		return DateTimeFormat.getFormat("yyyyMMdd").parse(
				DateTimeFormat.getFormat("yyyyMMdd").format(date));
	}

	private static void nextDay(final Date date) {
		com.google.gwt.user.datepicker.client.CalendarUtil.addDaysToDate(date,
				1);
	}
	
}
