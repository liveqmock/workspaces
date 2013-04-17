/**      
* ModifyKilledCauseDialog.java Create on 2012-11-16     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.client.ui.xdialog;

import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.security.common.shared.model.KilledCauseConfig;
import com.iwgame.gm.p1.security.common.shared.util.RegexGwtHelper;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityKilledCauseView.KilledCausePresenter;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledCauseBox;
import com.iwgame.gm.p1.security.modules.manage.client.ui.combobox.SecurityKilledTypeBox;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.TextAreaField;
import com.iwgame.ui.panel.client.form.field.TextField;

/** 
 * @简述: 封杀原因新增/修改窗口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-16 上午09:36:28 
 */
public class ModifyKilledCauseDialog extends XDialogBox {
	private FormLayout formLayout;

	private XFormPanel formPanel;
	
	private SecurityKilledCauseBox killedCauseBox;
	
	private SecurityKilledTypeBox killedTypeBox;
	
	private TextField causeNum;
	
	private TextAreaField causeNote;
	
	private Integer id;
	
	private KilledCausePresenter presenter;
	
	public ModifyKilledCauseDialog(Integer id,KilledCausePresenter presenter)
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
		causeNum = new TextField("方案编号");
		causeNum.setAllowBlank(false);
		causeNum.setRegx(RegexGwtHelper.NUMBER,"方案编号只能输入数字.");
		causeNum.setMaxLength(20);
		causeNum.setValidateOnBlur(true);
		
		causeNote = new TextAreaField("封杀理由");
		causeNote.getWidget().setHeight("200px");
		causeNote.setAllowBlank(false);
		causeNote.setMaxLength(255);
		causeNote.setValidateOnBlur(true);

		killedCauseBox = new SecurityKilledCauseBox("原因分类",false);
		killedCauseBox.setAllowBlank(false);
		
		killedTypeBox = new SecurityKilledTypeBox("封杀类型",false);
		killedTypeBox.setAllowBlank(false);
		
		formLayout.add(killedCauseBox);
		formLayout.add(killedTypeBox);
		formLayout.add(causeNum);
		formLayout.add(causeNote);
		formPanel.setLayout(formLayout);
		//修改
		if (id!=null) {
			initBox("修改封杀原因", formPanel);
		}else {//新增
			initBox("新增封杀原因", formPanel);
		}
		
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
			if (causeNum.validate()&&causeNote.validate()) {
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

	private KilledCauseConfig getKilledCauseModel()
	{
		KilledCauseConfig killedCause = new KilledCauseConfig();
		if (id!=null) {
			killedCause.setId(id);
		}
		killedCause.setCauseNote(causeNote.getValue());
		killedCause.setCauseNumber(causeNum.getValue());
		killedCause.setCauseType(Integer.valueOf(killedCauseBox.getValue()));
		killedCause.setKilledType(Integer.valueOf(killedTypeBox.getValue()));
		return killedCause;
	}
	
	
	
	public KilledCausePresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(KilledCausePresenter presenter) {
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

	public SecurityKilledCauseBox getKilledCauseBox() {
		return killedCauseBox;
	}

	public void setKilledCauseBox(SecurityKilledCauseBox killedCauseBox) {
		this.killedCauseBox = killedCauseBox;
	}

	public SecurityKilledTypeBox getKilledTypeBox() {
		return killedTypeBox;
	}

	public void setKilledTypeBox(SecurityKilledTypeBox killedTypeBox) {
		this.killedTypeBox = killedTypeBox;
	}

	public TextField getCauseNum() {
		return causeNum;
	}

	public void setCauseNum(TextField causeNum) {
		this.causeNum = causeNum;
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
