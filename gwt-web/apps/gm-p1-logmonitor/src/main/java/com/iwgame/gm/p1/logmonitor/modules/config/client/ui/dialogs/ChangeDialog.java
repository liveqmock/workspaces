/**
 * ChangeDialog.java Create on 2012-1-12
 * 
 * Copyright (c) 2012 by GreenShore Network Company: 上海绿岸网络科技有限公司(Shanghai
 * GreenShore Network Technology Co.,Ltd.)
 * 
 */

package com.iwgame.gm.p1.logmonitor.modules.config.client.ui.dialogs;

import com.iwgame.gm.p1.logmonitor.modules.config.client.presenter.WarningPresenter;
import com.iwgame.gm.p1.logmonitor.modules.config.shared.model.WarningConfigModelBean;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.view.SchemaGridView;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.MessageBoxEvent;
import com.iwgame.ui.panel.client.box.MessageBoxHandler;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * @ClassName: ChangeDialog
 * @Description: TODO(...)
 * @author 吴江晖
 * @date 2012-1-12 下午12:20:36
 * @Version 1.0
 * 
 */
public class ChangeDialog extends XDialogBox {

	private final XFormPanel userForm;

	private final SchemaGridView view;

	private final NumberField fldMaxCount;

	private final RadioButtonGroup fldAccountStatus;

	private final HiddenField fldUsernames;
	private final HiddenField fldId;

	/**
     * 
     */
	public ChangeDialog(final SchemaGridView view) {
		super();
		this.view = view;
		userForm = new XFormPanel();
		FormLayout layout = new FormLayout();
		layout.setWidth(300);
		userForm.setLayout(layout);

		BaseModelData data = view.getGrid().getSelected().get(0);

		fldMaxCount = new NumberField("报警阀值(按小时)", "maxCount");
		fldMaxCount.setValue(data.<Double> get("maxCount").intValue());
		layout.add(fldMaxCount);

		int status = data.<Double> get("status").intValue();
		fldAccountStatus = new RadioButtonGroup("策略状态", "status", Direction.Horizontal);
		fldAccountStatus.addRadioButton("启用", "0", status == 0);
		fldAccountStatus.addRadioButton("停用", "1", status == 1);
		layout.add(fldAccountStatus);

		int id = data.<Double> get("id").intValue();
		fldId = new HiddenField("usernames", Integer.toString(id));
		fldId.setValue(Integer.toString(id));
		layout.add(fldId);

		fldUsernames = new HiddenField("usernames", ApplicationContext.getCurrrentUser().getUsername());
		fldUsernames.setValue(ApplicationContext.getCurrrentUser().getUsername());
		layout.add(fldUsernames);

		initBox("修改预警配置", userForm);
		setButtons(XDialogBox.OKCANCEL);
	}

	@Override
	protected void onButtonPressed(final com.google.gwt.user.client.ui.Button button) {
		if (button == getButtonByItemId(XDialogBox.OK)) {
			if (userForm.validate()) {
				WarningConfigModelBean bean = new WarningConfigModelBean();
				bean.setId(Integer.valueOf(fldId.getValue()));
				bean.setMaxCount(fldMaxCount.getValue().intValue());
				bean.setStatus(Integer.valueOf(fldAccountStatus.getValue()));
				bean.setLastUpdateuser(fldUsernames.getValue());
				((WarningPresenter) view.getGrid().getDelegate()).updateWarningConfig(bean,
						new AsyncCallbackEx<Void>() {

							@Override
							public void onSuccess(final Void result) {
								MessageBox.info("成功修改", new MessageBoxHandler() {

									@Override
									public void onClose(final MessageBoxEvent event) {
										view.reload();
										hide();
									}
								});
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
