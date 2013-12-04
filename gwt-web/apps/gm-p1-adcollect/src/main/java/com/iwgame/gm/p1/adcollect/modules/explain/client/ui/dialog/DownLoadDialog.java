/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： DownLoadDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.explain.client.ui.dialog;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.iwgame.ui.panel.client.box.XDialogBox;

/**
 * @Description: 下载弹出框
 * @author ByName's pfwang
 * @Version 2.1
 * @email  wangpengfei@iwgame.com
 * @date 2012-12-20 下午3:46:15
 */
public class DownLoadDialog extends XDialogBox {

	/**
	 * 信息弹出框
	 */
	public DownLoadDialog(final String title, final String link) {
		final HTMLPanel panel ;
		panel  = new HTMLPanel("<a href='"+link+"' target='_blank'>下载数据字典</a>");
		panel.setWidth("200px");
		panel.setHeight("40px");
		initBox(title, panel);
		setButtons(CLOSE);
	}
}

