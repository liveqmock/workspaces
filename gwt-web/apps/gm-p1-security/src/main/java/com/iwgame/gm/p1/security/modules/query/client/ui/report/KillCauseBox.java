/****************************************************************
 *  系统名称  ： 'P1项目-[gm-p1-analysis]'
 *  文件名    ： IsFirstSelector.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.query.client.ui.report;

import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledCauseType;
import com.iwgame.ui.client.util.ReportSelector;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;

/** 
 * @简述: 封杀记录查询视图接口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-21 下午15:05:45 
 */
@ReportSelector("killCause")
public class KillCauseBox extends SimpleSelector{

	public KillCauseBox(String labelName) {
		super(labelName);
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iwgame.ui.panel.client.form.field.SimpleSelector#initItems()
	 */
	@Override
	public void initItems() {
		addItem("--不限--", "");
		for (SecurityKilledCauseType c : SecurityKilledCauseType.values()) {
			addItem(c.getTitle(), c.getValue());
		}
	}


}
