/****************************************************************
 *  系统名称  ： '广告系统-[gm-p1-adcollect]'
 *  文件名    ： ReportDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.customreport.client.ui.dialog;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.client.wiget.ConsumReportMediaField;
import com.iwgame.gm.p1.adcollect.modules.customreport.client.ui.CustomreportView;
import com.iwgame.gm.p1.adcollect.modules.customreport.client.ui.CustomreportView.Presenter;
import com.iwgame.gm.p1.adcollect.shared.model.CustomReportParam;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.ComboBoxField;
import com.iwgame.ui.panel.client.form.field.DateRangeField;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.ui.panel.client.form.field.TextAreaField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.DateWrapper;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述： 报表参数对话框
 * @作者： 李攀
 * @版本： 1.0
 * @邮箱： lipan@iwgame.com
 * @修改时间：2012-11-12 下午04:02:34
 */
public class ReportDialog extends XDialogBox {

	private CustomreportView view;
    private Presenter listener;
	private XFormPanel formPanel;
	private FormLayout layout;
	
	private final String productId = ApplicationContext.getCurrentProductId();
	private final String operator = ApplicationContext.getCurrrentUser().getUsername();
	
	private ComboBoxField type = null;//报表类型 1:留存，2：充值,
	private DateRangeField date = null; //日期
	private TextField days; //天数范围 不填为至今
	private RadioButtonGroup sumType ; //1：按广告汇总，2：按排期汇总(暂不实现)
	private ConsumReportMediaField reportMediaField; //媒体选择列表
	private TextField adID; //广告ID
	private TextField schedule; //广告排期码
	private TextAreaField remark; //解释

	public ReportDialog(final CustomreportView view) {
		this.view = view;
		listener = view.getPresenter();
		formPanel = new XFormPanel();
		formPanel.setWidth("800px");
		formPanel.setHeight("300px");
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);
		
		type = new ComboBoxField("选择报表类型");
		type.addItem("留存", "1");
		type.addItem("充值", "2");
		type.setColSpan(2);
		layout.add(type);
		
		date = new DateRangeField("日期");
		date.setColSpan(1);
		date.setAllowBlank(false);
		layout.add(date);
		
		days = new TextField("观察天数");
		days.setEmptyText("不填为至今");
		days.setColSpan(1);
		layout.add(2,days);
		
		sumType = new  RadioButtonGroup("汇总方式", "sumType", Direction.Horizontal);
		sumType.addRadioButton("按广告ID汇总","1",true);
		sumType.addRadioButton("按排期汇总","2",false);
		sumType.setColSpan(2);
		layout.add(sumType);
		
		reportMediaField = new ConsumReportMediaField("媒体选择列表");
        reportMediaField.setColSpan(2);
        layout.add(reportMediaField);
        
        adID = new TextField("广告ID");
        adID.setColSpan(1);
        layout.add(adID);
        
        schedule = new TextField("广告排期码");
        schedule.setColSpan(1);
        layout.add(2,schedule);
        
        remark = new TextAreaField("请输入描述内容");
        remark.setWidth("550px");
        remark.getWidget().setHeight("150px");
        remark.setColSpan(2);
        layout.add(remark);
        
		setButtons(OKCANCEL);
		initBox("添加定制报表", formPanel);
	}

	@Override
	protected void onButtonPressed(final Button button) {
		super.onButtonPressed(button);
		if (button == getButtonByItemId(OK)) {
			if (formPanel.validate()){
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("name", getName());
				paramMap.put("remark", remark.getValue().trim());
				paramMap.put("operator", operator);
//				paramMap.put("param", getParam());
				listener.addTask(productId, paramMap, getParam());
				hide();	
			}
		}
		if (button == getButtonByItemId(CANCEL)) {
			hide();
		}
	}
    
	//媒体类型_媒体_报表类型_汇总类型
	private String getName(){
		String name ="";
		
		if (!"".equals(reportMediaField.getMediaTypeId())) {
			name = reportMediaField.getMediaTypeName()+"_";
		}else {
			name = "所有媒体类型_";
		}
		if (!"".equals(reportMediaField.getMediaId())) {
			name = name+reportMediaField.getMediaName()+"_";
		}else {
			name = name + "所有媒体_";
		}
//		name = reportMediaField.getMediaTypeName()+"_"+reportMediaField.getMediaName()+"_"+(type.getValue().equals("1")?"留存":"充值")+"_"+(sumType.getValue().equals("1")?"ID":"排期");
		name = name + (type.getValue().equals("1")?"留存":"充值")+"_"+(sumType.getValue().equals("1")?"ID":"排期");
		return name	;	
	}

	private CustomReportParam getParam(){
		CustomReportParam param = new CustomReportParam();
		StringBuilder requestValue = new StringBuilder();
		requestValue.append("");
		if (!"".equals(adID.getValue().trim())) {
			requestValue.append("detail_ad_id = "+adID.getValue().trim()+",");
		}
		if (!"".equals(reportMediaField.getMediaTypeId())) {
			requestValue.append("detail_media_type_id = "+reportMediaField.getMediaTypeId()+",");
		}
		if (!"".equals(reportMediaField.getMediaId())) {
			requestValue.append("detail_media_id = "+reportMediaField.getMediaId()+",");
		}
		if (!"".equals(reportMediaField.getContractId())) {
			requestValue.append("detail_contract_id = "+reportMediaField.getContractId()+",");
	    }
		if (!"".equals(schedule.getValue().trim())) {
			requestValue.append("detail_shedule_id = "+schedule.getValue().trim()+",");
		}
		if (requestValue.length()== 0) {
			param.setRequest_value(" 1= 1 ");
		}else {
			param.setRequest_value(requestValue.delete(requestValue.length()-1, requestValue.length()).toString());
		}
		
		if ("".equals(days.getValue().trim())) {
			param.setReport_range("0");
		}else {
			param.setReport_range(days.getValue().trim());
		}
		param.setReport_endtime(DateWrapper.format(date.getEndValue(), "yyyy-MM-dd"));
		param.setReport_starttime(DateWrapper.format(date.getStartValue(), "yyyy-MM-dd"));
		param.setReport_type(type.getValue());
		param.setSummary_type(sumType.getValue());
        return param;	
	}
	
}

