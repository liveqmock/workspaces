/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： OperationContractDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import java.util.Date;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Float;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.ContractMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.ContractMgrListView;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedEvent;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.events.MediaSelectedHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.widget.DropDownField;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.SchemaGrid;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XUploadFormPanel;
import com.iwgame.ui.panel.client.form.field.DateField;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.FieldPlugin;
import com.iwgame.ui.panel.client.form.field.FileUploadField;
import com.iwgame.ui.panel.client.form.field.HiddenField;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.SimpleSelector;
import com.iwgame.ui.panel.client.form.field.TextAreaField;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.xmvp.client.utils.AppUtils;
import com.iwgame.xmvp.shared.data.BaseModelData;
import com.iwgame.xportal.common.client.ApplicationContext;

/**
 * 类说明
 * 
 * @简述：添加 修改合同
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-11 下午03:21:24
 */
public class OperationContractDialog extends XDialogBox {

	private final ContractMgrPresenter presenter;
	private final SchemaGrid<BaseModelData> gird;
	private final boolean type;

	private final XUploadFormPanel formpanel;
	private final FormLayout layout;

	private TextField itemno;
	private TextField name;
	private DateField signed;
	private TextField applyman;
	private TextField section;
	private SimpleSelector level;
	private PlainObjectSelector<DropDownListData> mediaType;
	private DropDownField media;
	private NumberField total;
	private CheckBox isatt;
	private FileUploadField adjunct;// 附件
	private TextAreaField content;
	private TextAreaField description;
	private TextAreaField note;
	private TextAreaField adsituation;
	private NumberField discount;
	private DateField payed;
	private NumberField stdRegcost;
	private NumberField stdLogincost;
	private NumberField stdDiscount;
	private NumberField adamt;
	private PlainObjectSelector<DropDownListData> titleId;
	private PlainObjectSelector<DropDownListData> logoId;

	private SimpleSelector typeField;

	private HiddenField productIdField;
	private HiddenField addOperationField;
	private HiddenField idField;

	public OperationContractDialog(final boolean type, final ContractMgrListView view) {
		presenter = view.getPresenter();
		gird = view.getGrid();
		this.type = type;
		formpanel = new XUploadFormPanel(null, "contractloadprocessor");
		formpanel.setWidth("900px");
		layout = new FormLayout();
		layout.setColumn(3);
		formpanel.setLayout(layout);

		typeField = new SimpleSelector("合同类型", "type") {

			@Override
			public void initItems() {
				addItem("---请选择---", "-1");
				addItem("采购", "采购");
				addItem("代理", "代理");
				addItem("直走", "直走");
			}
		};
		typeField.setColSpan(3);
		typeField.setFieldName("type");
		typeField.addValidator(new Validator() {

			@Override
			public String validate(Field<?, ?> field) {
				if (typeField.getValue().equals("-1")) {
					return "请选择合同类型";
				} else {
					return null;
				}
			}
		});
		layout.add(typeField);

		itemno = new TextField("合同编号", "itemno");
		itemno.setMaxLength(50);
		itemno.setAllowBlank(false);
		itemno.setValidateOnBlur(true);
		layout.add(1, itemno);

		itemno.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {

				presenter.checkItemno(itemno.getValue(), new AsyncCallbackEx<Boolean>() {

					@Override
					public void onSuccess(final Boolean result) {
						if (!result) {
							itemno.setValue("");
							MessageBox.alert("此合同编号已经存在请换一个");
						}
					}
				});
			}
		});

		name = new TextField("合同名称", "name");
		name.setMaxLength(100);
		name.setAllowBlank(false);
		name.setValidateOnBlur(true);
		layout.add(2, name);

		signed = new DateField("合同日期", "signed");
		signed.setValue(new Date());
		signed.setAllowBlank(false);
		signed.setValidateOnBlur(true);
		layout.add(3, signed);

		applyman = new TextField("申请人", "applyman");
		applyman.setMaxLength(20);
		applyman.setAllowBlank(false);
		applyman.setValidateOnBlur(true);
		layout.add(1, applyman);

		section = new TextField("所属部门", "section");
		section.setMaxLength(100);
		layout.add(2, section);

		level = new SimpleSelector("合同等级", "level") {

			@Override
			public void initItems() {
				addItem("一般", "一般");
				addItem("紧急", "紧急");
				addItem("特急", "特急");
			}
		};
		level.setFieldName("level");
		layout.add(3, level);

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
		layout.add(1, mediaType);
		mediaType.getWidget().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(final ChangeEvent event) {
				final int temptype = Integer.parseInt(mediaType.getValue());
				AppUtils.EVENT_BUS.fireEvent(new MediaSelectedEvent(temptype));
			}
		});
		media = new DropDownField("媒体", "mediaId") {
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
		media.setFieldName("mediaId");
		media.setEnablePlugin(true);
		media.addMedisSelectedHandlers(new MediaSelectedHandler() {

			@Override
			public void selectMedia(final MediaSelectedEvent event) {
				presenter.getMedia(event.getType(), new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						media.getWidget().clear();
						media.addItem("--请选择--", "-1");
						media.addItems(result);
					}
				});
			}
		});
		media.setAllowBlank(false);
		media.setValidateOnBlur(true);
		layout.add(2, media);

		// 抬头
		titleId = new PlainObjectSelector<DropDownListData>("合同抬头", "titleId") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		titleId.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

			@Override
			public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
				presenter.getPayableList(callback);
			}
		});
		titleId.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (titleId.getValue().equals("-1")) {
					return "请选择合同抬头";
				}
				return null;
			}
		});
		titleId.setAllowBlank(false);
		titleId.setValidateOnBlur(true);
		layout.add(3, titleId);

		isatt = new CheckBox("有附件");
		isatt.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(final ClickEvent event) {
				adjunct.getWidget().setEnabled(isatt.getValue());
			}
		});

		adjunct = new FileUploadField("合同附件", "adjunct");
		adjunct.setAccepter("doc", "xls", "jpg");
		adjunct.getWidget().setEnabled(false);
		adjunct.setColSpan(2);
		adjunct.getWidget().setWidth("150px");
		adjunct.setEnablePlugin(true);
		Element span = DOM.createSpan();
		span.setInnerText("(格式 doc xls jpg)");
		span.getStyle().setFloat(Float.RIGHT);
		adjunct.getWidget().getElement().getParentElement().appendChild(span);

		adjunct.setPlugin(new FieldPlugin<CheckBox>() {

			@Override
			public CheckBox getWidget(final Field<?, ?> field) {
				return isatt;
			}
		});
		layout.add(adjunct);

		// Logos
		logoId = new PlainObjectSelector<DropDownListData>("合同 LOGO", "logoId") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		logoId.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

			@Override
			public void loadItem(final AsyncCallback<List<DropDownListData>> callback) {
				presenter.getlogoList(callback);
			}
		});
		logoId.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (logoId.getValue().equals("-1")) {
					return "请选择合同 LOGO";
				}
				return null;
			}
		});
		logoId.setAllowBlank(false);
		logoId.setValidateOnBlur(true);
		layout.add(3, logoId);

		content = new TextAreaField("合同主旨", "content");
		content.getWidget().setHeight("60px");
		content.setColSpan(3);
		layout.add(content);

		description = new TextAreaField("合作概述", "description");
		description.getWidget().setHeight("60px");
		description.setColSpan(3);
		layout.add(description);

		note = new TextAreaField("说明", "note");
		note.setAllowBlank(false);
		note.setValidateOnBlur(true);
		note.getWidget().setHeight("60px");
		note.setColSpan(3);
		layout.add(note);

		adsituation = new TextAreaField("上次投放情况", "adsituation");
		adsituation.setAllowBlank(false);
		adsituation.setValidateOnBlur(true);
		adsituation.getWidget().setHeight("60px");
		adsituation.setColSpan(3);
		layout.add(adsituation);

		total = new NumberField("合同金额", "total");
		total.getWidget().setMaxLength(8);
		total.setDecimal(isModal());
		total.setPrecision(2);
		total.setAllowBlank(false);
		total.setValidateOnBlur(true);
		layout.add(1, total);

		discount = new NumberField("折扣", "discount");
		discount.getWidget().setMaxLength(3);
		discount.setDecimal(isModal());
		discount.setPrecision(2);
		discount.setAllowBlank(false);
		discount.setValidateOnBlur(true);
		layout.add(2, discount);

		payed = new DateField("付款日期", "payed");
		layout.add(3, payed);

		stdRegcost = new NumberField("注册成本", "stdRegcost");
		stdRegcost.getWidget().setMaxLength(8);
		stdRegcost.setDecimal(isModal());
		stdRegcost.setPrecision(2);
		stdRegcost.setAllowBlank(false);
		stdRegcost.setValidateOnBlur(true);
		layout.add(1, stdRegcost);

		stdLogincost = new NumberField("登陆成本", "stdLogincost");
		stdLogincost.getWidget().setMaxLength(8);
		stdLogincost.setDecimal(isModal());
		stdLogincost.setPrecision(2);
		stdLogincost.setAllowBlank(false);
		stdLogincost.setValidateOnBlur(true);
		layout.add(2, stdLogincost);

		stdDiscount = new NumberField("折扣标准", "stdDiscount");
		stdDiscount.getWidget().setMaxLength(3);
		stdDiscount.setDecimal(isModal());
		stdDiscount.setPrecision(2);
		layout.add(3, stdDiscount);

		adamt = new NumberField("广告数", "adamt");
		adamt.getWidget().setMaxLength(4);
		adamt.setAllowBlank(false);
		adamt.setValidateOnBlur(true);
		layout.add(1, adamt);

		// ////////////////////////////////////////////////
		productIdField = new HiddenField("productId", "productId");
		productIdField.setValue(ApplicationContext.getCurrentProductId());
		layout.add(productIdField);

		addOperationField = new HiddenField("operation", "operation");
		addOperationField.setValue(type ? "add" : "update");
		layout.add(addOperationField);

		idField = new HiddenField("id", null);
		layout.add(idField);

		if (type) {
			initBox("添加合同", formpanel);
		} else {
			final Integer tempid = Integer.parseInt(getSelectedValueByKey("mediaId").toString().substring(0, 2));
			mediaType.setValue(tempid + "");
			initBox("修改合同", formpanel);
			AppUtils.EVENT_BUS.fireEvent(new MediaSelectedEvent(tempid));
			itemno.setValue(getSelectedValueByKey("itemno").toString());
			itemno.getWidget().setEnabled(type);
			name.setValue(getSelectedValueByKey("name").toString());
			final String signedString = getSelectedValueByKey("signed").toString();
			if (signedString.length() > 0)
				signed.setValue(DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(signedString));
			final String payedString = getSelectedValueByKey("payed").toString();
			if (payedString.length() > 0)
				payed.setValue(DateTimeFormat.getFormat("yyyy-MM-dd HH:mm:ss").parse(payedString));

			applyman.setValue(getSelectedValueByKey("applyman").toString());
			section.setValue(getSelectedValueByKey("section").toString());
			level.setValue(getSelectedValueByKey("level").toString());
			if (getSelectedValueByKey("isatt").toString().equals("0")) {
				isatt.setValue(true);
				adjunct.getWidget().setEnabled(true);
			} else {
				isatt.setValue(false);
				adjunct.getWidget().setEnabled(false);
			}
			titleId.setValue(((Double) getSelectedValueByKey("titleId")).intValue() + "");
			logoId.setValue(((Double) getSelectedValueByKey("logoId")).intValue() + "");
			content.setValue(getSelectedValueByKey("content").toString());
			description.setValue(getSelectedValueByKey("description").toString());
			note.setValue(getSelectedValueByKey("note").toString());
			adsituation.setValue(getSelectedValueByKey("adsituation").toString());
			discount.setValue((Double) getSelectedValueByKey("discount"));
			stdRegcost.setValue((Double) getSelectedValueByKey("stdRegcost"));
			total.setValue((Double) getSelectedValueByKey("total"));
			stdLogincost.setValue((Double) getSelectedValueByKey("stdLogincost"));
			stdDiscount.setValue((Double) getSelectedValueByKey("stdDiscount"));
			adamt.setValue(((Double) getSelectedValueByKey("adamt")).intValue());
			idField.setValue(((Double) getSelectedValueByKey("id")).intValue() + "");
			String typeStr = getSelectedValueByKey("type").toString();
			if (typeStr.equals("直走") || typeStr.equals("代理") || typeStr.equals("采购")) {
				typeField.setValue(getSelectedValueByKey("type").toString());
			} else {
				typeField.setValue("-1");
			}
		}
		setButtons(XDialogBox.OKCANCEL);

		formpanel.addSubmitCompleteHandler(new SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(final SubmitCompleteEvent event) {
				hide();
				view.getGrid().load();
			}
		});
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		if (!type) {
			media.setValue(getSelectedValueByKey("mediaId").toString());
		}
	}

	@Override
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(XDialogBox.OK)) {

			// 调用添加方法
			if (formpanel.validate()) {
				formpanel.submit();
			}
		}
		if (button == getButtonByItemId(XDialogBox.CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
	}

	private Object getSelectedValueByKey(final String key) {
		return gird.getSelected().get(0).get(key);
	}
}
