/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： InfoDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.logs.client.ui.dialogs;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.iwgame.ui.panel.client.box.XDialogBox;

/**
 * 类说明
 * 
 * @简述： 信息弹出
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-12 上午10:50:01
 */
public class InfoDialog extends XDialogBox {
	
	private final HTMLPanel panel;
	
	public InfoDialog(String title,String content){
		panel =new HTMLPanel(content);
		panel.setWidth("350px");
		initBox(title, panel);
		setButtons(XDialogBox.CLOSE);
	}
	
	@Override
	protected void onButtonPressed(Button button) {
		if (button == getButtonByItemId(XDialogBox.CLOSE)) {
			hide();
		}
		super.onButtonPressed(button);
	}
}
