/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： MaterialAddDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.res.MediaType;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.MaterialMgrView;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FileUploadField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextAreaField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 添加硬广素材
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-25 上午11:56:11
 */
public class MaterialAddDialog extends XDialogBox {

	private final MaterialMgrView view;
	private final XUploadFormPanel formpanel;
	private final FormLayout layout;

	private PlainObjectSelector<DropDownListData> type = null;
	private TextField key;
	private FileUploadField uploadMaterial;
	// private NumberField width;
	// private NumberField height;
	private TextAreaField note;
	private HiddenField productIdField;
	private HiddenField generateField;

	private List<DropDownListData> typelist;

	private static Map<String, String[]> types;

	static {
		types = new HashMap<String, String[]>();
		final MediaType mediaType = GWT.create(MediaType.class);
		final JSONValue json = JSONParser.parseStrict(mediaType.types());
		final JSONArray array = json.isArray();
		for (int i = 0; i < array.size(); i++) {
			final JSONArray object = array.get(i).isArray();
			final String[] tmp = new String[object.size()];
			for (int index = 0; index < object.size(); index++) {
				tmp[index] = object.get(index).isString().stringValue();
			}
			types.put(Integer.toString(i), tmp);
		}
	}

	public MaterialAddDialog(final MaterialMgrView viewMgrView) {
		view = viewMgrView;

		formpanel = new XUploadFormPanel("素材属性", "materialuploadprocessor");
		formpanel.setWidth("450px");
		layout = new FormLayout();
		layout.setColumn(2);
		formpanel.setLayout(layout);

		type = new PlainObjectSelector<DropDownListData>("素材类型", "type") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		type.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

			@Override
			public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
				view.getPresenter().getMaterialType(3,
						new AsyncCallbackEx<List<DropDownListData>>() {

							@Override
							public void onSuccess(final List<DropDownListData> result) {
								typelist = result;
								callback.onSuccess(result);
							}
						});
			}
		});
		type.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				final int index = type.getWidget().getSelectedIndex();
				uploadMaterial.setAccepter(types.get(typelist.get(index - 1).getGenerate()));

			}
		});
		type.getWidget().setName("type");
		type.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (type.getValue().equals("-1")) {
					return "请选择素材类型";
				}
				return null;
			}
		});
		type.setAllowBlank(false);
		type.setValidateOnBlur(true);
		type.setColSpan(2);
		layout.add(type);

		key = new TextField("素材名称", "name");
		key.setMaxLength(25);
		key.setAllowBlank(false);
		key.setValidateOnBlur(true);
		key.setColSpan(2);
		layout.add(key);

		uploadMaterial = new FileUploadField("上传素材", "uploadMaterial");
		uploadMaterial.setAccepter("jpg", "swf", "png", "gif", "html", "fla", "psd");
		uploadMaterial.setAllowBlank(false);
		uploadMaterial.setValidateOnBlur(true);
		uploadMaterial.setColSpan(2);
		layout.add(uploadMaterial);

		// width = new NumberField("宽度", "width");
		// layout.add(1, width);
		//
		// height = new NumberField("高度", "height");
		// layout.add(2, height);

		note = new TextAreaField("素材说明", "note");
		note.setMaxLength(25);
		note.getWidget().setHeight("80px");
		note.setColSpan(2);
		layout.add(note);

		productIdField = new HiddenField("hiddenField_productId", "productId");
		productIdField.setValue(ApplicationContext.getCurrentProductId());
		layout.add(productIdField);

		generateField = new HiddenField("generate", "generate");
		layout.add(generateField);

		formpanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(final SubmitCompleteEvent event) {
				hide();
				view.getGrid().load();
			}
		});

		initBox("添加素材", formpanel);
		setButtons(XDialogBox.OKCANCEL);
	}

	@Override
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(XDialogBox.OK)) {
			// 调用添加方法
			if (formpanel.validate()) {
				if (type.getValue().equals("-1")) {
					MessageBox.alert("请选择类型");
					return;
				}

				final int index = type.getWidget().getSelectedIndex();
				generateField.setValue(typelist.get(index - 1).getGenerate());
				formpanel.submit();

			}
		}
		if (button == getButtonByItemId(XDialogBox.CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
	}
}
