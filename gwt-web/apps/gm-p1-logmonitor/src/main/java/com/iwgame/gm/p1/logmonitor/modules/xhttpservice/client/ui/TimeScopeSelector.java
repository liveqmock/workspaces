/****************************************************************
 *  文件名     ： TimeScopeSelector.java
 *  日期         :  2012-10-23
 *  Company: 上海绿岸网络科技有限公司
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           		All Rights Reserved.
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.ui;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.event.HasTimeScopeSelectorHandler;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.event.TimeScopeSelectorEvent;
import com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.event.TimeScopeSelectorHandler;
import com.iwgame.ui.client.widgets.form.YearMonthDayPicker;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FieldPlugin;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.xmvp.client.utils.AppUtils;

/** 
 * @ClassName:    TimeScopeSelector 
 * @Description:  TODO(...) 
 * @author:       吴君杰
 * @email:		  wujunjie@iwgame.com
 * @date:	   	  2012-10-23上午11:54:02
 * @Version:      1.0 
 */
public class TimeScopeSelector extends SimpleSelector {

	public class TimeRangeBox extends Composite implements HasTimeScopeSelectorHandler{
		private final HorizontalPanel container = new HorizontalPanel();
		protected final YearMonthDayPicker start;
		protected final YearMonthDayPicker end;
		
		public TimeRangeBox(final int startYear, final boolean showMonth, final boolean showDay) {
			start = new YearMonthDayPicker(startYear, showMonth, showDay);
			end = new YearMonthDayPicker(startYear, showMonth, showDay);
			
			Date curdate = new Date();
			
			start.setValue(curdate);
			end.setValue(curdate);
			container.add(start);
			container.add(new HTML("~"));
			container.add(end);
			initWidget(container);
			addTimeScopeSelectorHandler(new TimeScopeSelectorHandler() {
				
				@Override
				public void onSelected(TimeScopeSelectorEvent event) {
					if(null != event.getSelectIndex() && "1".equals(event.getSelectIndex())){
						start.showMonth(true);
						end.showMonth(true);
						start.showDay(false);
						end.showDay(false);
					} else if(null != event.getSelectIndex() && "2".equals(event.getSelectIndex())){
						start.showDay(false);
						end.showDay(false);
						start.showMonth(false);
						end.showMonth(false);
					} else{
						start.showDay(true);
						end.showDay(true);
						start.showMonth(true);
						end.showMonth(true);
					}
					
				}
			});
			
		}

		/* (non-Javadoc)
		 * @see com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.event.HasTimeScopeSelectorHandler#addTimeScopeSelectorHandler(com.iwgame.gm.p1.logmonitor.modules.xhttpservice.client.event.TimeScopeSelectorHandler)
		 */
		@Override
		public HandlerRegistration addTimeScopeSelectorHandler(TimeScopeSelectorHandler handler) {
			return AppUtils.EVENT_BUS.addHandler(TimeScopeSelectorEvent.TYPE, handler);
		}

		public YearMonthDayPicker getStart() {
			return start;
		}

		public YearMonthDayPicker getEnd() {
			return end;
		}
	}
	
	public class Plugin implements FieldPlugin<TimeRangeBox>{
		
		private final TimeRangeBox timeRangeBox = new TimeRangeBox(2012, true, true);

		/* (non-Javadoc)
		 * @see com.iwgame.ui.panel.client.form.field.FieldPlugin#getWidget(com.iwgame.ui.panel.client.form.field.Field)
		 */
		@Override
		public TimeRangeBox getWidget(Field<?, ?> field) {
			return timeRangeBox;
		}
		
		public int getStartYear(){
			return timeRangeBox.getStart().getYear();
		}
		
		public int getEndYear(){
			return timeRangeBox.getEnd().getYear();
		}
		
		public int getStartMonth(){
			return timeRangeBox.getStart().getMonth();
		}
		
		public int getEndMonth(){
			return timeRangeBox.getEnd().getMonth();
		}
		
		public int getStartDay(){
			return timeRangeBox.getStart().getDay();
		}
		
		public int getEndDay(){
			return timeRangeBox.getEnd().getDay();
		}
	}
	
	private final Plugin plugin = new Plugin();
	
	@SuppressWarnings("unchecked")
	@Override
	public Plugin getPlugin() {
		return plugin;
	}

	/**
	 * @param labelName
	 */
	public TimeScopeSelector(String labelName) {
		super(labelName);
		setEnablePlugin(true);
		setPlugin(plugin);
		getWidget().addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				AppUtils.EVENT_BUS.fireEvent(new TimeScopeSelectorEvent(getValue()));
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.iwgame.ui.panel.client.form.field.SimpleSelector#initItems()
	 */
	@Override
	public void initItems() {
		addItem("按天统计", "0");
		addItem("按月统计", "1");
		addItem("按年统计", "2");
	}
}
