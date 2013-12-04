/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： AddAdGroupDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.GroupMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.GroupMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedEvent;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget.DropDownField;
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
import com.iwgame.xmvp.client.utils.AppUtils;

/**
 * 类说明
 * 
 * @简述： 添加广告组
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-8 上午09:12:37
 */
public class AddAdGroupDialog extends XDialogBox {

	private GroupMgrPresenter presenter;
	private GroupMgrListView view;

	private XFormPanel formPanel;
	private FormLayout layout;

	private final PlainObjectSelector<DropDownListData> mediaType;
	private final DropDownField media;
	private TextField name;

	public AddAdGroupDialog(final GroupMgrListView view) {
		this.view = view;
		presenter = view.getPresenter();
		formPanel = new XFormPanel();
		formPanel.setWidth("450px");
		layout = new FormLayout();
		layout.setColumn(1);
		formPanel.setLayout(layout);

		mediaType = new PlainObjectSelector<DropDownListData>("媒体分类") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		mediaType.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

			@Override
			public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
				presenter.getMediaType(1, callback);
			}
		});
		mediaType.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (mediaType.getValue().equals("-1")) {
					return "请选择媒体分类";
				}
				return null;
			}
		});
		mediaType.setAllowBlank(false);
		mediaType.setValidateOnBlur(true);
		layout.add(mediaType);
		mediaType.getWidget().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(final ChangeEvent event) {
				final int temptype = Integer.parseInt(mediaType.getValue());
				AppUtils.EVENT_BUS.fireEvent(new MediaSelectedEvent(temptype));
			}
		});
		media = new DropDownField("媒体") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		media.addItem("---请先选择媒体分类---", "-1");
		media.setEnablePlugin(true);
		media.addMedisSelectedHandlers(new MediaSelectedHandler() {

			@Override
			public void selectMedia(final MediaSelectedEvent event) {
				presenter.getMedia(event.getType(), new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						media.getWidget().clear();
						media.addItem("--请选择--", "-1");
						for (final DropDownListData ddld : result) {
							media.addItem(ddld.getName(), ddld.getId());
						}
					}
				});
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

		name = new TextField("广告组名");
		name.setMaxLength(50);
		name.setAllowBlank(false);
		name.setValidateOnBlur(true);
		name.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				// TODO (验证是否已经被占用)
				final String adgname = name.getValue();
				// 调用方法
				presenter.checkGroupName(adgname, new AsyncCallbackEx<Boolean>() {

					@Override
					public void onSuccess(final Boolean result) {
						if (!result) {
							MessageBox.alert("此名称已经存在请换一个");
							name.setValue("");
							return;
						}
					}
				});
			}
		});
		layout.add(name);

		setButtons(OKCANCEL);
		initBox("添加广告组", formPanel);
	}

	@Override
	protected void onButtonPressed(final Button button) {
		super.onButtonPressed(button);
		if (button == getButtonByItemId(OK)) {
			if (formPanel.validate()) {
				if (media.getValue().equals("-1")) {
					MessageBox.alert("请选择媒体");
					return;
				}

				presenter.addGroup(name.getValue(), media.getValue(),
						new AsyncCallbackEx<Integer>() {

							@Override
							public void onSuccess(final Integer result) {
								if (result == 1) {
									hide();
									view.getPanel().load();
								} else {
									MessageBox.alert("添加广告组失败");
								}
							}
						});
			}
		}
		if (button == getButtonByItemId(CANCEL)) {
			hide();
		}
	}

}
