/**      
* ModifyKilledCauseDialog.java Create on 2012-11-16     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.client.ui.xdialog;

import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.security.common.shared.model.SafeModeCauseConfig;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecuritySafeModeCauseView.SafeModeCausePresenter;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.ui.panel.client.form.field.TextAreaField;

/** 
 * @简述: 安全模式备注新增/修改窗口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-16 上午09:36:28 
 */
public class ModifySafeModeCauseDialog extends XDialogBox {
	private FormLayout formLayout;

	private XFormPanel formPanel;
	
	private TextAreaField causeNote;
	
	private Integer id;
	
	private SafeModeCausePresenter presenter;
	
	public ModifySafeModeCauseDialog(Integer id,SafeModeCausePresenter presenter)
	{
		this.presenter = presenter;
		
		this.id = id;
		
		this.initComponentInfo();
	}

	/**
	 * 初始化组件
	 */
	private void initComponentInfo() {
		formLayout = new FormLayout();
		formPanel = new XFormPanel();
		
		
		causeNote = new TextAreaField("原因备注");
		causeNote.getWidget().setHeight("200px");
		causeNote.setAllowBlank(false);
		causeNote.setMaxLength(255);
		causeNote.setValidateOnBlur(true);

		//修改
		if (id!=null) {
			LabelField idlabel = new LabelField("自增ID");
			idlabel.setValue(String.valueOf(id));
			idlabel.setHiddenLabel(false);
			formLayout.add(idlabel);
			initBox("修改安全模式备注", formPanel);
		}else {//新增
			initBox("新增安全模式备注", formPanel);
		}
		
		formLayout.add(causeNote);
		formPanel.setLayout(formLayout);
		setButtons(OKCANCEL);
		getButtonByItemId(OK).setText("保存");
		getButtonByItemId(CANCEL).setText("关闭");
	}

	
	@Override
	protected void onAttach() {
		super.onAttach();
		if (id!=null) {
			presenter.onAttachUpdateBox(id);
		}
	}
	
	@Override
	protected void onButtonPressed(Button button) {
		super.onButtonPressed(button);
		if (button==getButtonByItemId(OK)) {
			if (causeNote.validate()) {
				if (id!=null) {
					presenter.onClickUpdate(getKilledCauseModel());
				}
				else {
					presenter.onClickAdd(getKilledCauseModel());
				}
				
				hide();
			}else {
				MessageBox.alert("请检查填写内容.");
			}
		}
		else if (button==getButtonByItemId(CANCEL)) {
			hide();
		}
	}

	private SafeModeCauseConfig getKilledCauseModel()
	{
		SafeModeCauseConfig safeModeCause = new SafeModeCauseConfig();
		if (id!=null) {
			safeModeCause.setId(id);
		}
		safeModeCause.setCauseNote(causeNote.getValue());
		return safeModeCause;
	}
	
	
	
	public SafeModeCausePresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(SafeModeCausePresenter presenter) {
		this.presenter = presenter;
	}

	public FormLayout getFormLayout() {
		return formLayout;
	}

	public void setFormLayout(FormLayout formLayout) {
		this.formLayout = formLayout;
	}

	public XFormPanel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(XFormPanel formPanel) {
		this.formPanel = formPanel;
	}

	public TextAreaField getCauseNote() {
		return causeNote;
	}

	public void setCauseNote(TextAreaField causeNote) {
		this.causeNote = causeNote;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
