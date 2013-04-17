/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： OperationPayableDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.PayableMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.PayableMgrListView;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.SchemaGrid;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * 类说明
 * 
 * @简述： 添加修改合同抬头
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-10 下午12:17:02
 */
public class OperationPayableDialog extends XDialogBox {

	private final PayableMgrPresenter presenter;
	private final SchemaGrid<BaseModelData> gird;
	private final boolean type;

	private final XFormPanel formpanel;
	private final FormLayout layout;

	private final TextField title;

	public OperationPayableDialog(final boolean type, final PayableMgrListView view) {
		presenter = view.getPresenter();
		gird = view.getGrid();
		this.type = type;
		formpanel = new XFormPanel();
		formpanel.setWidth("600px");
		layout = new FormLayout();
		formpanel.setLayout(layout);

		title = new TextField("合同抬头");
		title.setMaxLength(50);
		title.setAllowBlank(false);
		title.setValidateOnBlur(true);
		layout.add(title);

		if (type) {
			initBox("添加合同抬头", formpanel);
		} else {
			initBox("修改合同抬头", formpanel);
			title.setValue(gird.getSelected().get(0).get("title").toString());
		}
		setButtons(OKCANCEL);
	}

	@Override
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(OK)) {
			// 调用添加方法
			if (formpanel.validate()) {
				if (type) {
					presenter.addPayable(title.getValue(), new AsyncCallbackEx<Integer>() {

						@Override
						public void onSuccess(final Integer result) {
							if (result != 1) {
								MessageBox.alert("添加失败");
							} else {
								gird.load();
								hide();
							}
						}
					});
				} else {
					final Double d = gird.getSelected().get(0).get("id");
					final int id = d.intValue();
					presenter.updatePayable(id, title.getValue(), new AsyncCallbackEx<Integer>() {

						@Override
						public void onSuccess(final Integer result) {
							if (result != 1) {
								MessageBox.alert("修改失败");
							} else {
								gird.load();
								hide();
							}
						}
					});
				}
			}
		}
		if (button == getButtonByItemId(CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
	}
}
