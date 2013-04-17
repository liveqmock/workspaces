/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecuritySafeModeViewImpl.java
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
import com.iwgame.gm.p1.security.modules.operate.client.ui.combobox.SafeModeCauseComboBox;
import com.iwgame.gm.p1.security.modules.operate.client.ui.widget.UsernameField;
import com.iwgame.gm.p1.security.modules.operate.client.ui.xdialog.SampleDialogBox;
import com.iwgame.gm.p1.security.modules.operate.shared.bean.SafeModeBean;
import com.iwgame.gm.p1.security.modules.operate.shared.rpc.SecuritySafeModeService;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.CheckBoxField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述：添加安全模式UI
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午09:50:50
 */
public class SecuritySafeModeViewImpl extends Composite implements SecuritySafeModeView {
	private final VerticalPanel vPanel = new VerticalPanel();
	private XUploadFormPanel formPanel;
	private FormLayout layout;
	private UsernameField dbid;
	private SafeModeCauseComboBox causenote;
	private CheckBoxField flagCheckBox;
	private HiddenField flag;
	private HiddenField pid;
	private HiddenField operator;
	private ButtonToolItem sublock;
	private LabelField label;
	private SecuritySafeModePresenter presenter;
	
	public SecuritySafeModeViewImpl(SecuritySafeModePresenter presenter){
		this.presenter = presenter;
		this.initViewInfo();
		initWidget(vPanel);
	}
	
	public void initViewInfo(){
		formPanel = new XUploadFormPanel("添加安全模式","fileUploadForSafeMode"){
			@Override
			public void onUploadCompleted(String results) {
				MessageBox.info(results);
			}
		};
		layout = new FormLayout();
		layout.setColumn(2);
		sublock = new ButtonToolItem("确认提交");
		dbid = new UsernameField("角色DBID","test", "dbid","dbids");
		dbid.setAllowBlank(false);
		flagCheckBox = new CheckBoxField("","允许自助解除安全模式","");
		flagCheckBox.setHiddenLabel(true);
		flagCheckBox.setColSpan(2);
		flag = new HiddenField("flag", "");
		causenote = new SafeModeCauseComboBox("原因备注","causenote");
		causenote.setColSpan(2);
		causenote.setWidth("50%");
		causenote.addItem("--不限--", "");
		causenote.setAllowBlank(false);
		pid = new HiddenField("pid", ApplicationContext.getCurrentProductId());
		operator = new HiddenField("operator", ApplicationContext.getCurrrentUser().getUsername());

		sublock.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(flagCheckBox.getValue()){
					flag.setValue("1");
				}else{
					flag.setValue("0");
				}
				if(formPanel.validate()){
					if("more".equals(dbid.getValue())){
						formPanel.submit();
					}else{
						SafeModeBean bean = new SafeModeBean();
						bean.setPid(pid.getValue());
						bean.setDbid(dbid.getVString());
						bean.setFlag(Integer.parseInt(flag.getValue()));
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
		layout.add(dbid);
		layout.add(2,label);
		layout.add(causenote);
		layout.add(flagCheckBox);
		layout.add(flag);
		layout.add(pid);
		layout.add(operator);
		formPanel.setLayout(layout);
		formPanel.addButton(sublock);
		vPanel.add(formPanel);
	}

	@Override
	public void doClickSubmit(SafeModeBean bean, String operator) {
		SecuritySafeModeService.Util.get().insert(bean, operator, new AsyncCallback<String>() {
			
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
