/****************************************************************
 *  系统名称  ： '[gm-p1-adcollect]'
 *  文件名    ： OperationMediaDialog.java
 *  (C) Copyright Green Shore Network Technology Co.,Ltd.2012
 *           	All Rights Reserved.
 * **************************************************************
 *  注意： 本内容仅限于上海绿岸网络科技有限公司内部使用，禁止转发
 ****************************************************************/
package com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.dialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.iwgame.gm.p1.adcollect.modules.hard.admin.client.ui.MediaMgrListView;
import com.iwgame.gm.p1.adcollect.shared.model.DropDownListData;
import com.iwgame.ui.core.client.AsyncCallbackEx;
import com.iwgame.ui.grid.client.SchemaGrid;
import com.iwgame.ui.panel.client.box.MessageBox;
import com.iwgame.ui.panel.client.box.XDialogBox;
import com.iwgame.ui.panel.client.form.FormLayout;
import com.iwgame.ui.panel.client.form.Validator;
import com.iwgame.ui.panel.client.form.XFormPanel;
import com.iwgame.ui.panel.client.form.field.Field;
import com.iwgame.ui.panel.client.form.field.LabelField;
import com.iwgame.ui.panel.client.form.field.NumberField;
import com.iwgame.ui.panel.client.form.field.PlainObjectSelector;
import com.iwgame.ui.panel.client.form.field.TextField;
import com.iwgame.ui.panel.client.form.field.UrlField;
import com.iwgame.xmvp.shared.data.BaseModelData;

/**
 * 类说明
 * 
 * @简述：添加 修改媒体
 * @作者： ByName's pfwang
 * @版本： 1.0
 * @邮箱： wangpengfei@iwgame.com
 * @创建时间：2012-9-27 上午10:01:40
 */
public class OperationMediaDialog extends XDialogBox {

	private final boolean type;
	private final SchemaGrid<BaseModelData> gird;
	private final MediaMgrListView view;
	private final Map<String, Object> oldBase = new HashMap<String, Object>();

	private XFormPanel formPanel;
	private FormLayout layout;

	private PlainObjectSelector<DropDownListData> mediaType;
	private LabelField mediaTypeUpdate;
	private TextField name;
	private PlainObjectSelector<DropDownListData> mediaSort;
	private TextField subclass;
	private UrlField adds;
	private NumberField data1;
	private NumberField data2;
	private NumberField data3;
	private NumberField data4;

	public OperationMediaDialog(final boolean type,
			final MediaMgrListView mediaMgrListView) {
		this.type = type;
		gird = mediaMgrListView.getGrid();
		view = mediaMgrListView;

		formPanel = new XFormPanel();
		formPanel.setWidth("450px");
		layout = new FormLayout();
		layout.setColumn(2);
		formPanel.setLayout(layout);

		mediaType = new PlainObjectSelector<DropDownListData>("媒体分类",
				"mediaType") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		mediaType
				.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

					@Override
					public void loadItem(
							final AsyncCallback<List<DropDownListData>> callback) {
						view.getPresenter().getType(1, callback);
					}
				});
		mediaType.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(final ChangeEvent event) {
				if (mediaType.getValue().equals("-1")) {
					MessageBox.alert("分类不能为空");
				}
			}
		});
		mediaType.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (mediaType.getValue().equals("-1")) {
					return "请选择媒体";
				}
				return null;
			}
		});
		mediaType.setColSpan(2);
		mediaType.setAllowBlank(false);
		mediaType.setValidateOnBlur(true);
		mediaType.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (mediaType.getValue().equals("-1")) {
					return "请选择媒体";
				}
				return null;
			}
		});
		mediaTypeUpdate = new LabelField("媒体分类");
		mediaTypeUpdate.setColSpan(2);
		mediaTypeUpdate.setHiddenLabel(false);
		if (type) {
			layout.add(mediaType);
		} else {
			layout.add(mediaTypeUpdate);
		}

		name = new TextField("名称", "name");
		name.setMaxLength(50);
		name.setAllowBlank(false);
		name.setValidateOnBlur(true);
		name.setColSpan(2);
		layout.add(name);

		mediaSort = new PlainObjectSelector<DropDownListData>("媒体类型",
				"mediaSort") {
			@Override
			protected String getValue(final DropDownListData t) {
				return t.getId();
			}

			@Override
			protected String getLabel(final DropDownListData t) {
				return t.getName();
			}
		};
		mediaSort
				.setDelegate(new PlainObjectSelector.Delegate<DropDownListData>() {

					@Override
					public void loadItem(
							final AsyncCallback<List<DropDownListData>> callback) {
						view.getPresenter().getType(2, callback);
					}
				});
		mediaSort.setColSpan(2);
		mediaSort.addValidator(new Validator() {

			@Override
			public String validate(final Field<?, ?> field) {
				if (mediaSort.getValue().equals("-1")) {
					return "请选择媒体类型";
				}
				return null;
			}
		});
		mediaSort.setAllowBlank(false);
		mediaSort.setValidateOnBlur(true);
		layout.add(mediaSort);

		subclass = new TextField("媒体子类", "subclass");
		subclass.setMaxLength(50);
		subclass.setColSpan(2);
		layout.add(subclass);

		adds = new UrlField("媒体地址", "adds");
		adds.setMaxLength(50);
		// adds.setAllowBlank(false);
		// adds.setValidateOnBlur(true);
		adds.setColSpan(2);
		layout.add(adds);

		data1 = new NumberField("数据1", "data1");
		layout.add(data1);

		data2 = new NumberField("数据2", "data2");
		layout.add(2, data2);

		data3 = new NumberField("数据3", "data3");
		layout.add(data3);

		data4 = new NumberField("数据4", "data4");
		layout.add(2, data4);

		mediaType.getWidget().addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				data2.getWidget().setEnabled(!mediaType.getValue().equals("2"));
			}
		});

		String title = "";
		if (!type) {
			title = "修改id为：" + gird.getSelected().get(0).get("id").toString()
					+ "的媒体";
			data2.setValue(getSelectDoubleToString("data2"));
			setAllValue();
			Double typed = gird.getSelected().get(0).get("type");
			data2.getWidget().setEnabled(typed.intValue()!=2);
		} else {
			title = "添加媒体";
		}

		initBox(title, formPanel);
		setButtons(OKCANCEL);
	}

	private void setAllValue() {
		final String mediaTypeUpdateStr = gird.getSelected().get(0)
				.get("typeStr").toString();
		mediaTypeUpdate.setValue(mediaTypeUpdateStr);
		name.setValue(gird.getSelected().get(0).get("name").toString());
		name.getWidget().setReadOnly(true);

		final Double sort = gird.getSelected().get(0).get("sort");
		if (sort.intValue() != 0) {
			mediaSort.setValue(sort.intValue() + "");
		} else {
			mediaSort.setValue("-1");
		}
		subclass.setValue(gird.getSelected().get(0).get("subclass").toString());
		adds.setValue(gird.getSelected().get(0).get("adds").toString());

		data1.setValue(getSelectDoubleToString("data1"));
		data3.setValue(getSelectDoubleToString("data3"));
		data4.setValue(getSelectDoubleToString("data4"));

		oldBase.put("sort", sort.intValue() + "");
		oldBase.put("subclass", gird.getSelected().get(0).get("subclass")
				.toString());
		oldBase.put("adds", gird.getSelected().get(0).get("adds").toString());
		oldBase.put("data1", getSelectDoubleToString("data1"));
		oldBase.put("data2", getSelectDoubleToString("data2"));
		oldBase.put("data3", getSelectDoubleToString("data3"));
		oldBase.put("data4", getSelectDoubleToString("data4"));

	}

	private int getSelectDoubleToString(final String index) {
		final Double tempD = gird.getSelected().get(0).get(index);
		return tempD.intValue();
	}

	@Override
	protected void onButtonPressed(final Button button) {
		super.onButtonPressed(button);
		if (button == getButtonByItemId(OK)) {
			// 执行 添加 或者 修改操作
			if (formPanel.validate()) {
				if (type && mediaType.getValue().equals("-1")) {
					MessageBox.alert("请选择分类");
					return;
				}
				final Map<String, Object> parameter = new HashMap<String, Object>();
				parameter.put("mediaSort",
						Integer.parseInt(mediaSort.getValue()));
				parameter.put("subclass", subclass.getValue());
				parameter.put("adds", adds.getValue());
				parameter.put("data1", data1.getValue().intValue());
				parameter.put("data2", data2.getValue().intValue());
				parameter.put("data3", data3.getValue().intValue());
				parameter.put("data4", data4.getValue().intValue());
				parameter.put("mediaSortStr", mediaSort.getWidget().getItemText(mediaSort.getWidget().getSelectedIndex()));
				if (type) {
					parameter.put("mediaType",
							Integer.parseInt(mediaType.getValue()));
					parameter.put("name", name.getValue());
					view.getPresenter().addMedia(parameter,
							new AsyncCallbackEx<Integer>() {
								@Override
								public void onSuccess(final Integer result) {
									if (result != 1) {
										MessageBox.alert("添加失败");
									} else {
										view.getGrid().load();
										hide();
									}
								}
							});
				} else {
					parameter.put("id", gird.getSelected().get(0).get("id")
							.toString());

					if (parameter.get("mediaSort").toString()
							.equals(oldBase.get("sort").toString())) {
						parameter.remove("mediaSort");
						oldBase.remove("sort");
					}
					if (parameter.get("subclass").toString()
							.equals(oldBase.get("subclass").toString())) {
						parameter.remove("subclass");
						oldBase.remove("subclass");
					}
					if (parameter.get("adds").toString()
							.equals(oldBase.get("adds").toString())) {
						parameter.remove("adds");
						oldBase.remove("adds");
					}
					if (parameter.get("data1").toString()
							.equals(oldBase.get("data1").toString())) {
						parameter.remove("data1");
						oldBase.remove("data1");
					}
					if (parameter.get("data2").toString()
							.equals(oldBase.get("data2").toString())) {
						parameter.remove("data2");
						oldBase.remove("data2");
					}
					if (parameter.get("data3").toString()
							.equals(oldBase.get("data3").toString())) {
						parameter.remove("data3");
						oldBase.remove("data3");
					}
					if (parameter.get("data4").toString()
							.equals(oldBase.get("data4").toString())) {
						parameter.remove("data4");
						oldBase.remove("data4");
					}

					view.getPresenter().updateMedia(parameter, oldBase,
							new AsyncCallbackEx<Integer>() {

								@Override
								public void onSuccess(final Integer result) {
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
