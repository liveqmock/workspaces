/****************************************************************
 *  系统名称  ： 'P1项目-[gm-p1-analysis]'
 *  文件名    ： IsFirstSelector.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.analysis.client.widget;

import com.iwgame.ui.client.util.ReportSelector;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;

/**
 * 类说明
 * @简述： rpt_cost_level中是否是第首次消费列表
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-3-27 上午11:50:10
 */
@ReportSelector("firstSelector")
public class FirstSelector extends SimpleSelector{

	public FirstSelector(String labelName) {
		super(labelName);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.ui.panel.client.form.field.SimpleSelector#initItems()
	 */
	@Override
	public void initItems() {
		addItem("首次", "1");
		addItem("累计", "2");
	}


}
