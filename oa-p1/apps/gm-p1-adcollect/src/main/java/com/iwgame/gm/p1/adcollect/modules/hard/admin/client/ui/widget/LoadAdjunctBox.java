/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LoadAdjunctBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * 类说明
 * 
 * @简述： 加载附件box
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-18 下午03:31:10
 */
public class LoadAdjunctBox extends SimplePanel {

	private HorizontalPanel hp1;
	private Label isNot;
	private Anchor href;

	public LoadAdjunctBox() {
		hp1 = new HorizontalPanel();
		isNot = new Label();
		href = new Anchor("下载附件");
		hp1.add(isNot);
		hp1.add(href);
		setWidget(hp1);
	}

	public void setIsNotValue(final String text) {
		isNot.setText(text);
	}

	public void setHrefValue(final String path) {
		href.setHref(path);
	}

	/**
	 * @return 获取 isNot
	 */
	public Label getIsNot() {
		return isNot;
	}

	/**
	 * @param isNot
	 *            设置 isNot
	 */
	public void setIsNot(final Label isNot) {
		this.isNot = isNot;
	}

	/**
	 * @return 获取 href
	 */
	public Anchor getHref() {
		return href;
	}

	/**
	 * @param href
	 *            设置 href
	 */
	public void setHref(final Anchor href) {
		this.href = href;
	}
}
