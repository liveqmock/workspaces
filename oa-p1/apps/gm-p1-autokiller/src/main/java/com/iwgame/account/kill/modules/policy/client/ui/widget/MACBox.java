/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： MACBox.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui.widget;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable.CellFormatter;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-19 上午10:48:23
 */
public class MACBox extends SimplePanel{
	
	private final FlexTable holder;

	private final TextBox[] boxs = new TextBox[6];
	
	public MACBox(){
		holder = new FlexTable();
		holder.setStyleName("iwgame-field-ip");
		CellFormatter cFormatter = holder.getCellFormatter();
		for (int i = 0; i < 6; i++) {
			boxs[i] = new TextBox();
			boxs[i].setAlignment(TextAlignment.RIGHT);
			boxs[i].setMaxLength(2);
			holder.setWidget(0, i * 2, boxs[i]);
			cFormatter.addStyleName(0, i * 2, "iwgame-field-ip-box");
			if (i < 5) {
				holder.setHTML(0, i * 2 + 1, "-");
				cFormatter.addStyleName(0, i * 2 + 1, "iwgame-field-ip-dot");
				cFormatter.getElement(0, i * 2 + 1).getStyle()
						.setWidth(2, Unit.PX);
			}
		
		}
		setWidget(holder);
	}
	
	public String getValue() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < boxs.length; i++) {
			sb.append(boxs[i].getValue()).append("-");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public void setValue(final String value) {
		// 先找到IP地址字符串中.的位置
		int position1 = value.indexOf("-");
		int position2 = value.indexOf("-", position1 + 1);
		int position3 = value.indexOf("-", position2 + 1);
		int position4 = value.indexOf("-", position3 + 1);
		int position5 = value.indexOf("-", position4 + 1);
		// 将每个.之间的字符串转换成整型
		boxs[0].setValue(value.substring(0, position1));
		boxs[1].setValue(value.substring(position1 + 1, position2));
		boxs[2].setValue(value.substring(position2 + 1, position3));
		boxs[3].setValue(value.substring(position3 + 1, position4));
		boxs[4].setValue(value.substring(position4 + 1, position5));
		boxs[5].setValue(value.substring(position5 + 1));
	}
	
	public TextBox[] getBoxs(){
		return boxs;
	}
	
	public void setReadOnlyMac(boolean b){
		for (int i = 0; i < boxs.length; i++) {
			boxs[i].setReadOnly(b);
		} 
	}
}
