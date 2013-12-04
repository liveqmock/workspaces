/****************************************************************
 *  系统名称  ： '[killer]'
 *  文件名    ： MACField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.account.kill.modules.policy.client.ui.widget;

import com.google.gwt.regexp.shared.RegExp;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-7-19 上午10:47:57
 */
public class MACField extends Field<String, MACBox> {

	public MACField(final String label, final String fieldName) {
		this(label);
		setFieldName(fieldName);
	}

	public MACField(final String label) {
		super(label, new MACBox());
		addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				RegExp regexp = RegExp.compile("^[a-f|A-F|\\d][a-f|A-F|\\d](-[a-f|A-F|\\d][a-f|A-F|\\d]){5}$");
				if (regexp.exec(getValue()) == null) {
					return "MAC地址格式不正确";
				}
				return null;
			}
		});
	}

	@Override
	public String getValue() {
		return getWidget().getValue();
	}

	@Override
	public void setValue(String value) {
		getWidget().setValue(value);
	}
	
	public void setReadOnlyMac(boolean readOnly){
		getWidget().setReadOnlyMac(readOnly);
	}
}
