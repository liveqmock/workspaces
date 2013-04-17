/****************************************************************
 *  系统名称  ： '[account-forceout]'
 *  文件名    ： SelectNumber.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui.widget;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.iwgame.ui.panel.client.form.field.NumberBox;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-6 上午09:37:13
 */
public class SelectNumber extends Composite{

	private HorizontalPanel holder;
	private ListBox listbox;
	private NumberBox numbox;

	public SelectNumber() {
		holder = new HorizontalPanel();
		listbox = new ListBox();
		numbox = new NumberBox();
		numbox.setValue(0);
		holder.add(listbox);
		holder.add(numbox);
		initWidget(holder);
		listbox.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				numbox.setVisible(!listbox.getValue(listbox.getSelectedIndex()).equals("-1"));
			}
		});

		setStyleName("iwgame-select-filter");
	}

	public HorizontalPanel getHolder() {
		return holder;
	}

	public void setHolder(final HorizontalPanel holder) {
		this.holder = holder;
	}

	public ListBox getListbox() {
		return listbox;
	}

	public NumberBox getNumbox() {
		return numbox;
	}

	public void setNumbox(NumberBox numbox) {
		this.numbox = numbox;
	}

	public void setListbox(ListBox listbox) {
		this.listbox = listbox;
	}

	
}
