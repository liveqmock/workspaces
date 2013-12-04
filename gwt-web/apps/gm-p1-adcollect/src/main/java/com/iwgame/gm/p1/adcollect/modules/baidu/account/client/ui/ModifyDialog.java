/**      
 * ModifyDialog.java Create on 2012-10-11     
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */

package com.iwgame.gm.p1.adcollect.modules.baidu.account.client.ui;

import com.iwgame.gm.p1.adcollect.modules.baidu.account.shared.rpc.BaiduAccountService;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.MessageBoxEvent;
import com.iwgame.ui.panel.client.box.MessageBoxHandler;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.box.toptoolets.CloseTopToolet;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.PasswordField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: ModifyDialog
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-10-11 下午05:14:54
 * @Version 1.0
 * 
 */
public class ModifyDialog extends XDialogBox {

	private final XFormPanel userForm;
	private PasswordField fldRawPwd, fldNewPwd, fldConfirmPwd;
	private TextField fldUsername, fldToken;
	private SchemaGridView view;
	private int id;
	private String pwd;
	private String username;
	private String token;

	private BaseModelData data;

	/**
	 * 
	 */
	public ModifyDialog(final BaseModelData data, final SchemaGridView view) {
		this.data = data;
		id = data.getInt("id");
		pwd = data.get("password");
		username = data.get("username");
		token = data.get("token");
		this.view = view;
		userForm = new XFormPanel();
		final FormLayout layout = new FormLayout();
		layout.setAutoWidth(false);
		layout.setWidth(500);
		userForm.setLayout(layout);

		fldRawPwd = new PasswordField("原帐号密码", "rawpwd");
		fldRawPwd.setAllowBlank(false);
		fldRawPwd.setValidateOnBlur(true);
		layout.add(fldRawPwd);

		fldUsername = new TextField("帐号名称", "username");
		fldUsername.setAllowBlank(false);
		fldUsername.setValue(username);
		layout.add(fldUsername);

		fldToken = new TextField("校验码", "token");
		fldToken.setAllowBlank(false);
		fldToken.setValue(token);
		layout.add(fldToken);

		fldNewPwd = new PasswordField("新帐号密码", "newpwd");
		fldNewPwd.setValidateOnBlur(true);
		layout.add(fldNewPwd);

		fldConfirmPwd = new PasswordField("新帐号密码确认", "cnfpwd");
		fldConfirmPwd.setValidateOnBlur(true);
		fldConfirmPwd.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (fldNewPwd.getValue() == null) {
					return null;
				}
				if (fldConfirmPwd.getValue().equals(fldNewPwd.getValue())) {
					return null;
				} else {
					return "两次密码不一致！";
				}
			}
		});
		layout.add(fldConfirmPwd);

		initBox("修改百度帐号", userForm);
		setButtons(XDialogBox.OKCANCEL);
		addTopToolet(new CloseTopToolet(this));
	}

	@Override
	protected void onButtonPressed(final com.google.gwt.user.client.ui.Button button) {
		if (button == getButtonByItemId(XDialogBox.OK)) {
			if (userForm.validate()) {
				BaiduAccountService.Util.get().modifyPassword(id, fldUsername.getValue(), fldToken.getValue(),
						fldRawPwd.getValue(), pwd, fldNewPwd.getValue(),
						ApplicationContext.getCurrrentUser().getUsername(), new AsyncCallbackEx<String>() {

							@Override
							public void onSuccess(final String result) {
								if (result == null) {
									MessageBox.info("帐号修改成功", new MessageBoxHandler() {

										@Override
										public void onClose(final MessageBoxEvent event) {
											hide();
											view.reload();
										}
									});
								} else {
									MessageBox.error(result, new MessageBoxHandler() {

										@Override
										public void onClose(final MessageBoxEvent event) {
											fldRawPwd.setValue(null);
											fldNewPwd.setValue(null);
											fldConfirmPwd.setValue(null);
											fldUsername.setValue(username);
											fldToken.setValue(token);
										}
									});
								}

							}
						});
			}
		}
		if (button == getButtonByItemId(XDialogBox.CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
	};
}
