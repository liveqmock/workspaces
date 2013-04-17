/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： InfoDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.iwgame.ui.panel.client.box.XDialogBox;

/**
 * 类说明
 * 
 * @简述： 详细排期空之类
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-17 上午11:21:35
 */
public class InfoDialog extends XDialogBox {

	/**
	 * 信息弹出框
	 * @param type 是否是练级
	 * @param title title
	 * @param info 信息内容
	 */
	public InfoDialog(Boolean type,final String title, final String info) {
		final HTMLPanel panel ;
		if(type){
			panel  = new HTMLPanel("<a href='"+info+"' target='_blank'>"+info+"</a>");
		}else{
			panel = new HTMLPanel(info);
		}
		panel.setWidth("400px");
		panel.setHeight("250px");
		initBox(title, panel);
		setButtons(CLOSE);
	}

}
