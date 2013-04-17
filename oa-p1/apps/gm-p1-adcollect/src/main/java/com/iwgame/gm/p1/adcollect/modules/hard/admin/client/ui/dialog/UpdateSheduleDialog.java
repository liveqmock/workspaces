/****************************************************************
w *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： UpdateSheduleDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import java.util.List;

import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.SheduleMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.SheduleMgrListView;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.gm.p1.adcollect.shared.model.SheduleDataBase;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestField;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestField.AsyncValidator;
import com.iwgame.ui.panel.client.form.field.AsyncSuggestOracle;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.TextAreaField;
import com.iwgame.ui.panel.client.form.field.TextField;

/**
 * 类说明
 * 
 * @简述： 修改广告排期Dialog
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-17 上午09:21:51
 */
public class UpdateSheduleDialog extends XDialogBox {

	// private final XMVPLogger logger = new
	// XMVPLogger(UpdateSheduleDialog.class);

	private final SheduleMgrListView view;
	private XFormPanel formpanel;
	private FormLayout layout;
	private SheduleMgrPresenter presenter;

	private final SheduleDataBase oldDateBase = new SheduleDataBase();

	private TextField name;
	private AsyncSuggestField<DropDownListData> materialId;
	private AsyncSuggestField<DropDownListData> groupId;
	private AsyncSuggestField<DropDownListData> url;
	private TextAreaField source;

	public UpdateSheduleDialog(final SheduleMgrListView view) {
		this.view = view;
		presenter = view.getPresenter();
		formpanel = new XFormPanel();
		formpanel.setWidth("600px");
		layout = new FormLayout();
		layout.setColumn(1);
		formpanel.setLayout(layout);

		name = new TextField("排期名称");
		name.setAllowBlank(false);
		name.setValidateOnBlur(true);
		name.setMaxLength(25);
		layout.add(name);

		materialId = new AsyncSuggestField<DropDownListData>("素材ID", new AsyncSuggestOracle<DropDownListData>() {

			@Override
			public void getCandidatesFromQuery(final String query, final AsyncCallback<List<DropDownListData>> callback) {
				presenter.autoMaterialId(query, callback);
			}

			@Override
			public String buildDisplayString(final DropDownListData candidate) {
				return "<span><strong>ID：</strong>" + candidate.getId() + "<span> " + "<span><strong>名称：</strong>"
						+ candidate.getName() + "<span> ";
			}

			@Override
			public String buildReplaceString(final DropDownListData candidate) {
				return candidate.getId();
			}
		});
		materialId.setValidateOnBlur(true);
		layout.add(materialId);
		materialId.setAsyncValidator(new AsyncValidator() {
			@Override
			public void validate(final String value, final AsyncCallbackEx<String> callback) {
				if ("".equals(value) && materialId.isAllowBlank()) {
					materialId.setHasError(false);
					return;
				}
				presenter.checkMaterialId(materialId.getValue(), new AsyncCallbackEx<String>() {
					@Override
					public void onSuccess(final String result) {
						callback.onSuccess(result);
					}
				});
			}
		});

		groupId = new AsyncSuggestField<DropDownListData>("广告组ID", new AsyncSuggestOracle<DropDownListData>() {

			@Override
			public void getCandidatesFromQuery(final String query, final AsyncCallback<List<DropDownListData>> callback) {
				presenter.autoGroupId(query, callback);
			}

			@Override
			public String buildDisplayString(final DropDownListData candidate) {
				return "<span><strong>ID：</strong>" + candidate.getId() + "<span> " + "<span><strong>名称：</strong>"
						+ candidate.getName() + "<span> ";
			}

			@Override
			public String buildReplaceString(final DropDownListData candidate) {
				return candidate.getId();
			}
		});
		groupId.setValidateOnBlur(true);
		groupId.setAsyncValidator(new AsyncValidator() {

			@Override
			public void validate(final String value, final AsyncCallbackEx<String> callback) {
				if ("".equals(value) && groupId.isAllowBlank()) {
					groupId.setHasError(false);
					return;
				}
				presenter.checkGroupId(groupId.getValue(), new AsyncCallbackEx<String>() {

					@Override
					public void onSuccess(final String result) {
						callback.onSuccess(result);
					}
				});
			}
		});
		layout.add(groupId);

		url = new AsyncSuggestField<DropDownListData>("广告链接", new AsyncSuggestOracle<DropDownListData>() {

			@Override
			public void getCandidatesFromQuery(final String query, final AsyncCallback<List<DropDownListData>> callback) {
				presenter.autoAdUrl(query, callback);
			}

			@Override
			public String buildDisplayString(final DropDownListData candidate) {
				return "<span><strong>名称：</strong>" + candidate.getName() + "<span><br/> "
						+ "<span><strong>URL：</strong>" + candidate.getGenerate() + "<span> ";
			}

			@Override
			public String buildReplaceString(final DropDownListData candidate) {
				return candidate.getGenerate();
			}
		});
		url.setAllowBlank(false);
		url.setValidateOnBlur(true);
		url.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				String v = url.getValue();
				if (!url.isAllowBlank() && ("http://".equals(v) || "".equals(v))) {
					return "广告链接不能为空";
				}
				if (v.length()>90) {
				    return "你输入的太长了";
				}
				RegExp regexp = RegExp.compile("^((https|http)?://)+[A-Za-z0-9]+\\.[A-Za-z0-9]+[/=\\?%\\-&_~`@[\\]\\':+!]*([^<>\"\"])*$");
//				RegExp regexp = RegExp.compile("^((?:(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d)\\.){3}(?:25[0-5]|2[0-4]\\d|[01]?\\d?\\d))$");
        			if (regexp.exec(v) == null) {
        				return "格式不正确";
        			}
				return null;
			}
		});
		layout.add(url);

		url.setAsyncValidator(new AsyncValidator() {

			@Override
			public void validate(final String value, final AsyncCallbackEx<String> callback) {
				presenter.checkAdUrl(url.getValue(), new AsyncCallbackEx<String>() {

					@Override
					public void onSuccess(final String result) {
						callback.onSuccess(null);
					}
				});
			}
		});

		source = new TextAreaField("配送来源");
		source.setMaxLength(50);
		source.getWidget().setHeight("60px");
		layout.add(source);

		String materialIdStr = view.getGrid().getSelected().get(0).get("materialId").toString();
		Double groupIdd = view.getGrid().getSelected().get(0).get("adGroup");
		String urlStr = view.getGrid().getSelected().get(0).get("adUrl").toString();
		String sourceStr = view.getGrid().getSelected().get(0).get("source").toString();

		oldDateBase.setMaterialId(materialIdStr);
		if (null != groupIdd && groupIdd.intValue()!=0) {
			oldDateBase.setAdGroup(groupIdd.intValue());
		}
		oldDateBase.setAdUrl(urlStr);
		String nameStr = view.getGrid().getSelected().get(0).get("name").toString();
		name.setValue(nameStr);
		oldDateBase.setName(nameStr);
//		name.getWidget().setEnabled(false);
		materialId.setValue(materialIdStr);
		if (groupIdd != null && groupIdd.intValue()!=0) {
			groupId.setValue(groupIdd.intValue() + "");
		}
		url.setValue(urlStr);
		String usedType = view.getGrid().getSelected().get(0).get("usedType").toString();
		if (usedType.equals("2")) {
			source.setValue(sourceStr);
			oldDateBase.setSource(sourceStr);
		} else {
			source.setVisible(false);
		}
		initBox("修改广告排期", formpanel);
		setButtons(OKCANCEL);
	}

	@Override
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(OK)) {
			// 调用添加方法
			if (formpanel.validate()) {
				SheduleDataBase newDateBase = new SheduleDataBase();
				newDateBase.setId(view.getGrid().getSelected().get(0).get("id").toString());
				if (!materialId.getValue().equals("")) {
					newDateBase.setMaterialId(materialId.getValue());
				}
				if (!groupId.getValue().equals("")) {
					newDateBase.setAdGroup(Integer.parseInt(groupId.getValue()));
				}
				if (!name.getValue().equals("")) {
					newDateBase.setName(name.getValue());
				}
				
				newDateBase.setAdUrl(url.getValue());
				newDateBase.setSource(source.getValue());
				newDateBase.setAdId(view.getGrid().getSelected().get(0).get("adId").toString());
				presenter.updateShedule(newDateBase, oldDateBase, new AsyncCallbackEx<Integer>() {

					@Override
					public void onSuccess(final Integer result) {
						if (result == 1) {
							view.getGrid().load();
							hide();
						} else {
							MessageBox.alert("修改广告排期失败");
						}
					}
				});
			}
		}
		if (button == getButtonByItemId(CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
	}
}
