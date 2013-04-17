/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： KeyTypeField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.crl.client.ui.widget;

import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FileUploadField;

/**
 * 类说明
 * 
 * @简述： XXXXXXX
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-8-23 上午10:49:19
 */
public class UploadTypeField extends Field<String, UploadTypeBox> {

	public UploadTypeField(final String label, final String fieldName) {
		this(label);
		setFieldName(fieldName);
	}

	public UploadTypeField(final String label) {
		super(label, new UploadTypeBox());
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
		return widget.getType();
	}

	@Override
	public void setValue(final String value) {

	}

	public String getType() {
		return widget.getType();
	}

	public String getTextKeyword() {
		return widget.getTextKeyword();
	}

	public FileUploadField getUploadField() {
		return widget.getUploadField();
	}
}
