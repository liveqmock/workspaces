/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： LoadAdjunctField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget;

import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述： 加载附件组件
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-18 下午03:39:40
 */
public class LoadAdjunctField extends Field<String, LoadAdjunctBox> {

	public LoadAdjunctField(final String label) {
		super(label, new LoadAdjunctBox());
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(final String value) {
		// TODO Auto-generated method stub
	}

	public void setLoadAdds(final String path) {
		if (null != path && !path.trim().equals("")) {
			getWidget().setIsNotValue("是");
			getWidget().setHrefValue(path);
		} else {
			getWidget().setIsNotValue("否");
			getWidget().getHref().setVisible(false);
		}
	}
}
