/****************************************************************
 *  系统名称  ： '石器部落GM平台-[gm-wg1-player]'
 *  文件名    ： PropsSelectorField.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.client.wiget;

import com.iwgame.ui.panel.client.form.field.Field;

/**
 * 类说明
 * 
 * @简述： 自动报表媒体位选择组件
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-4-23 下午06:02:02
 */
public class ConsumReportMediaField extends Field<String, ConsumReportMediaSelector> {
	
	private static ConsumReportMediaSelector consumReportMediaSelector = null;

	public ConsumReportMediaField(final String label) {
		super(label, initPropsPanel());
	}

	@Override
	public void setValue(final String value) {
	}

	@Override
	public void setLabel(final String label) {
		super.setLabel(label);
	}

	@Override
	public void setFieldName(final String fieldName) {
		super.setFieldName(fieldName);
		widget.getListBox().setName(fieldName);
	}

	@Override
	public String getValue() {
 		return  consumReportMediaSelector.getContractId();
	}

	@Override
	public String getStringValue() {
		return  consumReportMediaSelector.getContractId();
	}
	
	
    private static ConsumReportMediaSelector initPropsPanel() {
    	consumReportMediaSelector = null;
    	consumReportMediaSelector = new ConsumReportMediaSelector();
        return consumReportMediaSelector;
    }

	
	/**
	 * 获取值
	 * 
	 * @return
	 */
	public String getMediaTypeId() {
		return  consumReportMediaSelector.getMediaTypeId();
	}
	
	public String getMediaId() {
		return consumReportMediaSelector.getMediaId();
	}

	public String getContractId() {
		return  consumReportMediaSelector.getContractId();
	}
    
	 //获取三个列表名称
	public String getMediaTypeName() {
		return  consumReportMediaSelector.getMediaTypeName();
	}
	
	public String getMediaName() {
		return  consumReportMediaSelector.getMediaName();
	}

	public String getContractName() {
		return  consumReportMediaSelector.getContractName();
	}

}
