/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SampleDialogBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client.ui.xdialog;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.ui.panel.client.box.XDialogBox;

/**
 * 类说明
 * @简述： XXXXXXX
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-26 上午09:46:47
 */
public class SampleDialogBox extends XDialogBox{
	
	public SampleDialogBox(){
		VerticalPanel panel = new VerticalPanel();
		FlexTable flexTable = new FlexTable();
		flexTable.addStyleName("cw-FlexTable");
		flexTable.setCellSpacing(0);
	    flexTable.setCellPadding(0);
	    flexTable.setWidget(0, 0, new Label("XXXX"));
	    flexTable.setWidget(1, 0, new Label("XXXX"));
	    flexTable.setWidget(2, 0, new Label("XXXX"));
	    flexTable.setWidget(3, 0, new Label("......"));
		panel.add(flexTable);
		setButtons(OK);
		initBox("批量导入txt文件格式", panel);
	}

	@Override
	protected void onButtonPressed(Button button) {
		super.onButtonPressed(button);
		if (button == getButtonByItemId(XDialogBox.OK)) {
			hide();

		}
	}

}
