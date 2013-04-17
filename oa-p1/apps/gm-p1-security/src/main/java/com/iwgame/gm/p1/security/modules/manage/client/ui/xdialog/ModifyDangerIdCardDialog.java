/**      
* ModifyDangerIdCardDialog.java Create on 2012-11-19     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.manage.client.ui.xdialog;


import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.security.common.shared.model.DangerIdCard;
import com.iwgame.gm.p1.security.common.shared.util.RegexGwtHelper;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityDangerIdCardView;
import com.iwgame.gm.p1.security.modules.manage.client.ui.SecurityDangerIdCardView.DangerIdCardPresenter;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.FileUploadField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.ui.panel.client.form.field.TextAreaField;
import com.iwgame.ui.panel.client.form.field.TextField;

/** 
 * @简述: 高危身份证新增/修改窗口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期: 2012-11-19 下午01:50:23 
 */
public class ModifyDangerIdCardDialog extends XDialogBox {
	private FormLayout formLayout;

	private XUploadFormPanel formPanel;
	
	private TextAreaField causeNote;
	
	private TextField idCard;
	
	private FileUploadField idCardUpload;
	
	private Integer id;
	
	private DangerIdCardPresenter presenter;
	//是否是上传
	private boolean isUpload = false;
	
	private String productId;
	
	private SecurityDangerIdCardView dangerIdCardView;
	
	private HiddenField pidField;
	/**
	 * 新增
	 * @param id
	 * @param presenter
	 * @param securityDangerIdCardViewImpl
	 */
	public ModifyDangerIdCardDialog(Integer id,DangerIdCardPresenter presenter, SecurityDangerIdCardView securityDangerIdCardViewImpl,String productId)
	{
		this.presenter = presenter;
		
		this.id = id;
		
		this.dangerIdCardView = securityDangerIdCardViewImpl;
		
		this.productId = productId;
		
		this.initComponentInfo();
	}
	/**
	 * 修改
	 * @param id
	 * @param presenter
	 */
	public ModifyDangerIdCardDialog(Integer id,DangerIdCardPresenter presenter,String productId)
	{
		this.presenter = presenter;
		
		this.id = id;
		
		this.productId = productId;
		
		this.initComponentInfo();
	}
	
	/**
	 * 初始化组件
	 */
	private void initComponentInfo() {
		formLayout = new FormLayout();
		formPanel = new XUploadFormPanel("dangerIdCardUploadHandler"){
			public void onUploadCompleted(String results) {
				MessageBox.info(results);
				dangerIdCardView.getsSchemaGridView().getPanel().getGrid().load();
				hide();
			};
		};
		pidField = new HiddenField("pid", productId);
		idCard = new TextField("身份证");
		
		causeNote = new TextAreaField("原因备注");
		causeNote.getWidget().setHeight("200px");
		causeNote.setMaxLength(255);
		causeNote.setValidateOnBlur(true);
		//修改
		if (id!=null) {
			idCard.getWidget().setEnabled(false);
			initBox("修改高危身份证", formPanel);
		}else {//新增
			idCardUpload = new FileUploadField("批量上传", "idCardFile");
			idCardUpload.setAccepter("txt");
			idCardUpload.getWidget().addChangeHandler(new ChangeHandler() {
				
				@Override
				public void onChange(ChangeEvent event) {
					idCard.getWidget().setEnabled(false);
					idCard.setValue("批量上传无法手动添加");
					causeNote.getWidget().setEnabled(false);
					causeNote.setValue("批量上传无法手动添加");
					//上传
					isUpload = true;
				}
			});

			LabelField note = new LabelField("提示");
			note.setValue("只能上传txt文件,按tab键分割列,第一列为身份证号码,第二列为原因备注.");
			note.setHiddenLabel(false);
			formLayout.add(idCardUpload);
			formLayout.add(note);
			formLayout.add(pidField);
			initBox("新增高危身份证", formPanel);
		}
		formLayout.add(idCard);
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
			if (isUpload) {//文件上传,提交表单
				formPanel.submit();
				
			}else {
				if (idCard.validate()&&causeNote.validate()) {
					if (id!=null) {
						presenter.onClickUpdate(getDangerIdCardModel());
						hide();
					}
					else {
						DangerIdCard dangerIdCard = getDangerIdCardModel();
						if (dangerIdCard!=null) {
							presenter.onClickAdd(dangerIdCard);
							hide();
						}
					}
				}else {
					MessageBox.alert("请检查填写内容.");
					return;
				}
			}
		}
		else if (button==getButtonByItemId(CANCEL)) {
			hide();
		}
	}

	private DangerIdCard getDangerIdCardModel()
	{
		DangerIdCard dangerIdCard = new DangerIdCard();
		if (id!=null) {
			dangerIdCard.setId(id);
		}
		if (isUpload==false) {
			if (RegexGwtHelper.isIdCard(idCard.getValue())) {
				dangerIdCard.setIdCard(idCard.getValue());
			}else {
				MessageBox.alert("不是合法的身份证格式");
				return null;
			}
		}
		dangerIdCard.setCauseNote(causeNote.getValue());
		return dangerIdCard;
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

	public DangerIdCardPresenter getPresenter() {
		return presenter;
	}

	public void setPresenter(DangerIdCardPresenter presenter) {
		this.presenter = presenter;
	}

	public TextField getIdCard() {
		return idCard;
	}

	public void setIdCard(TextField idCard) {
		this.idCard = idCard;
	}
}
