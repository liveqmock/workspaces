/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： OperDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.landing.client.ui.dialog;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.LandingView;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.events.MaterialSelectedEvent;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.events.MaterialSelectedHandler;
import com.iwgame.gm.p1.adcollect.modules.landing.client.ui.widget.MaterialField;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.SchemaGrid;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Direction;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FieldHeaderPlugin;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.RadioButtonGroup;
import com.iwgame.ui.panel.client.form.field.TextAreaField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述： 操作到达页
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-21 下午04:58:30
 */
public class OperDialog extends XDialogBox {
	private Map<String, Object> oldBase;

	private XFormPanel formPanel;
	private FormLayout layout;

	private TextField name;
	private TextField link;
	private RadioButtonGroup status;
	private final MaterialField material;
	private TextAreaField explain;
	private HiddenField productIdField;
	private HiddenField operField;
	private HiddenField materialIdField;

	private boolean type;
	private final SchemaGrid<BaseModelData> gird;
	private final LandingView view;
	private int landingId;

	/**
	 * 
	 * @param type
	 *            true 添加 false 修改
	 */
	public OperDialog(final boolean type, final LandingView landingView) {
		this.type = type;
		gird = landingView.getGrid();
		view = landingView;
		formPanel = new XFormPanel();
		formPanel.setWidth("450px");
		layout = new FormLayout();
		layout.setColumn(1);
		formPanel.setLayout(layout);

		name = new TextField("名称", "name");
		name.setAllowBlank(false);
		name.setValidateOnBlur(true);
		layout.add(name);

		link = new TextField("链接", "link");
		link.setMaxLength(100);
		link.setAllowBlank(false);
		link.setValidateOnBlur(true);
		layout.add(link);

		materialIdField = new HiddenField("hiddenField_productId", "materialId");
		layout.add(materialIdField);

		material = new MaterialField("素材", "material");
		material.setAllowBlank(false);
		material.setValidateOnBlur(true);
		material.getWidget().setReadOnly(true);
		material.setEnablePlugin(true);
		material.setHeaderPlugin(new FieldHeaderPlugin() {

			@Override
			public Widget getHeader(final Field<?, ?> field) {
				final Button btn = new Button("选择素材");
				btn.setWidth("80px");
				field.setFieldName("selMaterial");
				btn.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						new SelectMaterialDialog().center();
					}
				});

				return btn;
			}
		});
		material.addMaterialSelectedHandlers(new MaterialSelectedHandler() {

			@Override
			public void selectMaterial(MaterialSelectedEvent event) {
				material.setValue(event.getName());
				materialIdField.setValue(event.getId());
			}
		});
		layout.add(material);

		status = new RadioButtonGroup("状态", "status", Direction.Horizontal);
		status.addRadioButton("使用", "1", true);
		status.addRadioButton("暂停", "0", false);
		status.setAllowBlank(false);
		status.setValidateOnBlur(true);
		layout.add(status);

		explain = new TextAreaField("说明", "explain");
		explain.getWidget().setHeight("60px");
		layout.add(explain);

		productIdField = new HiddenField("hiddenField_productId", "productId");
		productIdField.setValue(ApplicationContext.getCurrentProductId());
		layout.add(productIdField);

		operField = new HiddenField("hiddenField_productId", "oper");
		operField.setValue(type + "");
		layout.add(operField);

		String id = "";
		if (!type) {
			final Double d = gird.getSelected().get(0).get("id");
			setUpdateValue(gird);
			id = d.intValue() + "";
			landingId = d.intValue();
		}

		initBox(type ? "添加到达页" : "修改id为：" + id + "的到达页", formPanel);
		setButtons(OKCANCEL);
	}

	@Override
	protected void onButtonPressed(Button button) {
		if (button == getButtonByItemId(OK)) {
			// 执行 添加 或者 修改操作
			if (formPanel.validate()) {
				final Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("name", name.getValue());
				parameter.put("url", link.getValue());
				parameter.put("materialId", materialIdField.getValue());
				parameter.put("note", explain.getValue());
				parameter.put("status", Integer.parseInt(status.getValue()));
				if (type) {
					view.getPresenter().addLanding(parameter, new AsyncCallbackEx<Integer>() {

						@Override
						public void onSuccess(Integer result) {
							if (result != 1) {
								MessageBox.alert("添加失败");
							} else {
								gird.load();
								hide();
							}
						}
					});
				} else {
					parameter.put("id", landingId);
					view.getPresenter().updateLanding(parameter, oldBase, new AsyncCallbackEx<Integer>() {

						@Override
						public void onSuccess(Integer result) {
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

	// 修改加載參數
	private void setUpdateValue(final SchemaGrid<BaseModelData> gird) {
		oldBase = new HashMap<String, Object>();
		final String oldname = gird.getSelected().get(0).get("name").toString();
		if ((null != oldname) && (oldname.length() > 0)) {
			name.setValue(oldname);
			oldBase.put("name", name.getValue());
		}
		final String oldlink = gird.getSelected().get(0).get("link").toString();
		if ((null != oldlink) && (oldlink.length() > 0)) {
			link.setValue(oldlink);
			oldBase.put("url", link.getValue());
		}
		final Double d = gird.getSelected().get(0).get("status");
		if (null != d) {
			status.setValue((d.intValue() + "").trim());
			oldBase.put("status", Integer.parseInt(status.getValue()));
		}
		final String oldnote = gird.getSelected().get(0).get("note").toString();
		if ((null != oldnote) && (oldnote.length() > 0)) {
			explain.setValue(oldnote);
			oldBase.put("note", explain.getValue());
		} else {
			explain.setValue(null);
			oldBase.put("note", null);
		}
		final String oldurl = gird.getSelected().get(0).get("mname").toString();
		if ((null != oldurl) && (oldurl.length() > 0)) {
			material.setValue(oldurl);
		}
		final String materialId = gird.getSelected().get(0).get("materialId").toString();
		if ((null != materialId) && (materialId.length() > 0)) {
			materialIdField.setValue(materialId);
			oldBase.put("materialId", materialIdField.getValue());
		}
		if (!type) {
			oldBase.put("id", gird.getSelected().get(0).get("id").toString());
		}
	}
}
