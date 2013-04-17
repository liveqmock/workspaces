/****************************************************************
 *  系统名称  ： 'P1项目-[gm-p1-analysis]'
 *  文件名    ： SourceField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.analysis.client.widget;

import com.google.gwt.user.client.ui.FlowPanel;
import com.iwgame.ui.client.util.ReportSelector;
import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述：大区组件
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-3-15 上午10:52:32
 */
@ReportSelector("guid")
public class ZoneField extends Field<String, FlowPanel> {
	public static ZoneListBox zone = null;// 来源

	public ZoneField(final String label) {
		super(label, initGuidPanel("--所有大区--"));
	}

	public ZoneField(final String label, final String headCapation) {
		super(label, initGuidPanel(headCapation));
	}

	@Override
	public String getValue() {
		return zone.getValue(zone.getSelectedIndex());
	}

	@Override
	public void setValue(final String value) {
	}

	@Override
	public void setLabel(final String label) {
		super.setLabel(label);
	}

	public void getGuid(final ZoneFieldCallback zoneFieldCallback) {
		String result = zone.getValue(zone.getSelectedIndex());
		zoneFieldCallback.getSource(result);
	}

	/**
	 * 操作类型Panel
	 * 
	 * @return
	 */
	private static FlowPanel initGuidPanel(final String headCapation) {
		FlowPanel sourceTypePanel = new FlowPanel();
		zone = new ZoneListBox(headCapation);
		zone.setWidth("150px");
		sourceTypePanel.add(zone);
		return sourceTypePanel;
	}

}