/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： KeyTypeField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.client.wiget;

import java.util.List;
import java.util.Map;

import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述： 关键字Field
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-23 上午10:49:19
 */
public class KeyTypeField extends Field<String, KeyTypeBox> {

	public KeyTypeField(final String label, String radioGroupName, final String fieldName) {
		this(label, radioGroupName);
		setFieldName(fieldName);
	}

	/**
	 * @param label
	 *            标题 这里为 null
	 * @param radioGroupName
	 *            单选按钮的组名称
	 */
	public KeyTypeField(final String label, String radioGroupName) {
		super(label, new KeyTypeBox(radioGroupName));
		addValidator(new Validator() {

			@Override
			public String validate(Field<?, ?> field) {
				if (getKeyType().equals("type")) {
					final List<String> types = getTypeList();
					if ((null == types) || (types.size() == 0)) {
						return "请选择类别";
					}
				}
				return null;
			}
		});
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setValue(String value) {

	}

	/**
	 * 获取值
	 * 
	 * @return Map <String, Object>
	 */
	public Map<String, Object> getkeyTypeValue() {
		return getWidget().getValue();
	}

	/**
	 * @return key （关键字） or type （关键字类型）
	 */
	public String getKeyType() {
		return getWidget().getKeyType();
	}

	/**
	 * @return TextBox获得的值
	 */
	public String getkey() {
		return getWidget().getKey().getValue();
	}

	/**
	 * @return List<String> 关键字类型
	 */
	public List<String> getTypeList() {
		return getWidget().getTypeValue();
	}

	public void setTypeValue(String type) {
		getWidget().setTypeValue(type);
	}
}
