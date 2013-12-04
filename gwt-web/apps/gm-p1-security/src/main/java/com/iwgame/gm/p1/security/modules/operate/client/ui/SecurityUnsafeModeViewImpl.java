/****************************************************************
 *  系统名称  ： ''
 *  文件名    ： SecurityUnsafeModeViewImpl.java
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
import com.iwgame.gm.p1.security.modules.operate.shared.bean.SafeModeBean;
import com.iwgame.gm.p1.security.modules.operate.shared.rpc.SecurityUnsafeModeService;
import com.iwgame.ui.grid.client.toolbar.ButtonToolItem;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * @简述： 解除安全模式UI
 * @作者： chenhuibo
 * @版本： 1.0
 * @邮箱： chenhuibo@iwgame.com
 * @修改时间：2012-11-21 上午09:52:18
 */
public class SecurityUnsafeModeViewImpl extends Composite implements
		SecurityUnsafeModeView {
	private final VerticalPanel vPanel = new VerticalPanel();
	private XUploadFormPanel formPanel;
	private FormLayout layout;
	private UsernameField dbid;
	private TextField causenote;
	private HiddenField pid;
	private HiddenField operator;
	private ButtonToolItem sublock;
	private LabelField label;
	private SecurityUnsafeModePresenter presenter;

	public SecurityUnsafeModeViewImpl(SecurityUnsafeModePresenter presenter){
		this.presenter = presenter;
		this.initSecurityLockAccountViewInfo();
		initWidget(vPanel);
	}
	
	public void initSecurityLockAccountViewInfo(){
		formPanel = new XUploadFormPanel("解除安全模式","fileUploadForUnsafeMode"){
			@Override
			public void onUploadCompleted(String results) {
				MessageBox.info(results);
			}
		};
		layout = new FormLayout();
		sublock = new ButtonToolItem("确认提交");
		dbid = new UsernameField("角色DBID","test", "dbid","dbids");
		dbid.setAllowBlank(false);
		causenote = new TextField("解除原因", "causenote");
		causenote.setColSpan(2);
		pid = new HiddenField("pid", ApplicationContext.getCurrentProductId());
		operator = new HiddenField("operator", ApplicationContext.getCurrrentUser().getUsername());
		sublock.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(formPanel.validate()){
					if("more".equals(dbid.getValue())){
						formPanel.submit();
					}else{
						SafeModeBean bean = new SafeModeBean();
						bean.setPid(pid.getValue());
						bean.setDbid(dbid.getVString());
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
		layout.add(pid);
		layout.add(operator);
		formPanel.setLayout(layout);
		formPanel.addButton(sublock);
		vPanel.add(formPanel);
	}

	@Override
	public void doClickSubmit(SafeModeBean bean, String operator) {
		SecurityUnsafeModeService.Util.get().insert(bean, operator, new AsyncCallback<String>() {
			
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
