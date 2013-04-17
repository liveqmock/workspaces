/**      
* AccountDetailsDialogBox.java Create on 2012-12-21     
*      
* Copyright (c) 2012 by GreenShore Network
* Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

*/ 

package com.iwgame.gm.p1.security.modules.query.client.ui.xdialog;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.security.common.shared.bean.AccountDocs;
import com.iwgame.gm.p1.security.modules.query.shared.rpc.SecurityRegisInfoQueryService;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.LabelField;

/** 
 * @简述: 账户资料详细信息窗口
 * @作者: 朱斌
 * @版本： 1.0
 * @邮箱：zhubin@iwgame.com
 * @修改日期 2012-12-21 上午11:22:56 
 */
public class AccountDetailsDialogBox extends XDialogBox {

	private String userName;
	private String productId;
	
	private LabelField userNameField;
	private LabelField userStatusField;
	private LabelField registerTimeField;
	private LabelField registerIpField;
	private LabelField registerPlaceField;
	private LabelField realName;
	private LabelField idCardField;
	private LabelField idCardStatusField;
	private LabelField registerEmailField;
	private LabelField maxLevelField;
	private LabelField totalPaidField;
	private LabelField password4Field;
	
	private FormLayout formLayout;
	private XFormPanel formPanel;
	
	public AccountDetailsDialogBox(String userName,String productId)
	{
		this.userName = userName;
		
		this.productId = productId;
		
		this.initComponentInfo();
	}
	
	private void initComponentInfo() {
		formLayout = new FormLayout();
		//formLayout.setColumn(2);
		formPanel = new XFormPanel();
		
		userNameField = new LabelField("账号");
		userNameField.setHiddenLabel(false);
		formLayout.add(userNameField);
		userStatusField = new LabelField("账号状态");
		userStatusField.setHiddenLabel(false);
		formLayout.add(userStatusField);
		
		registerTimeField = new LabelField("注册时间");
		registerTimeField.setHiddenLabel(false);
		formLayout.add(registerTimeField);
		registerIpField = new LabelField("注册IP");
		registerIpField.setHiddenLabel(false);
		formLayout.add(registerIpField);
		
		registerPlaceField = new LabelField("注册来源");
		registerPlaceField.setHiddenLabel(false);
		formLayout.add(registerPlaceField);
		realName = new LabelField("注册姓名");
		realName.setHiddenLabel(false);
		formLayout.add(realName);
		
		idCardField = new LabelField("身份证");
		idCardField.setHiddenLabel(false);
		formLayout.add(idCardField);
		idCardStatusField = new LabelField("身份证状态");
		idCardStatusField.setHiddenLabel(false);
		formLayout.add(idCardStatusField);
		
		registerEmailField = new LabelField("注册邮箱");
		registerEmailField.setHiddenLabel(false);
		formLayout.add(registerEmailField);
		maxLevelField = new LabelField("最高等级");
		maxLevelField.setHiddenLabel(false);
		formLayout.add(maxLevelField);
		
		totalPaidField = new LabelField("充值钻石");
		totalPaidField.setHiddenLabel(false);
		formLayout.add(totalPaidField);
		password4Field = new LabelField("四位密码");
		password4Field.setHiddenLabel(false);
		formLayout.add(password4Field);
		
		formPanel.setLayout(formLayout);
		setButtons(CANCEL);
		getButtonByItemId(CANCEL).setText("关闭");
		initBox("账户详细信息", formPanel);
	}

	@Override
	protected void onButtonPressed(Button button) {
		super.onButtonPressed(button);
		if (button==getButtonByItemId(CANCEL)) {
			hide();
		}
	}
	@Override
	protected void onAttach() {
		super.onAttach();
		SecurityRegisInfoQueryService.Util.get().getAccountDocsByUserName(userName,productId, new AsyncCallback<AccountDocs>() {
			
			@Override
			public void onSuccess(AccountDocs result) {
				if (result!=null) {
					userNameField.setValue(result.getUserName());
					String status = result.getAccountStatus();
					if("0".equals(status)){
						userStatusField.setValue("未激活");
					}else if("1".equals(status)){
						userStatusField.setValue("已激活");
					}
					
					registerTimeField.setValue(result.getRegisterTime());
					registerIpField.setValue(result.getRegisterIp());
					registerPlaceField.setValue(result.getRegisterSourcetype());
					realName.setValue(result.getRealName());
					idCardField.setValue(result.getIdcard());
					idCardStatusField.setValue(result.getIdcardStatus());
					registerEmailField.setValue(result.getUserEmail());
					maxLevelField.setValue(result.getMaxLevel().toString());
					totalPaidField.setValue(result.getTotalPaid().toString());
					password4Field.setValue(result.getPassword4());
				}else {
					userNameField.setValue("");
					userStatusField.setValue("");
					registerTimeField.setValue("");
					registerIpField.setValue("");
					registerPlaceField.setValue("");
					realName.setValue("");
					idCardField.setValue("");
					idCardStatusField.setValue("");
					registerEmailField.setValue("");
					maxLevelField.setValue("");
					totalPaidField.setValue("");
					password4Field.setValue("");
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
				MessageBox.error("加载账户相信信息出错:"+caught.getMessage());
			}
		});
	}
}
