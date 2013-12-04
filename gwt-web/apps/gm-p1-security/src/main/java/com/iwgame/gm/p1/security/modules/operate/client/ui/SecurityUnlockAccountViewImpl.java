/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityUnlockAccountViewImpl.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.security.modules.operate.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.iwgame.gm.p1.security.modules.operate.client.ui.widget.UsernameField;
import com.iwgame.gm.p1.security.modules.operate.client.ui.xdialog.SampleDialogBox;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.LockAccountBean;
import com.iwgame.gm.p1.security.modules.operate.shared.rpc.SecurityUnlockAccountService;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.CheckBoxField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述： 解封账号UI
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-15 下午03:50:28
 */
public class SecurityUnlockAccountViewImpl extends Composite implements SecurityUnlockAccountView {
	private final VerticalPanel vPanel = new VerticalPanel();
	private XUploadFormPanel formPanel;
	private FormLayout layout;
	private TextField causenote;
	private UsernameField username;
	private CheckBoxField sqloginCheckBox;
	private HiddenField sqlogin;
	private HiddenField pid;
	private HiddenField operator;
	private ButtonToolItem sublock;
	private LabelField label;
	private SecurityUnlockAccountPresenter presenter;
	public SecurityUnlockAccountViewImpl(SecurityUnlockAccountPresenter presenter){
		this.presenter = presenter;
		this.initSecurityLockAccountViewInfo();
		initWidget(vPanel);
	}
	
	public void initSecurityLockAccountViewInfo(){
		formPanel = new XUploadFormPanel("解封账号","fileUploadForUnlockAccount"){
			@Override
			public void onUploadCompleted(String results) {
				MessageBox.info(results);
			}
		};
		layout = new FormLayout();
		layout.setColumn(2);
		sublock = new ButtonToolItem("提交解封");
		username=new UsernameField("解封账号", "test","username","usernames");
		username.setAllowBlank(false);
		causenote = new TextField("解封原因", "causenote");
		causenote.setColSpan(2);
		causenote.setWidth("70%");
		sqloginCheckBox = new CheckBoxField("","解除社区登入","");
		sqloginCheckBox.setHiddenLabel(true);
		sqloginCheckBox.setColSpan(2);
		sqlogin = new HiddenField("sqlogin", "");
		pid = new HiddenField("pid", ApplicationContext.getCurrentProductId());
		operator = new HiddenField("operator", ApplicationContext.getCurrrentUser().getUsername());
		sublock.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(sqloginCheckBox.getValue()){
					sqlogin.setValue("2");
				}else{
					sqlogin.setValue("0");
				}
				if(formPanel.validate()){
					if("more".equals(username.getValue())){
						formPanel.submit();
					}else{
						LockAccountBean bean = new LockAccountBean();
						bean.setPid(pid.getValue());
						bean.setGuid("all");
						bean.setUsername(username.getVString());
						bean.setSqlogin(Integer.parseInt(sqlogin.getValue()));
						bean.setCausenote(causenote.getValue());
						
						presenter.onClickSubmit(bean, operator.getValue());
					}
				}
			}
		});
		
		label = new LabelField("查看样例");
		label.getWidget().setStyleName("cw_Label");
		label.getWidget().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				SampleDialogBox box = new SampleDialogBox();
				box.setWidth(150);
				box.setHeight(120);
				box.setPopupPosition(event.getClientX(), event.getClientY());
				box.show();
			}
		});
		layout.add(username);
		layout.add(2,label);
		layout.add(causenote);
		layout.add(sqloginCheckBox);
		layout.add(sqlogin);
		layout.add(pid);
		layout.add(operator);
		formPanel.setLayout(layout);
		formPanel.addButton(sublock);
		vPanel.add(formPanel);
	}

	@Override
	public void doClickSubmit(LockAccountBean bean, String operator) {
		SecurityUnlockAccountService.Util.get().insert(bean, operator, new AsyncCallback<String>() {
			
			@Override
			public void onSuccess(String result) {
				MessageBox.info(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				
			}
		});
	}
}
