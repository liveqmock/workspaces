/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： HoldTipInfo.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui.dialogs;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.iwgame.ui.grid.client.SchemaGrid;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-12 下午12:27:19
 */
public class HoldTipInfo extends XDialogBox {
	
	private final SchemaGrid<BaseModelData> griddata;
	
	private final HTMLPanel formpanel;
	
	public HoldTipInfo(String doing ,SchemaGrid<BaseModelData> gd){
		this.griddata = gd;
		formpanel = new HTMLPanel("正在"+doing+"，请耐心等待...");
		initBox("", formpanel);
		
		timingClose(2);
		setButtons("");
	}
	
	private void timingClose(int seconds){
		Timer t = new Timer() {
			@Override
			public void run() {
				hide();
				griddata.load();
			}
		};
		t.schedule(seconds*1000);
	}
}
