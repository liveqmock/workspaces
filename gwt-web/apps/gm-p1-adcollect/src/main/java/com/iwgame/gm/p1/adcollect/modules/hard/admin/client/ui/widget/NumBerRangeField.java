/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： NumBerRangeField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget;

import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述： 数字区间Field
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-11 上午09:44:35
 */
public class NumBerRangeField extends Field<String, NumBerRangeBox> {

	public NumBerRangeField(final String label) {
		super(label, new NumBerRangeBox());

		addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if ((getWidget().getNumBegan().getValue() != null) && (getWidget().getNumEnd().getValue() != null)) {
					final int num1 = Integer.parseInt(getWidget().getNumBegan().getValue());
					final int num2 = Integer.parseInt(getWidget().getNumEnd().getValue());
					if (num2 < num1) {
						return "范围填写不正确";
					}
				}
				return null;
			}
		});
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(final String value) {

	}

	public void setEndValue(final String value) {
		getWidget().getNumEnd().setValue(value);
	}

	public void setNumBegan(final String value) {
		getWidget().getNumBegan().setValue(value);
	}

	public String getEndValue() {
		return getWidget().getNumEnd().getValue();
	}

	public String getBeganValue() {
		return getWidget().getNumBegan().getValue();
	}

}
