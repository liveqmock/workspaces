/**      
 * OperationClientDialog.java Create on 2012-11-12 下午2:49:25    
 *      
 * Copyright (c) 2012 by GreenShore Network
 * Company: 上海绿岸网络科技有限公司(Shanghai GreenShore Network Technology Co.,Ltd.)

 */
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.ClientMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.ClientMgrView;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;

/**
 * @ClassName: 添加修改网吧客户端
 * @Description: TODO(...)
 * @author ByName's pfwang
 * @date 2012-11-12 下午2:49:25
 * @Version 1.0
 * 
 */
public class OperationClientDialog extends XDialogBox {

	private final ClientMgrPresenter presenter;
	private final ClientMgrView view;
	private final boolean type;

	private XFormPanel formPanel;
	private FormLayout layout;

	private PlainObjectSelector<DropDownListData> media;
	private TextField code;
	private TextField version;

	public OperationClientDialog(boolean tempType, ClientMgrView mgrview) {
		this.presenter = mgrview.getPresenter();
		this.view = mgrview;
		this.type = tempType;
		formPanel = new XFormPanel();
		formPanel.setWidth("450px");
		layout = new FormLayout();
		layout.setColumn(1);
		formPanel.setLayout(layout);

		media = new PlainObjectSelector<DropDownListData>("网吧媒体") {

			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};

		media.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

			@Override
			public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
				presenter.getMedia(2, callback);
			}
		});

		media.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (media.getValue().equals("-1")) {
					return "请选择媒体";
				}
				return null;
			}
		});
		media.setAllowBlank(false);
		media.setValidateOnBlur(true);
		layout.add(media);

		code = new TextField("游戏客户识别码");
		code.getWidget().setMaxLength(20);
		code.setAllowBlank(false);
		code.setValidateOnBlur(true);

		version = new TextField("游戏客户端版本");
		version.getWidget().setMaxLength(20);
		version.setAllowBlank(false);
		version.setValidateOnBlur(true);

		layout.add(version);
		layout.add(code);

		setButtons(OKCANCEL);
		String title = "";
		if (type) {
			title = "录入客户端识别码";
		} else {
			title = "修改客户端识别码";
			media.setValue(view.getGrid().getSelected().get(0).get("mediaId").toString());
			code.setValue(view.getGrid().getSelected().get(0).get("code").toString());
			version.setValue(view.getGrid().getSelected().get(0).get("version").toString());
		}
		initBox(title, formPanel);
	}

	@Override
	protected void onButtonPressed(final Button button) {
		super.onButtonPressed(button);
		if (button == getButtonByItemId(OK)) {
			if (formPanel.validate()) {
				String mid = media.getValue();
				String v = version.getValue();
				String c = code.getValue();
				if (type) { // 添加
					presenter.addClent(mid, v, c, new AsyncCallbackEx<Integer>() {

						@Override
						public void onSuccess(Integer result) {
							if (result != 1) {
								MessageBox.alert("添加失败");
							} else {
								view.getGrid().load();
								hide();
							}
						}
					});
				} else { // 修改
					Double did = view.getGrid().getSelected().get(0).get("id");
					int id = did.intValue();
					presenter.updateClent(mid, v, c, id, new AsyncCallbackEx<Integer>() {

						@Override
						public void onSuccess(Integer result) {
							if (result != 1) {
								MessageBox.alert("修改失败");
							} else {
								view.getGrid().load();
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
	}

}
