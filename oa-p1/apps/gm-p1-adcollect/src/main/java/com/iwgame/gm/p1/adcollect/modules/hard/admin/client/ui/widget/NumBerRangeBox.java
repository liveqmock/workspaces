/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： NumBerRangeBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.field.NumberBox;
import com.iwgame.ui.panel.client.form.style.Styles;

/**
 * 类说明
 * 
 * @简述： 数字区间Box
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-11 上午09:29:38
 */
public class NumBerRangeBox extends SimplePanel {

	private final HorizontalPanel hp1;
	private NumberBox numBegan;
	private NumberBox numEnd;

	/**
	 * @return 获取 numBegan
	 */
	public NumberBox getNumBegan() {
		return numBegan;
	}

	/**
	 * @param numBegan
	 *            设置 numBegan
	 */
	public void setNumBegan(final NumberBox numBegan) {
		this.numBegan = numBegan;
	}

	/**
	 * @return 获取 numEnd
	 */
	public NumberBox getNumEnd() {
		return numEnd;
	}

	/**
	 * @param numEnd
	 *            设置 numEnd
	 */
	public void setNumEnd(final NumberBox numEnd) {
		this.numEnd = numEnd;
	}

	public NumBerRangeBox() {
		hp1 = new HorizontalPanel();
		numBegan = new NumberBox();
		numBegan.addStyleName(Styles.style().xFormPanelDateRangeField());
		numEnd = new NumberBox();
		numEnd.addStyleName(Styles.style().xFormPanelDateRangeField());
		numEnd.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				final int num1 = Integer.parseInt(numBegan.getValue());
				final int num2 = Integer.parseInt(numEnd.getValue());
				if (num1 > num2) {
					MessageBox.alert("范围格式不正确应该为：下限 至  上线");
				}
			}
		});
		hp1.add(numBegan);
		hp1.add(new HTML("至"));
		hp1.add(numEnd);
		setWidget(hp1);
	}

}
