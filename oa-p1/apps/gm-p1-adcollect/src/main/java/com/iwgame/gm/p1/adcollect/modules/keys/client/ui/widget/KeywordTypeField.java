/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： KeyTypeField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.keys.client.ui.widget;

import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-23 上午10:49:19
 */
public class KeywordTypeField extends Field<String, KeywordTypeBox> {

	private static KeywordTypeBox keywordTypeBox = new KeywordTypeBox();

	public KeywordTypeField(final String label, final String fieldName) {
		this(label);
		setFieldName(fieldName);
	}

	public KeywordTypeField(final String label) {
		super(label, keywordTypeBox);
		/*
		 * addValidator(new Validator() {
		 * 
		 * @Override public String validate(Field<?, ?> field) {
		 * if((keywordTypeBox.getTextKeyword() == null
		 * ||("").equals(keywordTypeBox
		 * .getTextKeyword()))&&(keywordTypeBox.getIdKeyword() == null
		 * ||("").equals(keywordTypeBox.getIdKeyword())) ){ return "搜索内容不能为空"; }
		 * return null; } });
		 */
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void setValue(String value) {

	}

	public String getType() {
		return keywordTypeBox.getType();
	}

	public String getTextKeyword() {
		return keywordTypeBox.getTextKeyword();
	}

	public String getIdKeyword() {
		return keywordTypeBox.getIdKeyword();
	}

	public String getChecked() {
		return keywordTypeBox.getChecked();
	}
}
