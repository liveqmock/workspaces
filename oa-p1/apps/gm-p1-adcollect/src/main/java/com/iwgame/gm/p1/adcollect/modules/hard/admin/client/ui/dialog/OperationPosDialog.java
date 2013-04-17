/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： OperationPosDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteHandler;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.presenter.PositionMgrPresenter;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.PositionMgrListView;
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
import com.iwgame.ui.panel.client.form.field.Field;
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
 * @简述： 添加修改广告位
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-10-8 下午04:36:35
 */
public class OperationPosDialog extends XDialogBox {

	private PositionMgrPresenter presenter;

	private PositionMgrListView view;
	private final SchemaGrid<BaseModelData> gird;

	private final XUploadFormPanel formpanel;
	private final FormLayout layout;

	private PlainObjectSelector<DropDownListData> mediaType;
	private DropDownField media;
	private TextField name;
	private DropDownField units;
	private final SimpleSelector adType;
	private TextField identification;
	private TextField link;
	private FileUploadField legend;
	private NumberField generalPrice;
	private NumberField specialPrice;
	private TextAreaField format;
	private TextAreaField explain;
	private TextAreaField remark;
	private HiddenField productIdField;
	private HiddenField addOperationField;
	private HiddenField idField;

	private List<String> unitsIntdex;

	public OperationPosDialog(final boolean type, final PositionMgrListView positionMgrListView) {
		presenter = positionMgrListView.getPresenter();
		view = positionMgrListView;
		gird = positionMgrListView.getGrid();
		formpanel = new XUploadFormPanel(null, "posuploadprocessor");
		formpanel.setWidth("600px");
		layout = new FormLayout();
		layout.setColumn(2);
		formpanel.setLayout(layout);

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
		mediaType.setAllowBlank(false);
		mediaType.setValidateOnBlur(true);

		media = new DropDownField("媒体", "media") {
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
		media.setFieldName("media");
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
		if (type) {
			layout.add(1, mediaType);

			layout.add(2, media);
		}

		name = new TextField("广告位名", "name");
		name.setMaxLength(100);
		name.setAllowBlank(false);
		name.setValidateOnBlur(true);
		layout.add(1, name);

		name.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				presenter.checkPositionName(name.getValue(), new AsyncCallbackEx<Boolean>() {

					@Override
					public void onSuccess(final Boolean result) {
						if (!result) {
							MessageBox.alert("此广告位名已经存在请换一个");
							name.setValue("");
						}
					}
				});
			}
		});

		units = new DropDownField("售卖单位", "units") {

			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		units.setFieldName("units");
		units.addItem("---请先选择媒体分类---", "-1");
		// units.setDelegate(new
		// PlainObjectSelector.Delegate<DropDownListData>() {
		//
		// @Override
		// public void loadItem(
		// final AsyncCallback<List<DropDownListData>> callback) {
		// presenter.getMediaType(5, callback);
		// }
		// });
		units.setEnablePlugin(true);
		units.addMedisSelectedHandlers(new MediaSelectedHandler() {

			@Override
			public void selectMedia(final MediaSelectedEvent event) {
				// 网吧媒体
				if (event.getType() == 2) {
					presenter.getUnitsIsNetBar(new AsyncCallbackEx<List<DropDownListData>>() {

						@Override
						public void onSuccess(final List<DropDownListData> result) {
							units.getWidget().clear();
							units.addItem("--请选择--", "-1");
							for (final DropDownListData ddld : result) {
								units.addItem(ddld.getName(), ddld.getId());
							}
						}
					});
				} else { // 其他媒体
					presenter.getMediaType(5, new AsyncCallbackEx<List<DropDownListData>>() {

						@Override
						public void onSuccess(final List<DropDownListData> result) {
							units.getWidget().clear();
							units.addItem("--请选择--", "-1");
							for (final DropDownListData ddld : result) {
								units.addItem(ddld.getName(), ddld.getId());
							}
						}
					});
				}
			}
		});

		units.setFieldName("units");
		units.setAllowBlank(false);
		units.setValidateOnBlur(true);
		layout.add(2, units);

		link = new TextField("链接", "adds");
		link.setMaxLength(100);
		link.setColSpan(2);
		// layout.add(link);

		adType = new SimpleSelector("广告位类型", "adType") {
			@Override
			public void initItems() {
				addItem("---请选择媒体分类---", "-1");
			}
		};
		adType.setAllowBlank(false);
		adType.setValidateOnBlur(true);

		adType.addValidator(new Validator() {

			@Override
			public String validate(Field<?, ?> field) {

				if (adType.getValue().equals("-1")) {
					return "请选择广告位类型";
				}
				return null;
			}
		});

		adType.setFieldName("adType");
		identification = new TextField("识别码", "adid");
		identification.setValidateOnBlur(true);
		identification.setAllowBlank(false);
		identification.getWidget().setMaxLength(7);

		mediaType.getWidget().addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(final ChangeEvent event) {
				if (type) {
					final int temptype = Integer.parseInt(mediaType.getValue());
					AppUtils.EVENT_BUS.fireEvent(new MediaSelectedEvent(temptype));
				}

				if (mediaType.getValue().equals("2")) {
					adType.getWidget().clear();
					adType.addItem("---请选择广告位类型---", "-1");
					adType.addItem("客户端推送", "1");
					adType.addItem("独立广告位", "2");
					adType.addItem("推送相关位置", "3");
					identification.setValue("");
					identification.getWidget().setEnabled(true);
				} else {
					adType.getWidget().clear();
					adType.addItem("---请选择广告位类型---", "-1");
					adType.addItem("独立广告位", "2");
					identification.setValue("后台自动生成");
					identification.getWidget().setEnabled(false);

				}

			}
		});

		adType.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (adType.getValue().equals("2")) {
					identification.setValue("后台自动生成");
					identification.getWidget().setEnabled(false);
				} else {
					identification.setValue("");
					identification.getWidget().setEnabled(true);
				}

			}
		});

		if (type) {
			layout.add(1, adType);
			layout.add(2, identification);
		}

		adType.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				if (adType.getValue().equals("39")) {
					identification.setValue("独立广告位没识别码");
				} else if (identification.getValue().equals("独立广告位没识别码")
						&& (adType.getValue().equals("38") || adType.getValue().equals("40"))) {
					identification.setValue("");
				}
			}
		});

		legend = new FileUploadField("广告位图例", "legend");
		legend.setAccepter("jpg", "png", "gif");
		legend.setColSpan(2);
		layout.add(1, legend);

		generalPrice = new NumberField("单位原价一般日", "generalPrice");
		generalPrice.getWidget().setMaxLength(11);
		generalPrice.setDecimal(isModal());
		generalPrice.setPrecision(2);
		layout.add(1, generalPrice);

		specialPrice = new NumberField("单位原价特殊日", "specialPrice");
		specialPrice.getWidget().setMaxLength(11);
		specialPrice.setDecimal(isModal());
		specialPrice.setPrecision(2);
		layout.add(2, specialPrice);

		format = new TextAreaField("规格", "format");
		format.setMaxLength(50);
		format.getWidget().setHeight("60px");
		format.setColSpan(2);
		layout.add(1, format);

		explain = new TextAreaField("说明", "explain");
		explain.setMaxLength(100);
		explain.getWidget().setHeight("60px");
		explain.setColSpan(2);
		layout.add(1, explain);

		remark = new TextAreaField("备注", "remark");
		remark.setMaxLength(200);
		remark.getWidget().setHeight("60px");
		remark.setColSpan(2);
		layout.add(1, remark);

		productIdField = new HiddenField("productId", "productId");
		productIdField.setValue(ApplicationContext.getCurrentProductId());
		layout.add(productIdField);

		addOperationField = new HiddenField("operation", "operation");
		addOperationField.setValue(type ? "add" : "update");
		layout.add(addOperationField);

		idField = new HiddenField("id", null);
		layout.add(idField);

		if (!type) {
			String dmediaId = gird.getSelected().get(0).get("mediaId").toString();
			if (dmediaId.substring(0, 2).trim().equals("02")) {// 网吧媒体
				presenter.getUnitsIsNetBar(new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						setUnitValues(result);
					}
				});
			} else {// 其他的媒体
				presenter.getMediaType(5, new AsyncCallbackEx<List<DropDownListData>>() {

					@Override
					public void onSuccess(final List<DropDownListData> result) {
						setUnitValues(result);
					}
				});
			}

			idField.setValue(gird.getSelected().get(0).get("id").toString());
			name.setValue(gird.getSelected().get(0).get("name").toString());

			// legend.setValue(gird.getSelected().get(0).get("legend").toString());
			generalPrice.setValue((Number) gird.getSelected().get(0).get("generalPrice"));
			specialPrice.setValue((Number) gird.getSelected().get(0).get("specialPrice"));
			specialPrice.setValue((Number) gird.getSelected().get(0).get("specialPrice"));
			link.setValue(gird.getSelected().get(0).get("adds").toString());
			format.setValue(gird.getSelected().get(0).get("format").toString());
			explain.setValue(gird.getSelected().get(0).get("explain").toString());
			remark.setValue(gird.getSelected().get(0).get("remark").toString());
			name.getWidget().setEnabled(false);
			initBox("修改广告位", formpanel);
		} else {
			initBox("添加广告位", formpanel);
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
	protected void onButtonPressed(final Button button) {
		if (button == getButtonByItemId(XDialogBox.OK)) {
			// 调用添加方法
			if (formpanel.validate()) {
				if (units.getValue().equals("-1")) {
					MessageBox.alert("请选择售卖单位");
					return;
				}
				formpanel.submit();
			}
		}
		if (button == getButtonByItemId(XDialogBox.CANCEL)) {
			hide();
		}
		super.onButtonPressed(button);
	}

	/**
	 * 
	 * 设置 售卖单位
	 * 
	 * @param result
	 */
	private void setUnitValues(final List<DropDownListData> result) {
		units.getWidget().clear();
		units.addItem("--请选择--", "-1");
		unitsIntdex = new ArrayList<String>();
		unitsIntdex.add("-1");
		for (final DropDownListData ddld : result) {
			units.addItem(ddld.getName(), ddld.getId());
			unitsIntdex.add(ddld.getId());
		}
		final Double d = gird.getSelected().get(0).get("units");

		for (int i = 0; i < unitsIntdex.size(); i++) {
			String id = unitsIntdex.get(i);
			if (id.equals(d.intValue() + "")) {
				units.getWidget().setSelectedIndex(i); // i+1 是因为 line 410
			}
		}
	}
}
